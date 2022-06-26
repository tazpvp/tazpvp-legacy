package net.tazpvp.tazpvp.Utils.Custom.Sword;

import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateSword {
    public static void updateSword(Player p, ItemStack i) {
        if (i.hasItemMeta()) {
            Objects.requireNonNull(i.getItemMeta()).getPersistentDataContainer();
            if (i.getItemMeta().getPersistentDataContainer().has(PdcUtils.key, PersistentDataType.DOUBLE)) {
                ItemMeta meta = i.getItemMeta();
                PersistentDataContainer container = meta.getPersistentDataContainer();
                double id = container.get(PdcUtils.key, PersistentDataType.DOUBLE);

                List<ItemStack> items = new ArrayList<>();

                for (Items item : Items.values()) {
                    if (item.getStoredID() == id) {
                        p.getInventory().removeItemAnySlot(i);
                        items.add(ItemBuilder.maekItem(item));
                    }
                }

                for (ItemStack item : items) {
                    p.getInventory().addItem(item);
                }
            }
        }
    }
}
