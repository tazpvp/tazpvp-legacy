package net.tazpvp.tazpvp.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import static org.bukkit.inventory.ItemFlag.HIDE_ATTRIBUTES;

public class MainGUI {
    private InventoryGUI gui;

    public MainGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.RED + "Taz" + ChatColor.GOLD + "spree"));
        makeItems();
        gui.open(p);
    }

    public void makeItems() {
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        ItemStack sword = new ItemBuilder(Material.WOODEN_SWORD).setName(ChatColor.RED + "Sword").setLore(ChatColor.GOLD + "Click to open the sword menu");
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.removeItemFlags(HIDE_ATTRIBUTES);
        sword.setItemMeta(swordMeta);

        ItemButton swordButton = ItemButton.create(sword, e -> {
            e.getWhoClicked().closeInventory();
            //open sword gui ex: new SwordGUI(p);
        });

        gui.update();
    }
}
