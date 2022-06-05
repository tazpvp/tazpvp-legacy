package net.tazpvp.tazpvp.Events.ChatEvents;

import net.tazpvp.tazpvp.GUI.MainMenu.SubMenu.ServerStore;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatEvent implements Listener {

    final HashMap<Player, String> previousMessages = new HashMap<>();
    final ArrayList<Player> cooldown = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        Player p = e.getPlayer();
        String prefix = Tazpvp.chat.getPlayerPrefix(p);
        msg = ChatColor.translateAlternateColorCodes('&', prefix) + msg;
        if (e.getPlayer().isOp()) {
            msg = (ChatColor.translateAlternateColorCodes('&', msg));
        }

        if (Tazpvp.punishmentManager.isBanned(p)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You is banned!");
            return;
        }

        if (Tazpvp.chatMuted){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Chat is currently muted.");
            return;
        }
        List<String> badWord = new ArrayList<>();
        badWord.add("fuck");
        badWord.add("shit");
        badWord.add("bitch");
        badWord.add("shit");
        badWord.add("dick");
        badWord.add("dildo");
        badWord.add("pussy");
        badWord.add("porn");
        badWord.add("whore");
        badWord.add("fag");
        badWord.add("cock");
        badWord.add("retar");
        badWord.add("cunt");
        badWord.add("penis");
        badWord.add("bitc");
        badWord.add("nigg");
        badWord.add("slut");
        badWord.add("fuk");
        badWord.add("fuc");
        for (int i = 0; i < badWord.size(); i++) {
            if (msg.contains(badWord.get(i))) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.GOLD + "You cannot say: " + ChatColor.RED + badWord.get(i));
            }
        }

        if (cooldown.contains(p) && !p.isOp()) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Please don't spam the chat!");
        }

        if (previousMessages.containsKey(p) && !p.isOp()){
            if (msg.equalsIgnoreCase(previousMessages.get(p))) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "You cannot repeat the same message!");
            }
        }

        ChatColor lvl = ChatColor.GRAY;
        if (Tazpvp.statsManager.getRebirth(p) > 0) {
            lvl = ChatColor.GOLD;
        }
        String lvltxt = ChatColor.GRAY + "[" + lvl + Tazpvp.statsManager.getLevel(p) + ChatColor.GRAY + "] ";
        if (p.hasPermission("tazpvp.level")) {
            lvltxt = "";
        }

        String fmsg = lvltxt + ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(p)) + "%s ";

        String format = p.isOp()
                ? fmsg + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', "%s")
                : fmsg + ChatColor.WHITE + "%s";
        e.setFormat(format);

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
