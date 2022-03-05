package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import redempt.redlib.commandmanager.CommandHook;

public class AnnounceCMD {
    @CommandHook("announce")
    public void announce(CommandSender sender, String message) {
        Bukkit.broadcastMessage(message);
    }
}
