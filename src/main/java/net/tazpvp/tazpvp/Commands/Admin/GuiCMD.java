package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.GUI.EnderChests.EnderChestPoorGUI;
import net.tazpvp.tazpvp.GUI.MainMenu.MainGUI;
import net.tazpvp.tazpvp.GUI.MutliGuiPrototype.TestOne;
import net.tazpvp.tazpvp.GUI.Template.OpenSlotGUI;
import net.tazpvp.tazpvp.unused.FlameVilGUI;
import net.tazpvp.tazpvp.GUI.NPCGui.ShopGUI;
import net.tazpvp.tazpvp.GUI.Template.TestGUI;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class GuiCMD implements CommandListener {
    @CommandHook("gui")
    public void gui(Player p, String gui) {
        if (gui.equalsIgnoreCase("test")) {
            new TestGUI(p);
        } else if (gui.equalsIgnoreCase("shop")) {
            new ShopGUI(p);
        } else if (gui.equalsIgnoreCase("main")) {
            new MainGUI(p);
        } else if (gui.equalsIgnoreCase("flame")) {
            new FlameVilGUI(p);
        } else if (gui.equalsIgnoreCase("os")) {
            new OpenSlotGUI(p);
        } else if (gui.equalsIgnoreCase("ecp")) {
            new EnderChestPoorGUI(p);
        } else if (gui.equalsIgnoreCase("testOne")) {
            new TestOne(p);
        }
    }
}
