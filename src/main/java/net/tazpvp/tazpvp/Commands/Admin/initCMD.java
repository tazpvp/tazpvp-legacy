package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class initCMD  {

    @CommandHook("init")
    public void hook(Player p){
        Tazpvp.statsManager.initPlayer(p);
        p.sendMessage("Stats wurden erfolgreich initialisiert!");
    }
}
