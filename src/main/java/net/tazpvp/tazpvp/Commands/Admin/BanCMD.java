package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.DiscordBot.Events.SendBanNotification;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.commandmanager.CommandHook;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class BanCMD {
    @CommandHook("ban")
    public void ban(CommandSender sender, Player target, String reason) throws IOException {
        if (reason == null) {
            reason = "Unfair Advantage";
        }
        if (!Tazpvp.punishmentManager.isBanned(target)) {
            Tazpvp.punishmentManager.initBan(target, true, reason);
            for (Player p : Bukkit.getOnlinePlayers()){
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                if (!p.getName().equals(target.getName())){
                    p.sendMessage(ChatColor.DARK_GRAY + "");
                    p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " has been banned for " + ChatColor.WHITE + reason);
                    p.sendMessage(ChatColor.DARK_GRAY + "");
                }
            }

            SendBanNotification.sendBanNotification(target.getUniqueId(), sender);

            final String rs = reason;
            target.teleport(new Location(Bukkit.getWorld("ban"), 0, 77, 0));
            new BukkitRunnable(){
                public void run(){
                    target.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "BANNED", ChatColor.GOLD + "You have been sent to The Depths.", 10, 100, 10);

                    target.sendMessage(ChatColor.DARK_GRAY + "");
                    target.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN" + ChatColor.GRAY + " You've been banned for " + ChatColor.WHITE + rs);
                    target.sendMessage(ChatColor.GRAY + "If you wish to be unbanned, do not log out. Follow the steps in " + ChatColor.WHITE + "/appeal");
                    target.sendMessage(ChatColor.DARK_GRAY + "");
                }
            }.runTaskLater(Tazpvp.getInstance(), 30);
        } else {
            sender.sendMessage(ChatColor.RED + target.getName() + " is already banned");
        }
    }

    @CommandHook("unban")
    public void unban(CommandSender sender, Player target) throws IOException {
        if (Tazpvp.punishmentManager.isBanned(target)) {
            Tazpvp.punishmentManager.removeBan(target);
            for (Player p : Bukkit.getOnlinePlayers()){
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                if (p.hasPermission("tazpvp.ban")){
                    p.sendMessage("");
                    p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "UNBAN " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " has been unbanned by " + ChatColor.WHITE + sender.getName());
                    p.sendMessage("");
                }
            }
            target.sendMessage("");
            target.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "UNBAN" + ChatColor.GRAY + " Congratulations! You have been unbanned.");
            target.sendMessage("");
            target.teleport(new Location(Bukkit.getWorld("arena"), 0.5, 101, -1.5, 0, 0));
            SendBanNotification.sendUnBanNotification(target.getUniqueId(), sender);
        } else {
            sender.sendMessage(ChatColor.RED + target.getName() + " is not banned.");
        }
    }
}
