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

public class MineGUI {
    private InventoryGUI gui;

    public MineGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.RED + "Miner"));
        addItems();
        gui.open(p);
    }

    public void createButton(ItemStack item, int slot, String name, String description) {
        ItemBuilder b = new ItemBuilder(item).setName(name).setLore(description);
        ItemButton button = ItemButton.create(b, e -> {
            e.getWhoClicked().closeInventory();
            //open sword gui ex: new SwordGUI(p);
        });
        gui.addButton(slot, button);
    }
    public void addItems () {
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        createButton(new ItemStack(Material.GOLDEN_PICKAXE), 11, ChatColor.DARK_AQUA + "Upgrades", ChatColor.GRAY + "Pickaxe upgrades.");
        createButton(new ItemStack(Material.CHEST_MINECART), 15, ChatColor.DARK_AQUA + "Trade Ores", ChatColor.GRAY + "Click to sell your ores.");
        gui.update();
    }
}
