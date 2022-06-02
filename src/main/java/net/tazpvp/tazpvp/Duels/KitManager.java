package net.tazpvp.tazpvp.Duels;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.itemutils.ItemBuilder;

public enum KitManager {

    SWORD(new ItemBuilder(Material.DIAMOND_HELMET).unbreakable(),
            new ItemBuilder(Material.DIAMOND_CHESTPLATE).unbreakable(),
            new ItemBuilder(Material.DIAMOND_LEGGINGS).unbreakable(),
            new ItemBuilder(Material.DIAMOND_BOOTS).unbreakable(),
            new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_SWORD).unbreakable(),
                new ItemBuilder(Material.GOLDEN_APPLE, 8)
    });

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack[] contents;

    KitManager(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack[] contents) {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
        this.contents = contents;
    }

    public void giveKit(Player p) {
        Inventory inv = p.getInventory();
        inv.clear();

        inv.setContents(contents);
        inv.setItem(36, boots);
        inv.setItem(37, leggings);
        inv.setItem(38, chestplate);
        inv.setItem(39, helmet);
    }
}
