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
        createButton();
        gui.open(p);
    }

    public void createButton() {
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        ItemStack pickaxe = new ItemBuilder(Material.GOLDEN_PICKAXE).setName(ChatColor.DARK_AQUA + "Upgrades").setLore(ChatColor.GRAY + "Pickaxe upgrades.");
        ItemStack upgrade = new ItemBuilder(Material.CHEST_MINECART).setName(ChatColor.DARK_AQUA + "Trade").setLore(ChatColor.GRAY + "Click to sell your ores.");

        ItemButton button = ItemButton.create(pickaxe, e -> {
            e.getWhoClicked().closeInventory();
            //open sword gui ex: new SwordGUI(p);
        });

        gui.addButton(11, button);
        gui.addButton(15, button);
        gui.update();
    }
}
