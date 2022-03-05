package net.tazpvp.tazpvp.Events;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Block b = new Location(e.getPlayer().getWorld(), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY() - 1, e.getPlayer().getLocation().getZ()).getBlock();
        if (b.getType() == Material.WATER) {
            for (int i = 0; i < e.getPlayer().getHealth(); i++) {
                e.getPlayer().setHealth(e.getPlayer().getHealth() - 1);
            }
        }
    }
}
