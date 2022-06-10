package net.tazpvp.tazpvp.Events;


import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Block b = new Location(e.getPlayer().getWorld(), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY() - 1, e.getPlayer().getLocation().getZ()).getBlock();
        if (b.getType() == Material.WATER && p.getGameMode() == GameMode.SURVIVAL) {
            if (Tazpvp.lastDamage.containsKey(p.getUniqueId())) {
                new DeathEvent().DeathFunction(p, Bukkit.getPlayer(Tazpvp.lastDamage.get(p.getUniqueId())), false);
            } else {
                new DeathEvent().DeathFunction(p, null, false);
            }
            return;
        }

        Location raidus = new Location(Bukkit.getWorld("arena"), 0, 100, 24);
        if (p.getWorld().getName().equals("arena")) {
            if (p.getLocation().distance(raidus) < 5) {
                Launchpad(p); // Launchpad
            }
        }

//        if (Tazpvp.fallDamageImmune.contains(p.getUniqueId())) {
//            double y = p.getLocation().getY();
//            if (y > 98 && y < 99) {
//                Tazpvp.fallDamageImmune.remove(p.getUniqueId());
//                Location loc = new Location(p.getWorld(), p.getLocation().getX(), 96, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
//                p.setVelocity(new Vector(0, 0, 0));
//                p.setFallDistance(0);
//            }
//        }
    }

    public void Launchpad(Player p) {
        if (p.getGameMode().equals(GameMode.SURVIVAL)){
            Tazpvp.fallDamageImmune.add(p);
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);
            p.setVelocity(new Vector(0, 1.5, 3));
        }
    }
}
