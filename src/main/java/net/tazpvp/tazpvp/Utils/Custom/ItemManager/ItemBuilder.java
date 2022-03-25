package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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
        Material material = item.getMaterial();

        //NamespacedKey key = new NamespacedKey(Tazpvp.getInstance(), "custom");

        //meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);

        ItemStack itemz = new redempt.redlib.itemutils.ItemBuilder(material).setName(name).setLore(lore);

        p.getInventory().addItem(itemz);
    }
}
