package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ReportCMD {
    @CommandHook("report")
    public void report(Player p, Player target, String reason) {
        Bukkit.broadcast(ChatColor.DARK_GRAY +"▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n"+ChatColor.RED+""+ChatColor.BOLD +"REPORT " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " was reported for " + ChatColor.WHITE + reason + ChatColor.GRAY+" by "+ChatColor.WHITE + p.getName() + "\n" + ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬", "tazspree.staff.reports");
        p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
    }
}
