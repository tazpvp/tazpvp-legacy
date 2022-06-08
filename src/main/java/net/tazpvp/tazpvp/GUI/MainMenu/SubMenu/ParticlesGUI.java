package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import redempt.redlib.inventorygui.InventoryGUI;

public class ParticlesGUI {
    private InventoryGUI gui;

    public ParticlesGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 3 * 9, "Particles"));
        addItems();
        gui.open(p);
    }

    public void addItems() {
        gui.update();
    }
}
