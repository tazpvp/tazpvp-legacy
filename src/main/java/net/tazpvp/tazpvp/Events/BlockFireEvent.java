package net.tazpvp.tazpvp.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

public class BlockFireEvent implements Listener {
    @EventHandler
    public void onBlockFire(BlockBurnEvent e) {
        e.setCancelled(true);
    }
}
