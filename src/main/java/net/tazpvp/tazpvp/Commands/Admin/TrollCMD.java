package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class TrollCMD {
    @CommandHook("demo")
    public void demoCommand(Player p, Player target) {
        target.showDemoScreen();
    }
}
