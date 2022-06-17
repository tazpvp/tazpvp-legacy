package net.tazpvp.tazpvp.Commands.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.commandmanager.CommandHook;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VotekickCMD {
    @CommandHook("votekick") public void onCommandSend(Player p,Player target) {
        if (target.getName().equals(p.getName())) {
            p.sendMessage(ChatColor.RED + "You can't votekick yourself!");
            return;
        }
        if (target.hasPermission("tazpvp.staff")) {
            p.sendMessage(ChatColor.RED + "You can't votekick staff!");
            return;
        }
        if (Tazpvp.toBeKicked != null) {
            p.sendMessage(ChatColor.RED + "Someone is already being votekicked!");
            return;
        }

        Tazpvp.toBeKicked = target;
        p.sendMessage(ChatColor.GREEN + "You have started a vote to kick " + target.getName() + "!");

        // announce votekicks
        Bukkit.broadcastMessage(ChatColor.GOLD + Tazpvp.toBeKicked.getName() + ChatColor.YELLOW + " is being votekicked by " + ChatColor.GOLD + p.getName() + "!");

        TextComponent invite = new TextComponent(ChatColor.GREEN + "YES");
        invite.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "CLICK TO VOTE YES").create()));
        invite.setClickEvent(new net.md_5.bungee.api.chat.ClickEvent(ClickEvent.Action.RUN_COMMAND, "/yes"));

        TextComponent no = new TextComponent(ChatColor.RED + "NO");
        invite.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "CLICK TO VOTE NO").create()));

        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.spigot().sendMessage(invite);
            pl.spigot().sendMessage(no);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Tazpvp.toBeKicked == null) {
                    cancel();
                    return;
                }
                List<UUID> didntVote = new ArrayList<>();
                for (Player plr : Bukkit.getOnlinePlayers()) {
                    if (!Tazpvp.votedYes.contains(plr.getUniqueId())) {
                        didntVote.add(plr.getUniqueId());
                    }
                }

                if (Tazpvp.votedYes.size() > didntVote.size()) {
                    if (Tazpvp.toBeKicked.isOnline()) {
                        Tazpvp.toBeKicked.sendMessage(ChatColor.RED + "You have been voted to be kicked by " + p.getName() + "!");
                        Tazpvp.toBeKicked.kick();
                    }
                    Tazpvp.tempBan.put(Tazpvp.toBeKicked.getUniqueId(), System.currentTimeMillis());
                    Bukkit.broadcastMessage(ChatColor.RED + Tazpvp.toBeKicked.getName() + " has kicked!");
                } else {
                    Bukkit.broadcastMessage(ChatColor.RED + "Not enough votes to kick " + Tazpvp.toBeKicked.getName() + "!");
                }
                Tazpvp.toBeKicked = null;
                Tazpvp.votedYes.clear();
            }
        }.runTaskLater(Tazpvp.getInstance(), 20 * 60);

    }

    @CommandHook("yes") public void yesCommand(Player p) {
        if (Tazpvp.toBeKicked == null) {
            p.sendMessage(ChatColor.RED + "No one is being votekicked!");
            return;
        }

        if (Tazpvp.votedYes.contains(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You have already voted!");
            return;
        }

        Tazpvp.votedYes.add(p.getUniqueId());
        p.sendMessage(ChatColor.GREEN + "You have voted yes, This vote is unchangeable!");
    }
}
