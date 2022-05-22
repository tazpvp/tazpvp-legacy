package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.GUI.EnderChests.PoorECGUI;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class inventoryCMD {
    @CommandHook("inv_poor")
    public void openPoor(Player p) {
        new PoorECGUI(p);
    }
    @CommandHook("inv_rich")
    public void openRich(Player p) {
        p.sendMessage("Coming to amongus near you!");
    }
}
