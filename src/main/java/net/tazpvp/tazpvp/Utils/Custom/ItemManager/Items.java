package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Items {
    TEMAPLTE("temp", ChatColor.GREEN + "h", Material.DIRT, 100, 5, 'c');

    private String name;
    private String lore;
    private Material material;
    private int cost;
    private int damage;
    private char rarity; //c = common, u = uncommon, r = rare, e = epic, l = legendary

    Items(String name, String lore, Material material, int cost, int damage, char rarity) {
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.cost = cost;
        this.damage = damage;
        this.rarity = rarity;
    }

    public String getName() {
        return this.name;
    }

    public String getLore() {
        return this.lore;
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getCost() {
        return this.cost;
    }

    public int getDamage() {
        return this.damage;
    }

    public char getRarity() {
        return this.rarity;
    }
}
