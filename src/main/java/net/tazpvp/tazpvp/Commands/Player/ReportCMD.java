package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.CmdCooldown;
import net.tazpvp.tazpvp.Utils.Functionality.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReportCMD extends CmdCooldown {
    @Override
    public void execute(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(ChatColor.RED + "Usage: /report <player> <reason>");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        String reason = StringUtils.buildString(args, 1);
        if (Tazpvp.hasBeenReported.containsKey(target.getUniqueId())) {
            p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
        } else {
            Bukkit.broadcast(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "                                                                         ", "tazpvp.reports");
            Bukkit.broadcast(ChatColor.RED + "" + ChatColor.BOLD + "REPORT " + ChatColor.WHITE + p.getName() + ChatColor.GRAY + " reported " + ChatColor.WHITE + target.getName(), "tazpvp.reports");
            Bukkit.broadcast(ChatColor.GRAY+ "Reason: " + ChatColor.WHITE + reason, "tazpvp.reports");
            Bukkit.broadcast(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "                                                                         ", "tazpvp.reports");
            p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
        }
    }

    public ReportCMD() {
        super(20*60);
    }
}
