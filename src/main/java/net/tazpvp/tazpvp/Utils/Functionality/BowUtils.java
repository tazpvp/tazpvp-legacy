package net.tazpvp.tazpvp.Utils.Functionality;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BowUtils {
    public static void applyEnchant(Enchantment ench, int level, ItemStack item, boolean rebirthed) {
        for (org.bukkit.enchantments.Enchantment e : item.getEnchantments().keySet()) {
            item.removeEnchantment(e);
        }
        item.addUnsafeEnchantment(ench, level);
        if (rebirthed) item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
    }

    public static ItemStack getBow(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null && item.getType() == Material.BOW) {
                return item;
            }
        }
        return null;
    }

    public static void apllyEnchant(ItemStack[] items, Enchantment ench, int level) {
        for (ItemStack item : items) {
            for (org.bukkit.enchantments.Enchantment e : item.getEnchantments().keySet()) {
                item.removeEnchantment(e);
            }
            item.addUnsafeEnchantment(ench, level);
        }
    }

    public static ItemStack[] getArmor(Player p) {
        ItemStack[] armor = new ItemStack[4];
        for (int i = 0; i < 4; i++) {
            armor[i] = new ItemStack(Material.AIR);
        }
        for (int i = 0; i < 4; i++) {
            armor[i] = p.getInventory().getArmorContents()[i];
        }
        return armor;
    }

}
