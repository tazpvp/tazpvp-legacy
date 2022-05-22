package net.tazpvp.tazpvp.Utils.Custom.Sword;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.PdcUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public enum Items {
    WOOGSWORD("oak sworden",  Material.WOODEN_SWORD, 100, 1, 1, "C", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>() {{

        }}, ChatColor.GREEN + "3"),
    UNCLESWORD(ChatColor.GREEN + "Uncle Sworden", Material.STONE_SWORD, 100, 2, 1, "U", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 1, new HashMap<Enchantment, Integer>() {{

    }}, ChatColor.GREEN + "very uncle"),
    GULKSWORD(ChatColor.YELLOW + "Gulk Sworden", Material.GOLDEN_SWORD, 100, 3, 1, "R", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 2, new HashMap<Enchantment, Integer>() {{

    }}, ChatColor.YELLOW + "gulk"),
    FILLETSWORD(ChatColor.LIGHT_PURPLE + "Fillet Sworden", Material.IRON_SWORD, 100, 4, 1, "E", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 3, new HashMap<Enchantment, Integer>() {{

    }}, ChatColor.LIGHT_PURPLE + "fillet deez nuts"),
    EXCALIBUR(ChatColor.GOLD + "Excalibur", Material.DIAMOND_SWORD, 100, 5, 1, "L", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 4, new HashMap<Enchantment, Integer>() {{
        put(Enchantment.DURABILITY, 1);
        put(Enchantment.KNOCKBACK, 2);
    }}, ChatColor.GOLD + "famoos sword", "", ChatColor.GRAY + "Knockback II");


    private final String name;
    private final String[] lore;
    private final Material material;
    private final int cost;
    private final int damage;
    private final int exp;
    private final String rarity; //c = common, u = uncommon, r = rare, e = epic, l = legendary
    private final double cooldown;
    private final NamespacedKey key;
    private final PersistentDataType type;
    private final double storedID;
    private final Map<Enchantment, Integer> enchantments;

    Items(String name, Material material, int cost, int damage, int exp, String rarity, double cooldown, NamespacedKey key, PersistentDataType type, double storedID, Map<Enchantment, Integer> enchantments, String... lore) {
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.cost = cost;
        this.damage = damage;
        this.exp = exp;
        this.rarity = rarity;
        this.cooldown = cooldown;
        this.key = key;
        this.type = type;
        this.storedID = storedID;
        this.enchantments = enchantments;
    }

    public String getName() {
        return this.name;
    }

    public String[] getLore() {
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

    public String getRarity() {
        return this.rarity;
    }

    public int getExp() {
        return this.exp;
    }

    public double getCooldown() {
    	return this.cooldown;
    }

    public NamespacedKey getKey() {
    	return this.key;
    }

    public PersistentDataType getType() {
    	return this.type;
    }

    public double getStoredID() {
    	return this.storedID;
    }

    public Map<Enchantment, Integer> getEnchantments() {
    	return this.enchantments;
    }
}
