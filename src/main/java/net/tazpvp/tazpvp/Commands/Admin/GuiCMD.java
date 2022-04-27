package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.unused.FlameVilGUI;
import net.tazpvp.tazpvp.GUI.ShopGUI;
import net.tazpvp.tazpvp.GUI.TestGUI;
import net.tazpvp.tazpvp.GUI.Upgrade.MainMenuGUI;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class GuiCMD {
    @CommandHook("gui")
    public void gui(Player p, String gui) {
        if (gui.equalsIgnoreCase("test")) {
            new TestGUI(p);
        } else if (gui.equalsIgnoreCase("shop")) {
            new ShopGUI(p);
        } else if (gui.equalsIgnoreCase("upgrade")) {
            new MainMenuGUI(p);
        } else if (gui.equalsIgnoreCase("flame")) {
            new FlameVilGUI(p);
        }
    }
}
