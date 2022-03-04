package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class StatsCMD {

    //Points Command
    @CommandHook("point_add")
    public void pointAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addPoints(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have added " + ChatColor.RED + amount + ChatColor.GOLD + " points to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " points to " + target.getName());
        }
    }
}
