package net.tazpvp.tazpvp.GUI.Template.OpenSlotTesting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;

public class OSGUI {
    private InventoryGUI gui;

    public OSGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 5 * 9, "TestGUI"));
        addItems();
        gui.open(p);
    }

    public void addItems() {
        gui.fill(0, 21, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));
        gui.fill(23, 45, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        gui.openSlot(22);
        gui.setReturnsItems(true);
        gui.setOnClickOpenSlot(e -> {
            Player p = (Player) e.getWhoClicked();
            p.sendMessage("Clicked open slot! " + e.getCurrentItem());
        });
    }
}
