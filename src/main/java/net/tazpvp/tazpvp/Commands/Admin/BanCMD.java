package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.DiscordBot.Events.SendBanNotification;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.commandmanager.CommandHook;

import java.io.IOException;

public class BanCMD implements CommandListener {
    @CommandHook("ban")
    public void ban(CommandSender sender, Player target, String reason) throws IOException {
        if (reason == null) {
            reason = "Unfair Advantage";
        }
        if (target.hasPermission("tazpvp.banBypass")) {
            sender.sendMessage(ChatColor.RED + "You cannot ban this player!");
            return;
        }
        if (target == sender) {
            sender.sendMessage(ChatColor.RED + "You cannot ban yourself!");
            return;
        }
        if (!Tazpvp.punishmentManager.isBanned(target)) {
            Tazpvp.punishmentManager.initBan(target, true, reason);
            SendBanNotification.sendBanNotification(target.getUniqueId(), sender, reason);

            final String rs = reason;
            target.teleport(new Location(Bukkit.getWorld("ban"), 0.5, 70, 0.5));
            new BukkitRunnable(){
                public void run(){
                    target.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "BANNED", ChatColor.GOLD + "You have been sent to The Depths.", 10, 100, 10);

                    target.sendMessage(ChatColor.DARK_GRAY + "");
                    target.sendMessage(ChatColor.DARK_RED + " You've been banned for " + ChatColor.GRAY + rs);
                    target.sendMessage(ChatColor.DARK_RED + " If you wish to be unbanned, do not log out. Type " + ChatColor.GRAY + "/appeal");
                    target.sendMessage(ChatColor.DARK_GRAY + "");
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, PlayerUtils.setLPRankCommand(target, "banned"));

                    for (Player p : Bukkit.getOnlinePlayers()){
                        p.playSound(p.getLocation(), Sound.BLOCK_GRINDSTONE_USE, 1, 1);
                        if (!p.getName().equals(target.getName())){
                            p.sendMessage(ChatColor.DARK_GRAY + "");
                            p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "BAN " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " has been banned for " + ChatColor.WHITE + rs);
                            p.sendMessage(ChatColor.DARK_GRAY + "");
                        }
                    }
                }
            }.runTaskLater(Tazpvp.getInstance(), 20);
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
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, "lp user " + target.getName() + " group remove " + "banned");
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
