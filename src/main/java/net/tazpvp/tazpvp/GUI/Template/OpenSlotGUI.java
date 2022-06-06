package net.tazpvp.tazpvp.GUI.Template;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;

public class OpenSlotGUI {
    private InventoryGUI gui;

    public OpenSlotGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "TestGUI"));
        addItems();
        gui.open(p);
    }

    public void addItems(){
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        gui.openSlot(13);
        gui.setOnClickOpenSlot(this::checkItem);
        gui.setOnDragOpenSlot(this::checkItem);

        gui.update();
    }

    public void checkItem(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getAction().equals(InventoryAction.DROP_ALL_SLOT)) {
            p.sendMessage(e.getCursor().toString());
        };
    }
    public void checkItem(InventoryDragEvent e) {
        Player p = (Player) e.getWhoClicked();
        p.sendMessage(e.getCursor().toString());
    }
}
