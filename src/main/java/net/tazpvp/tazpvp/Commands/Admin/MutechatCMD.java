package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import redempt.redlib.commandmanager.CommandHook;

public class MutechatCMD {
    @CommandHook("mutechat")
    public void muteChat(CommandSender sender) {
        if (Tazpvp.chatMuted) {
            Tazpvp.chatMuted = false;
            sender.sendMessage(ChatColor.GOLD + "Successfully" + ChatColor.RED + " unmuted " + ChatColor.GOLD + "chat!");
        } else {
            Tazpvp.chatMuted = true;
            sender.sendMessage(ChatColor.GOLD + "Successfully" + ChatColor.RED + " muted " + ChatColor.GOLD + "chat!");
        }
    }
}
