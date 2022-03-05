package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.GUI.TestGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class GuiCMD {
    @CommandHook("gui")
    public void gui(Player p, String gui) {
        if (gui.equalsIgnoreCase("test")) {
            new TestGUI(p);
        }
    }
}
