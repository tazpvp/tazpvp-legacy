package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Utils.Custom.Items.Item;
import net.tazpvp.tazpvp.Utils.Custom.Items.ItemManager;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            p.getInventory().getItemInMainHand();
            if (p.getInventory().getItemInMainHand().getType() != Material.FISHING_ROD) {
                for (Items items : Items.values()) {
                    if (items.display.equals(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName())) {
                        for (Item i : ItemManager.items) {
                            if (i.enumeration.equals(items)) {
                                i.execute(p, p.getInventory().getItemInMainHand());
                                return;
                            }
                        }
                        return;
                    }
                }
            }
        }
    }
}
