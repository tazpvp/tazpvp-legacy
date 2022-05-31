package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.util.Arrays;

public class BanCMD {
    @CommandHook("ban")
    public void ban(CommandSender sender, Player target, String... reason) {
        if (!Tazpvp.punishmentManager.isBanned(target)) {
            Tazpvp.punishmentManager.initBan(target, true, Arrays.toString(reason));
            sender.sendMessage(ChatColor.GOLD + "You have banned " + ChatColor.RED + target.getName() + ChatColor.GOLD + " for " + ChatColor.RED + Arrays.toString(reason));
            target.teleport(new Location(Bukkit.getWorld("ban"), 0, 70, 0));
        } else {
            sender.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " is already banned");
        }
    }

    @CommandHook("unban")
    public void unban(CommandSender sender, Player target) {
        if (Tazpvp.punishmentManager.isBanned(target)) {
            Tazpvp.punishmentManager.removeBan(target);
            sender.sendMessage(ChatColor.GOLD + "You have unbanned " + ChatColor.RED + target.getName());
            target.sendMessage(ChatColor.GOLD + "You have been unbanned");
            target.teleport(new Location(Bukkit.getWorld("arena"), 0.5, 101, -1.5, 0, 0));
        } else {
            sender.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " is not banned");
        }
    }
}
