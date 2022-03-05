package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import redempt.redlib.commandmanager.CommandHook;

public class NickCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 1) {
                String nick = args[0];
                if (!(nick.length() > 16)) {
                    p.setDisplayName(nick);
                    p.setPlayerListName(nick);
                    p.sendMessage(ChatColor.GOLD + "Your nickname has been set to " + ChatColor.RED + nick);
                } else {
                    return false;
                }
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                String nick = args[1];
                if (target != null) {
                    if (!(nick.length() > 16)) {
                        target.setDisplayName(nick);
                        target.setPlayerListName(nick);
                        target.sendMessage(ChatColor.GOLD + "Your nickname has been set to " + ChatColor.RED + nick);
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
