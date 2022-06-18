package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.commandmanager.CommandHook;

public class ReportCMD implements CommandListener extends CmdCooldown {
    @CommandHook("report")
    public void report(Player p, Player target, String reason) {

        if (Tazpvp.hasBeenReported.containsKey(target.getUniqueId())) {
            p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
        } else {
            Bukkit.broadcast(ChatColor.GRAY + " " + ChatColor.STRIKETHROUGH + "                                        ", "tazpvp.reports");
            Bukkit.broadcast(ChatColor.RED + "" + ChatColor.BOLD + "REPORT " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " reported " + ChatColor.WHITE + p.getName() + ChatColor.GRAY+ " for " + ChatColor.WHITE + reason, "tazpvp.reports");
            Bukkit.broadcast(ChatColor.GRAY + " " + ChatColor.STRIKETHROUGH + "                                        ", "tazpvp.reports");
            p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
        }
    }
}
