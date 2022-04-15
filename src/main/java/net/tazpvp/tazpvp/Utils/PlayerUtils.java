package net.tazpvp.tazpvp.Utils;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

public class PlayerUtils {
    public static void healPlayer(Player p) {
        p.setHealth(getMaxHealth(p));
        p.setFoodLevel(20);
        p.setExhaustion(0.0f);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
    }

    public static void setMaxHealth(Player p, double maxHealth) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
    }
    public static double getMaxHealth(Player p) {
        return p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    }

    public static void kitPlayer(Player p) {
        p.getInventory().clear();

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 16);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 5);
        ItemStack block = new ItemStack(Material.OAK_PLANKS, 64);
        ItemStack arrow = new ItemStack(Material.ARROW, 32);

        PlayerInventory inv = p.getInventory();
        inv.setHelmet(helmet);
        inv.setChestplate(chestplate);
        inv.setLeggings(leggings);
        inv.setBoots(boots);
        inv.setItem(0, sword);
        inv.setItem(1, bow);
        inv.setItem(2, pickaxe);
        inv.setItem(3, gapple);
        inv.setItem(4, steak);
        inv.setItem(5, block);
        inv.setItem(8, arrow);
    }
}
