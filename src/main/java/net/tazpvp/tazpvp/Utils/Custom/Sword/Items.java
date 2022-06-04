package net.tazpvp.tazpvp.Utils.Custom.Sword;

import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public enum Items {
    // common swords
    IvoDraKat(ChatColor.WHITE + "Ivory Dragon Katana", Material.WOODEN_SWORD, 1, 1, "C", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    WinRip(ChatColor.WHITE + "Wind Ripper", Material.WOODEN_SWORD, 1, 1, "C", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    BlaOfVig(ChatColor.WHITE + "Blade of Vigor", Material.WOODEN_SWORD, 1, 1, "C", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    DarShe(ChatColor.WHITE + "Darth Shear", Material.WOODEN_SWORD, 1, 1, "C", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    SteCut(ChatColor.WHITE + "Steel Cutlass", Material.WOODEN_SWORD, 1, 1, "C", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    BlaRaz(ChatColor.WHITE + "Blackiron Razor", Material.WOODEN_SWORD, 1, 1, "C", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    BalCen(ChatColor.WHITE + "Balisik Centurion", Material.WOODEN_SWORD, 1, 1, "C", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    // uncommon swords
    ThuShoSwo(ChatColor.GREEN + "Thunderfury Short Sword", Material.STONE_SWORD, 1, 1, "U", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    // rare swords
    InfGamDag(ChatColor.AQUA + "Inferno Gambit Dagger", Material.GOLDEN_SWORD, 1, 1, "R", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    // epic swords
    HeaDulKni(ChatColor.LIGHT_PURPLE + "Heavenly Dull Knife", Material.IRON_SWORD, 1, 1, "E", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Forged by urmom"),
    // legendary swords
    StiCla(ChatColor.YELLOW + "Stinger Claw", Material.DIAMOND_SWORD, 1, 1, "L", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 0, new HashMap<Enchantment, Integer>(){{
        put(Enchantment.DAMAGE_ALL, 1);
    }}, ChatColor.GRAY + "Forged by urmom");

    private final String name;
    private final String[] lore;
    private final Material material;
    private final int damage;
    private final int exp;
    private final String rarity; //c = common, u = uncommon, r = rare, e = epic, l = legendary
    private final double cooldown;
    private final NamespacedKey key;
    private final PersistentDataType type;
    private final double storedID;
    private final Map<Enchantment, Integer> enchantments;

    Items(String name, Material material, int damage, int exp, String rarity, double cooldown, NamespacedKey key, PersistentDataType type, double storedID, Map<Enchantment, Integer> enchantments, String... lore) {
        this.name = name;
        this.lore = lore;
        this.material = material;
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
