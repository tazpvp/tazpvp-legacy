package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class initCMD  {

    @CommandHook("init")
    public void initPlayer(Player p){
        Tazpvp.statsManager.initPlayer(p);
        p.sendMessage("Stats wurden erfolgreich initialisiert!");
    }
}
