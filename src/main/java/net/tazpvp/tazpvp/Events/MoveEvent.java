package net.tazpvp.tazpvp.Events;


import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Block b = new Location(e.getPlayer().getWorld(), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY() - 1, e.getPlayer().getLocation().getZ()).getBlock();
        if (b.getType() == Material.WATER && p.getGameMode() == GameMode.SURVIVAL) {
            new DeathEvent().DeathFunction(e.getPlayer(), null);
        }

        Location raidus = new Location(Bukkit.getWorld("arena"), -168, 48, -18);
        if (p.getLocation().distance(raidus) < 4) {
            p.setVelocity(new Vector(0, 2, -2));
        }
    }
}
