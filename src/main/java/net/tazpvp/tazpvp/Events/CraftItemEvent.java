package net.tazpvp.tazpvp.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CraftItemEvent implements Listener {
    @EventHandler
    public void onCraft(org.bukkit.event.inventory.CraftItemEvent e) {
        if (!e.getWhoClicked().isOp()) {
            e.setCancelled(true);
        }
    }
}
