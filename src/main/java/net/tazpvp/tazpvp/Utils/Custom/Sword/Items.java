package net.tazpvp.tazpvp.Utils.Custom.Sword;

import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public enum Items {
    // common swords
    WooKni(ChatColor.WHITE + "Kitchen Knife", Material.STONE_SWORD, 6, ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 1, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Simple but effective."),
    SteCut(ChatColor.WHITE + "Steel Cutlass", Material.STONE_SWORD, 6, ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 2, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 1); }}, ChatColor.GRAY + "Looking for something durable?."),
    KeeDag(ChatColor.WHITE + "Keen Dagger", Material.STONE_SWORD, 6, ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 3, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 1); }}, ChatColor.GRAY + "Basic but precise."),
    PriSha(ChatColor.WHITE + "Prison Shank", Material.STONE_SWORD, 5, ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 4, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Usually utilized by gang members."),
    ShaCle(ChatColor.WHITE + "Sharp Cleaver", Material.STONE_SWORD, 7, ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 5, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 1); }}, ChatColor.GRAY + "Best of the worst."),

    // uncommon swords
    HeaDulKni(ChatColor.GREEN + "Heavenly Dull Knife", Material.IRON_SWORD, 5, ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 6, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); }}, ChatColor.GRAY + "feels amazing in your hand but is quite dull."),
    LigSteMac(ChatColor.GREEN + "Light Steel Machete", Material.IRON_SWORD, 7, ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 7, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Easy to handle"),
    GolSwiBla(ChatColor.GREEN + "Iron Switchblade", Material.IRON_SWORD, 7, ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 8, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "Speedy and thin."),
    DarShe(ChatColor.GREEN + "Darth Shear", Material.IRON_SWORD, 7, ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 9, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); }}, ChatColor.GRAY + "Am relic of the dark side."),
    ThoSti(ChatColor.GREEN + "Thorn Striker", Material.IRON_SWORD, 7, ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 10, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 3); put(Enchantment.FIRE_ASPECT, 1);}}, ChatColor.GRAY + "Those stricken feel unbearable pain."),
    CopJac(ChatColor.GREEN + "Copper Jackknife", Material.IRON_SWORD, 7, ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 11, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "A tool for heavy duty jobs."),

    // rare swords
    WinRip(ChatColor.AQUA + "Wind Ripper", Material.GOLDEN_SWORD, 8, ChatColor.AQUA + "" + ChatColor.BOLD + "RARE", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 12, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); }}, ChatColor.GRAY + "Packed inside is the force of a hurricane."),
    WitBla(ChatColor.AQUA + "Withering Blade", Material.GOLDEN_SWORD, 7, ChatColor.AQUA + "" + ChatColor.BOLD + "RARE", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 13, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); }}, ChatColor.GRAY + "The effects of such blade can be toxic."),
    ThuShoSwo(ChatColor.AQUA + "Thunderfury Short Sword", Material.GOLDEN_SWORD, 7, ChatColor.AQUA + "" + ChatColor.BOLD + "RARE", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 14, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 3); put(Enchantment.KNOCKBACK, 1);}}, ChatColor.GRAY + "Only a master can wield such power."),
    StiCla(ChatColor.AQUA + "Stinger Claw", Material.GOLDEN_SWORD, 6, ChatColor.AQUA + "" + ChatColor.BOLD + "RARE", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 15, new HashMap<Enchantment, Integer>()  {{ put(Enchantment.SWEEPING_EDGE, 3); }}, ChatColor.GRAY + "Deadly slashes."),

    // epic swords
    BlaRaz(ChatColor.LIGHT_PURPLE + "Blackiron Razor", Material.DIAMOND_SWORD, 8, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "EPIC", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 16, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); }}, ChatColor.GRAY + "Forged from the Blackiron of the Taznanium mines."),
    BlaOfVig(ChatColor.LIGHT_PURPLE + "Blade of Vigor", Material.DIAMOND_SWORD, 8, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "EPIC", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 17, new HashMap<Enchantment, Integer>() {{put(Enchantment.KNOCKBACK, 2);}}, ChatColor.GRAY + "Power is amplified with this ancient treasure"),
    InfGamDag(ChatColor.LIGHT_PURPLE + "Inferno Gambit Dagger", Material.DIAMOND_SWORD, 8, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "EPIC", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 18, new HashMap<Enchantment, Integer>() {{ put(Enchantment.FIRE_ASPECT, 2); }}, ChatColor.GRAY + "Once used; the enemy will surely die; right?"),

    // legendary swords
    IvoDraKat(ChatColor.GOLD + "Ivory Dragon Katana", Material.NETHERITE_SWORD, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 19, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 3); }}, ChatColor.GRAY + "Made from the claws of the Southern Dragons."),
    BalCen(ChatColor.GOLD + "Balisik Centurion", Material.NETHERITE_SWORD, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 20, new HashMap<Enchantment, Integer>() {{ put(Enchantment.FIRE_ASPECT, 2); }}, ChatColor.GRAY + "Sacred blade of the Fang clan."),
    AtoEdgSwo(ChatColor.GOLD + "Atomic Edge", Material.NETHERITE_SWORD, 10, ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY", 0.625, PdcUtils.key, PersistentDataType.DOUBLE, 21, new HashMap<Enchantment, Integer>(), ChatColor.GRAY + "This sword will split anything in two.")
    ;

    private final String name;
    private final String[] lore;
    private final Material material;
    private final int damage;
    private final String rarity; //c = common, u = uncommon, r = rare, e = epic, l = legendary
    private final double cooldown;
    private final NamespacedKey key;
    private final PersistentDataType type;
    private final double storedID;
    private final Map<Enchantment, Integer> enchantments;

    Items(String name, Material material, int damage, String rarity, double cooldown, NamespacedKey key, PersistentDataType type, double storedID, Map<Enchantment, Integer> enchantments, String... lore) {
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.damage = damage;
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
