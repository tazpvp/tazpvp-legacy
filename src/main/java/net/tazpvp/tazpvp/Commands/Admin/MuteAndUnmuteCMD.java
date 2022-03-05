package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class MuteAndUnmuteCMD {
    @CommandHook("mute")
    public void mute(CommandSender sender, Player target) {
        if (!Tazpvp.punishmentManager.isMuted(target)) {
            Tazpvp.punishmentManager.initMute(target, true);
            sender.sendMessage(ChatColor.GOLD + "You have muted " + ChatColor.RED + target.getName());
            target.sendMessage(ChatColor.GOLD + "You have been muted");
        } else {
            sender.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " is already muted");
        }
    }

    @CommandHook("unmute")
    public void unmute(CommandSender sender, Player target) {
        if (Tazpvp.punishmentManager.isMuted(target)) {
            Tazpvp.punishmentManager.removeMute(target);
            sender.sendMessage(ChatColor.GOLD + "You have unmuted " + ChatColor.RED + target.getName());
            target.sendMessage(ChatColor.GOLD + "You have been unmuted");
        } else {
            sender.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " is not muted");
        }
    }
}
