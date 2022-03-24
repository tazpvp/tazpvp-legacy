package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class NickCMD {

    @CommandHook("nick")
    public void nick(Player p, Player target, String nickname) {
        if (nickname == null) {
            target.setDisplayName(target.getName());
            target.setPlayerListName(target.getName());
            target.sendMessage(ChatColor.GOLD + "Your nickname has been " + ChatColor.RED + "reset");
        } else {
            target.setDisplayName(nickname);
            target.setPlayerListName(nickname);
            target.sendMessage(ChatColor.GOLD + "Your nickname has been set to " + ChatColor.RED + nickname);
        }

    }
}
