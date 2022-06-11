package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class MuteCMD {
    @CommandHook("mute")
    public void mute(CommandSender sender, Player target) {
        if (!Tazpvp.punishmentManager.isMuted(target)) {
            Tazpvp.punishmentManager.initMute(target, true);
            sender.sendMessage(ChatColor.DARK_AQUA + "You muted " + ChatColor.AQUA + target.getName());
            target.sendMessage(ChatColor.DARK_AQUA + "You have been muted");
        } else {
            sender.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " is already muted");
        }
    }

    @CommandHook("unmute")
    public void unmute(CommandSender sender, Player target) {
        if (Tazpvp.punishmentManager.isMuted(target)) {
            Tazpvp.punishmentManager.removeMute(target);
            sender.sendMessage(ChatColor.DARK_AQUA + "You unmuted " + ChatColor.AQUA + target.getName());
            target.sendMessage(ChatColor.DARK_AQUA + "You have been unmuted");
        } else {
            sender.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " is not muted");
        }
    }
}
