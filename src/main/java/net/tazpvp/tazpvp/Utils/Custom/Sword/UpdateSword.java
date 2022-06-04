package net.tazpvp.tazpvp.Utils.Custom.Sword;

import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class UpdateSword {
    public static void updateSword(Player p, ItemStack i) {
        if (i.hasItemMeta()) {
            i.getItemMeta().getPersistentDataContainer().has(PdcUtils.key, PersistentDataType.DOUBLE);
            double id = i.getItemMeta().getPersistentDataContainer().get(PdcUtils.key, PersistentDataType.DOUBLE);

            for (Items item : Items.values()) {
                if (item.getStoredID() == id) {
                    p.getInventory().removeItem(i);
                    p.getInventory().addItem(ItemBuilder.maekItem(item));
                }
            }
        }
    }
}
