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
        no.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "CLICK TO VOTE NO").create()));
        no.setClickEvent(new net.md_5.bungee.api.chat.ClickEvent(ClickEvent.Action.RUN_COMMAND, "/no"));

        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.sendMessage("");
            pl.spigot().sendMessage(invite);
            pl.sendMessage("");
            pl.spigot().sendMessage(no);
            pl.sendMessage("");
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Tazpvp.toBeKicked == null) {
                    cancel();
                    return;
                }

                if (Tazpvp.votedYes.size() > Tazpvp.votedNo.size()) {
                    if (Tazpvp.toBeKicked.isOnline()) {
                        Tazpvp.toBeKicked.sendMessage(ChatColor.RED + "You have been voted to be kicked by " + p.getName() + "!");
                        Tazpvp.toBeKicked.kick();
                    }
                    Tazpvp.tempBan.put(Tazpvp.toBeKicked.getUniqueId(), System.currentTimeMillis());
                    Bukkit.broadcastMessage(ChatColor.RED + Tazpvp.toBeKicked.getName() + " has been kicked!");
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

        if (Tazpvp.votedYes.contains(p.getUniqueId()) || Tazpvp.votedNo.contains(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You have already voted!");
            return;
        }

        Tazpvp.votedYes.add(p.getUniqueId());
        p.sendMessage(ChatColor.GREEN + "You have voted yes, This vote is unchangeable!");
    }

    @CommandHook("no") public void noCommand(Player p) {
        if (Tazpvp.toBeKicked == null) {
            p.sendMessage(ChatColor.RED + "No one is being votekicked!");
            return;
        }

        if (Tazpvp.votedYes.contains(p.getUniqueId()) || Tazpvp.votedNo.contains(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You have already voted!");
            return;
        }

        Tazpvp.votedNo.add(p.getUniqueId());
        p.sendMessage(ChatColor.RED + "You have voted no, This vote is unchangeable!");
    }
}
