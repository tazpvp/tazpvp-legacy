package net.tazpvp.tazpvp.Utils.Custom.Items;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Items.items.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemManager {
    public static final ArrayList<Item> items = new ArrayList<>(Arrays.asList(
            new Agility(), new Extinguish()
    ));

    public static void givePlayerItem(Player player, Items item, int count) {
        player.getInventory().addItem(createItem(item.item, count, item.name, item.cID, item.lore));
    }

    public static ItemStack createItem(Material item, int count, String name, double cID, String... lore) {
        ItemStack itemStack = new ItemBuilder(item).setName(name).setLore(lore);
        itemStack.setAmount(count);
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "cid"), PersistentDataType.DOUBLE, cID);
        return itemStack;
    }
}
