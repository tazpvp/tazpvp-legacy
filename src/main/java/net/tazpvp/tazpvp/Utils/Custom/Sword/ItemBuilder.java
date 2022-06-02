package net.tazpvp.tazpvp.Utils.Custom.Sword;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    public static void giveItem(Player p, Items item, int amount) {
        String name = item.getName();
        String lore[] = item.getLore();
        List<String> loree = new ArrayList<>();
        Material material = item.getMaterial();

        loree.add(ChatColor.RED + "" + ChatColor.BOLD + "Damage: " + item.getDamage());

        ItemStack itemz = new redempt.redlib.itemutils.ItemBuilder(material).setName(name).setLore(lore);
        ItemMeta meta = itemz.getItemMeta();
        meta.getPersistentDataContainer().set(item.getKey(), item.getType(), item.getStoredID());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemz.setItemMeta(meta);

        Map<Enchantment, Integer> enchantments = item.getEnchantments();
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            itemz.addUnsafeEnchantment(entry.getKey(), entry.getValue());
        }

        for (int i = 0; i < amount; i++) {
            p.getInventory().addItem(itemz);
        }
    }
}
