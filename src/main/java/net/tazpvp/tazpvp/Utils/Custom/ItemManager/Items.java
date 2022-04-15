package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Items {
    WOOGSWORD("oak sworden", ChatColor.GREEN + "3", Material.WOODEN_SWORD, 100, 10, 1, 'c', 0.5);

    private String name;
    private String lore;
    private Material material;
    private int cost;
    private int damage;
    private int exp;
    private char rarity; //c = common, u = uncommon, r = rare, e = epic, l = legendary
    private double cooldown;

    Items(String name, String lore, Material material, int cost, int damage, int exp, char rarity, double cooldown) {
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.cost = cost;
        this.damage = damage;
        this.exp = exp;
        this.rarity = rarity;
        this.cooldown = cooldown;
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

    public int getExp() {
        return this.exp;
    }

    public double getCooldown() {
    	return this.cooldown;
    }
}
