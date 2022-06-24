package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import redempt.redlib.commandmanager.CommandHook;

public class MutechatCMD implements CommandListener {
    @CommandHook("mutechat")
    public void muteChat(CommandSender sender) {
        if (Tazpvp.chatMuted) {
            Tazpvp.chatMuted = false;
            Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Chat has been UN-MUTED");
        } else {
            Tazpvp.chatMuted = true;
            Bukkit.broadcastMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Chat has been MUTED");
        }
    }
}
