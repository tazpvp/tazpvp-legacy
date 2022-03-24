package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    public static void giveItem(Player p, Items item, int amount) {
        String name = item.getName();
        String lore = item.getLore();
        List<String> loree = new ArrayList<>();
        ItemStack itemStack = item.getItem();
        NamespacedKey key = new NamespacedKey(Tazpvp.getInstance(), "custom");

        itemStack.setAmount(amount);

        ItemMeta meta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(itemStack.getType());

        meta.setDisplayName(name);
        loree.add(lore);
        meta.setLore(loree);
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);

        itemStack.setItemMeta(meta);
        p.getInventory().addItem(itemStack);
    }
}
