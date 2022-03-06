package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import redempt.redlib.commandmanager.CommandHook;

public class NickCMD {

    @CommandHook("nick")
    public void nick(Player p, Player target, String nickname) {
        if (nickname.equalsIgnoreCase("reset")) {
            target.setDisplayName(target.getName());
            target.setPlayerListName(target.getName());
            p.sendMessage(ChatColor.GOLD + "Nickname " + ChatColor.RED + "reset.");
        } else {
            target.setDisplayName(nickname);
            target.setPlayerListName(nickname);
            target.sendMessage(ChatColor.GOLD + "Your nickname has been set to " + ChatColor.RED + nickname);
        }
    }
}
