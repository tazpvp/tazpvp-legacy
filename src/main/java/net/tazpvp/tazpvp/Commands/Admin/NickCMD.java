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
        target.setDisplayName(nickname);
        target.setPlayerListName(nickname);
        target.sendMessage(ChatColor.GOLD + "Your nickname has been set to " + ChatColor.RED + nickname);
    }
}
