package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickUpEvent implements Listener {
    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (e.getItem().getItemStack().getType() == Material.PRISMARINE_SHARD) {
                p.getInventory().remove(Material.PRISMARINE_SHARD);
                Tazpvp.statsManager.addShards(p, 1);
            }
        }
    }
}
