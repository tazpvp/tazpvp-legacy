package net.tazpvp.tazpvp.Utils.Functionality;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class RecycleUtils {
    /**
     * Checks if the given ItemStack is a valid sword and recycle's it's rewards.
     * @param p The Player.
     * @param i The ItemStack.
     */
    public static void recycleSword(Player p, ItemStack i) {
        if (isValidSword(i)) {
            p.getInventory().remove(i);
            Tazpvp.statsManager.addShards(p, 3);
            Tazpvp.statsManager.addCoins(p, 60);
        }
    }

    /**
     * Checks if passed ItemStack is a Sword in the Enum.
     * @param i The ItemStack
     * @return True if the ItemStack is a Custom Sword, else false.
     */
    public static boolean isValidSword(ItemStack i) {
        if (i.hasItemMeta()) {
            NamespacedKey key = PdcUtils.key;
            ItemMeta meta = i.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            return container.has(key, PersistentDataType.DOUBLE);
        }
        return false;
    }
}
