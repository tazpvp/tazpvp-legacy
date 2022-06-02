package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class DuelCMD {
    @CommandHook("duel")
    public void duel(Player p, Player target) {
        Tazpvp.duelLogic.duelStart(p, target, null);
    }
}
