package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Utils.PlayerUtils;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class KitCMD implements CommandListener {
    @CommandHook("kit")
    public void kitCMD(Player p, Player target) {
        PlayerUtils.kitPlayer(target);
    }
}
