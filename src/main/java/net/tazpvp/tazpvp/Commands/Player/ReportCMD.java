package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ReportCMD implements CommandListener {
    @CommandHook("report")
    public void report(Player p, Player target, String reason) {
        if (Tazpvp.hasBeenReported.containsKey(target.getUniqueId())) {
            p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
        } else {
            Bukkit.broadcast(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n" + ChatColor.RED + "" + ChatColor.BOLD + "REPORT " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " was reported for " + ChatColor.WHITE + reason + ChatColor.GRAY + " by " + ChatColor.WHITE + p.getName() + "\n" + ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬", "tazspree.staff.reports");
            p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
        }
    }
}
