package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Items.Item;
import net.tazpvp.tazpvp.Utils.Custom.Items.ItemManager;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class InteractEvent implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        runCustomItem(e.getPlayer());
    }
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        runCustomItem(e.getPlayer());
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        runCustomItem(e.getPlayer());
    }

    public void runCustomItem(Player p) {
        ItemStack inHand = p.getInventory().getItemInMainHand();
        if (inHand.hasItemMeta()) {
            ItemMeta meta = inHand.getItemMeta();
            assert meta != null;
            if (meta.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "cid"), PersistentDataType.DOUBLE)) {
                double cID = meta.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "cid"), PersistentDataType.DOUBLE);
                for (Item item : ItemManager.items) {
                    if (item.cID == cID) {
                        if (item.execute(p, inHand, cID)) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
