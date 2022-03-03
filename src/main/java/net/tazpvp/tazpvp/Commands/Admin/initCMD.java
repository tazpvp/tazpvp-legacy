package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class initCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p && commandSender.hasPermission("tazpvp.*")) {
            Tazpvp.statsManager.initPlayer(p);
            p.sendMessage("Stats wurden erfolgreich initialisiert!");
        }
        return true;
    }
}
