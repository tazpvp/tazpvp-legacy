package net.tazpvp.tazpvp.Events;

import net.md_5.bungee.api.ChatColor;
import net.tazpvp.tazpvp.Guilds.GuildConfig;
import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.ChatEnum;
import net.tazpvp.tazpvp.Utils.Functionality.ChatUtils;
import net.tazpvp.tazpvp.Utils.Functionality.DiscordMCUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatEvent implements Listener {

    final HashMap<Player, String> previousMessages = new HashMap<>();
    final ArrayList<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        String prefix = Tazpvp.chat.getPlayerPrefix(p);
        msg = ChatColor.translateAlternateColorCodes('&', prefix) + msg;
        ChatColor lvl = ChatColor.GRAY;
        String lvltxt = ChatColor.GRAY + "[" + lvl + Tazpvp.statsManager.getLevel(p) + ChatColor.GRAY + "] ";
        String tag = (GuildUtils.isInGuild(p) && GuildUtils.getGuild(p).tag() != null)
                ? ChatColor.YELLOW + "[" + GuildUtils.getGuild(p).tag() + ChatColor.YELLOW + "] " : "";
        String fmsg;
        if (Tazpvp.hidden.contains(e.getPlayer().getUniqueId())) {
            fmsg = lvltxt + ChatColor.GRAY + "%s " + tag;
        } else {
            fmsg = lvltxt + ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(p)) + "%s " + tag;
        }


        if (p.hasMetadata("staffchat")) {
            ChatUtils.sendStaffChatMessage(p, e.getMessage());
        }


        if (p.isOp()) {
            msg = (ChatColor.translateAlternateColorCodes('&', msg));
        }

        if (Tazpvp.punishmentManager.isMuted(p)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You is muted!");
            return;
        }
        if (Tazpvp.punishmentManager.isBanned(p)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You is banned!");
            return;
        }

        if (!p.hasPermission("tazpvp.chat")) {
            if (Tazpvp.chatMuted){
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Chat is currently muted.");
                return;
            }
            if (GuildConfig.isOffending(msg)) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You are not allowed to use this word.");
                return;
            }
            if (cooldown.contains(p)) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "Please don't spam the chat!");
            }
            if (previousMessages.containsKey(p)){
                if (msg.contains(previousMessages.get(p))) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "You cannot repeat the same message!");
                }
            }
        }
        if (Tazpvp.statsManager.getRebirth(p) > 0) {
            lvl = ChatColor.GOLD;
        }
        if (p.hasPermission("tazpvp.level")) {
            lvltxt = "";
        }

        if (e.getMessage().startsWith("@")) {
            if (GuildUtils.isInGuild(p)) {
                GuildUtils.guildChatMessage(p, e.getMessage().replaceFirst("@", ""), GuildUtils.getGuild(p));
                e.setCancelled(true);
                return;
            } else {
                p.sendMessage(ChatColor.RED + "You are not in a guild!");
                e.setCancelled(true);
                return;
            }
        }

        if (Tazpvp.chatEnum.get(p.getUniqueId()) == ChatEnum.STAFF) {
            e.setCancelled(true);
            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (pl.hasPermission("tazpvp.staff")) {
                    String begining = ChatColor.AQUA + "Staff > ";
                    String name = p.getDisplayName() + " ";
                    String msg2 = org.bukkit.ChatColor.WHITE + e.getMessage();
                    pl.sendMessage(begining + name + msg2);
                }
            }
            return;
        } else if (Tazpvp.chatEnum.get(p.getUniqueId()) == ChatEnum.GUILD) {
            GuildUtils.guildChatMessage(p, e.getMessage().replaceFirst("@", ""), GuildUtils.getGuild(p));
            e.setCancelled(true);
            return;
        }


        String format = (p.hasPermission("tazpvp.chat") && !(Tazpvp.hidden.contains(p.getUniqueId())))
                ? fmsg + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', "%s")
                : fmsg + ChatColor.GRAY + "%s";
//        if (!Tazpvp.hidden.contains(p.getUniqueId())) {
//            format = p.hasPermission("tazpvp.chat")
//                    ? fmsg + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', "%s")
//                    : fmsg + ChatColor.GRAY + "%s";
//        } else {
//            format = ChatColor.GRAY + "%s";
//        }
        e.setFormat(format);
        DiscordMCUtils.sendMessageToDiscord(e.getMessage(), p);

//        String format = lvltxt + ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(p)) + "%s " + ChatColor.WHITE + "%s";
//        e.setFormat(format);

        previousMessages.put(p, msg);
        cooldown.add(p);
        new BukkitRunnable() {
            @Override
            public void run() {
                cooldown.remove(p);
            }
        }.runTaskLater(Tazpvp.getInstance(), 10);

    }
}
