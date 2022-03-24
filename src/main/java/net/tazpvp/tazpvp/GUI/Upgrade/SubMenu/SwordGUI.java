package net.tazpvp.tazpvp.GUI.Upgrade.SubMenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;

public class SwordGUI {
    private InventoryGUI gui;
    public SwordGUI(Player p){
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "upgrade"));
        setitems();
        gui.open(p);
    }

    public void setitems(){
        gui.fill(1, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        gui.update();
    }
}
