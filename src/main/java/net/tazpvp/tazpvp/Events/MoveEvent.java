package net.tazpvp.tazpvp.Events;


import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Block b = new Location(e.getPlayer().getWorld(), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY() - 1, e.getPlayer().getLocation().getZ()).getBlock();
        if (b.getType() == Material.WATER) {
            new DeathEvent().DeathFunction(e.getPlayer(), null);
        }
    }
}
