package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import net.tazpvp.tazpvp.Utils.Custom.ItemManager.items.TempItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {
    public static final ArrayList<Item> items = new ArrayList<>();
    public static void init() {
        items.add(new TempItem());
    }

    public static void givePlayerItem(Player player, Items item, int count) {
        player.getInventory().addItem(createItem(item.getItem().getType(), count, item.getName()));
    }

    public static ItemStack createItem(Material item, int count, String name) {
        ItemStack itemStack = new ItemStack(item, count);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
