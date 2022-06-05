package net.tazpvp.tazpvp.Utils.Functionality;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BowUtils {
    public static void applyEnchant(Enchantment ench, int level, ItemStack item) {
        for (org.bukkit.enchantments.Enchantment e : item.getEnchantments().keySet()) {
            item.removeEnchantment(e);
        }
        item.addUnsafeEnchantment(ench, level);
    }

    public static ItemStack getBow(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null && item.getType() == Material.BOW) {
                return item;
            }
        }
        return null;
    }
}
