package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Utils.Custom.Items.ItemManager;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class TrollCMD implements CommandListener {
    @CommandHook("demo")
    public void demoCommand(Player p, Player target) {
        target.showDemoScreen();
    }

    @CommandHook("stick")
    public void stickCommand(Player p) {
         ItemManager.givePlayerItem(p, Items.JOESTICK, 1);
    }
}
