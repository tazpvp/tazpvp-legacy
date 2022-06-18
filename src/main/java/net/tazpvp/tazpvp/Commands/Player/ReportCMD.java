package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import redempt.redlib.commandmanager.CommandHook;

public class ReportCMD implements CommandExecutor {
    @Override
    public void execute(Player p, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        String reason = StringUtils.buildString(args, 1);
        if (Tazpvp.hasBeenReported.containsKey(target.getUniqueId())) {
            p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
        } else {
            Bukkit.broadcast(ChatColor.GRAY + " " + ChatColor.STRIKETHROUGH + "                                        ", "tazpvp.reports");
            Bukkit.broadcast(ChatColor.RED + "" + ChatColor.BOLD + "REPORT " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " reported " + ChatColor.WHITE + p.getName() + ChatColor.GRAY+ " for " + ChatColor.WHITE + reason, "tazpvp.reports");
            Bukkit.broadcast(ChatColor.GRAY + " " + ChatColor.STRIKETHROUGH + "                                        ", "tazpvp.reports");
            p.sendMessage(ChatColor.DARK_AQUA + "Thank you for reporting " + ChatColor.WHITE + target.getName() + ChatColor.DARK_AQUA + " they will be reviewed shortly.");
        }
        return;
    }

    public ReportCMD() {
        super (20*60);
    }
}
