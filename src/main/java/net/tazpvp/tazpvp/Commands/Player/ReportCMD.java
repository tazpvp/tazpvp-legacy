package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p) {
            if (strings.length <= 1) {
                return false;
            } else {
                String reason = StringUtils.buildString(strings, 1);
                Player reported = Bukkit.getPlayer(strings[0]);
                Bukkit.broadcast(ChatColor.DARK_GRAY +"▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n"+ChatColor.RED+""+ChatColor.BOLD +"REPORT " + ChatColor.WHITE + reported.getName() + ChatColor.GRAY + " was reported for " + ChatColor.WHITE + reason + ChatColor.GRAY+" by "+ChatColor.WHITE + p.getName() + "\n" + ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬", "tazspree.staff.reports");

                p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + reported.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
            }
        }
        return true;
    }
}
