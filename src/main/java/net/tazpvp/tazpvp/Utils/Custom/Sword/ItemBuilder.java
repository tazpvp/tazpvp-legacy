package net.tazpvp.tazpvp.Utils.Custom.Sword;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    public static void giveItem(Player p, Items item, int amount) {
        String name = item.getName();
        String lore[] = item.getLore();
        List<String> loree = new ArrayList<>();
        Material material = item.getMaterial();

        ItemStack itemz = new redempt.redlib.itemutils.ItemBuilder(material).setName(name).setLore(lore);
        ItemMeta meta = itemz.getItemMeta();
        meta.getPersistentDataContainer().set(item.getKey(), item.getType(), item.getStoredID());

        itemz.setItemMeta(meta);

        for (int i = 0; i < amount; i++) {
            p.getInventory().addItem(itemz);
        }
    }
}
