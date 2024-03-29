package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.GUI.MainMenu.MainGUI;
import net.tazpvp.tazpvp.GUI.NPCGui.BowGUI;
import net.tazpvp.tazpvp.GUI.NPCGui.PerkGUI;
import net.tazpvp.tazpvp.GUI.NPCGui.ShopGUI;
import net.tazpvp.tazpvp.GUI.Template.ClickInvGUI;
import net.tazpvp.tazpvp.GUI.Template.MutliGuiPrototype.TestOne;
import net.tazpvp.tazpvp.GUI.Template.OpenSlotGUI;
import net.tazpvp.tazpvp.GUI.Template.OpenSlotTesting.OSGUI;
import net.tazpvp.tazpvp.GUI.Template.TestGUI;
import net.tazpvp.tazpvp.unused.FlameVilGUI;
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
        } else if (gui.equalsIgnoreCase("perk")) {
            new PerkGUI(p);
        } else if (gui.equalsIgnoreCase("testOne")) {
            new TestOne(p);
        } else if (gui.equalsIgnoreCase("bow")) {
            new BowGUI(p);
        } else if (gui.equalsIgnoreCase("ops")) {
            new OSGUI(p);
        } else if (gui.equalsIgnoreCase("ci")) {
            new ClickInvGUI(p);
        }
    }
}
