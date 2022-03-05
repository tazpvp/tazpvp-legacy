package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class MutechatCMD {
    @CommandHook("mutechat")
    public void muteChat(CommandSender sender) {
        if (sender instanceof Player p) {
            if (Tazpvp.chatMuted) {
                Tazpvp.chatMuted = false;
                p.sendMessage(ChatColor.GOLD + "Successfully" + ChatColor.RED + " unmuted " + ChatColor.GOLD + "chat!");
            } else {
                Tazpvp.chatMuted = true;
                p.sendMessage(ChatColor.GOLD + "Successfully" + ChatColor.RED + " muted " + ChatColor.GOLD + "chat!");
            }
        }
    }
}
