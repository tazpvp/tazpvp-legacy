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
    WooKni(Material.STONE_SWORD, ChatColor.WHITE + "Kitchen Knife", ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 1, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 4); }} ),
    SteCut(Material.STONE_SWORD, ChatColor.WHITE + "Steel Cutlass", ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 2, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 5); }} ),
    KeeDag(Material.STONE_SWORD, ChatColor.WHITE + "Keen Dagger", ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 3, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 1); put(Enchantment.DAMAGE_ALL, 4); }} ),
    PriSha(Material.STONE_SWORD, ChatColor.WHITE + "Prison Shank", ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 4, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 4); }}),
    ShaCle(Material.STONE_SWORD, ChatColor.WHITE + "Sharp Cleaver", ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON", 5, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 1); put(Enchantment.DAMAGE_ALL, 5); }} ),
    // uncommon swords
    HeaDulKni(Material.IRON_SWORD, ChatColor.GREEN + "Heavenly Dull Knife", ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 6, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); put(Enchantment.DAMAGE_ALL, 2);}}),
    LigSteMac(Material.IRON_SWORD, ChatColor.GREEN + "Light Steel Machete", ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 7, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 3); }}),
    GolSwiBla(Material.IRON_SWORD, ChatColor.GREEN + "Iron Switchblade", ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 8, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 3); }}),
    DarShe(Material.IRON_SWORD, ChatColor.GREEN + "Darth Shear", ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 9, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); put(Enchantment.DAMAGE_ALL, 2);}}),
    ThoSti(Material.IRON_SWORD, ChatColor.GREEN + "Thorn Striker", ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 10, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 3); put(Enchantment.FIRE_ASPECT, 1); put(Enchantment.DAMAGE_ALL, 1);}}),
    CopJac(Material.IRON_SWORD, ChatColor.GREEN + "Copper Jackknife", ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON", 11, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 3); }}),
    // rare swords
    WinRip(Material.GOLDEN_SWORD, ChatColor.AQUA + "Wind Ripper", ChatColor.AQUA + "" + ChatColor.BOLD + "RARE", 12, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 3); put(Enchantment.DAMAGE_ALL, 2); }}),
    WitBla(Material.GOLDEN_SWORD, ChatColor.AQUA + "Wither Blade", ChatColor.AQUA + "" + ChatColor.BOLD + "RARE", 13, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 3); }}),
    ThuShoSwo(Material.GOLDEN_SWORD, ChatColor.AQUA + "Thunderfury Short Sword", ChatColor.AQUA + "" + ChatColor.BOLD + "RARE", 14, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 4); }}),
    StiCla(Material.GOLDEN_SWORD, ChatColor.AQUA + "Stinger Claw", ChatColor.AQUA + "" + ChatColor.BOLD + "RARE", 15, new HashMap<Enchantment, Integer>()  {{ put(Enchantment.SWEEPING_EDGE, 3); put(Enchantment.DAMAGE_ALL, 3); }}),
    // epic swords
    BlaRaz(Material.DIAMOND_SWORD, ChatColor.LIGHT_PURPLE + "Blackiron Razor", ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "EPIC", 16, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); put(Enchantment.DAMAGE_ALL, 1); }}),
    BlaOfVig(Material.DIAMOND_SWORD, ChatColor.LIGHT_PURPLE + "Blade of Vigor", ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "EPIC", 17, new HashMap<Enchantment, Integer>() {{put(Enchantment.SWEEPING_EDGE, 2); put(Enchantment.DAMAGE_ALL, 2); }}),
    InfGamDag(Material.DIAMOND_SWORD, ChatColor.LIGHT_PURPLE + "Inferno Gambit Dagger", ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "EPIC", 18, new HashMap<Enchantment, Integer>() {{ put(Enchantment.FIRE_ASPECT, 1); }}),
    // legendary swords
    IvoDraKat(Material.NETHERITE_SWORD, ChatColor.GOLD + "Ivory Dragon Katana", ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY", 19, new HashMap<Enchantment, Integer>() {{ put(Enchantment.FIRE_ASPECT, 1); }}),
    BalCen(Material.NETHERITE_SWORD, ChatColor.GOLD + "Balisik Centurion", ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY", 20, new HashMap<Enchantment, Integer>() {{ put(Enchantment.SWEEPING_EDGE, 2); }}),
    AtoEdgSwo(Material.NETHERITE_SWORD, ChatColor.GOLD + "Atomic Edge", ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY", 21, new HashMap<Enchantment, Integer>() {{ put(Enchantment.DAMAGE_ALL, 1); }} )
    ;

    private final String name;
    private final Material material;
    private final String rarity; //c = common, u = uncommon, r = rare, e = epic, l = legendary
    private final NamespacedKey key;
    private final PersistentDataType type;
    private final double storedID;
    private final Map<Enchantment, Integer> enchantments;

    Items(Material material, String name, String rarity, double storedID, Map<Enchantment, Integer> enchantments) {
        this.name = name;
        this.material = material;
        this.rarity = rarity;
        this.key = PdcUtils.key;
        this.type = PersistentDataType.DOUBLE;
        this.storedID = storedID;
        this.enchantments = enchantments;
    }

    public String getName() {
        return this.name;
    }
    public Material getMaterial() {
        return this.material;
    }
    public String getRarity() {
        return this.rarity;
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
