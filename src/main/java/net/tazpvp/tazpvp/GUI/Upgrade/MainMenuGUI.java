package net.tazpvp.tazpvp.GUI.Upgrade;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;

public class MainMenuGUI {
    private InventoryGUI gui;
    public MainMenuGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "upgrade"));
        setitems();
        gui.open(p);
    }

    public void setitems(){
        gui.fill(1, 27, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
        ItemButton sword = ItemButton.create(new ItemStack(Material.DIAMOND_SWORD), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            //sword menu gui
        });
        gui.addButton(10, sword);
        ItemButton armor = ItemButton.create(new ItemStack(Material.DIAMOND_CHESTPLATE), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            //armor menu gui
        });
        gui.addButton(12, armor);
        //other buttons will go here. I'll add more later im tired. -ntdi
        // lol whoever commented this is a fucking dumbass ^
        // oh crap that was me
        // well this is awkward

        gui.update();
    }
}
