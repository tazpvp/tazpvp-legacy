package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class SkullCMD {
    @CommandHook("skull")
    public void skullCMD(Player p, Player target) {
        PlayerUtils.skullPlayer(p, target);
    }
}
