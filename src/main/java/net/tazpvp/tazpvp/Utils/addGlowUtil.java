package net.tazpvp.tazpvp.Utils;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class addGlowUtil {
    public static ItemStack addGlow(ItemStack i) {
        if (i.getEnchantments().size() > 0) {
            Map<Enchantment, Integer> e = i.getEnchantments();
            List<String> lore = i.getItemMeta().getLore();
            for (Enchantment ench : e.keySet()) {
                lore.add(ChatColor.GRAY + ench.getName() + " " + e.get(ench));
            }
            i.addUnsafeEnchantment(Enchantment.LURE, 1);
            ItemMeta meta = i.getItemMeta();
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            i.setItemMeta(meta);
            return i;
        } else {
            i.addUnsafeEnchantment(Enchantment.LURE, 1);
            ItemMeta meta = i.getItemMeta();
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            i.setItemMeta(meta);
            return i;
        }
    }
}
