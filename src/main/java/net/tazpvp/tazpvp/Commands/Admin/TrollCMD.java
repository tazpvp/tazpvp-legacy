package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class TrollCMD implements CommandListener {
    @CommandHook("demo")
    public void demoCommand(Player p, Player target) {
        target.showDemoScreen();
    }
}
