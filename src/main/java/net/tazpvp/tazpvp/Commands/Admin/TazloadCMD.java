package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Duels.DW;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.util.List;
import java.util.UUID;

public class TazloadCMD {
    @CommandHook("tazload")
    public void onTazLoad(Player p) {
        Bukkit.broadcastMessage("---\nserver restarting in 60s LOL\n---");
        for (List<UUID> l : Tazpvp.duelLogic.duels.keySet()) {
            Tazpvp.duelLogic.duelEnd(Bukkit.getPlayer(l.get(0)));
        }
        Tazpvp.AllowBlocks = false;
        Tazpvp.isRestarting = true;
    }
}
