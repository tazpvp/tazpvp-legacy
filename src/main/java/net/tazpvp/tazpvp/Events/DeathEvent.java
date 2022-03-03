package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.PlayerUtils;
import net.tazpvp.tazpvp.Utils.configUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathEvent implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player p && e.getDamager() instanceof Player d) {
            if (p.getHealth() - e.getFinalDamage() <= 0) {
                e.setCancelled(true);
                Location deadLoc = p.getLocation();

                Bukkit.broadcastMessage(ChatColor.RED + d.getName() + ChatColor.GOLD + " has killed " + ChatColor.RED + p.getName());

                //TODO: Add stats and stuff

                p.setGameMode(GameMode.SPECTATOR);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        PlayerUtils.healPlayer(p);

                        p.teleport(configUtils.spawn);
                        p.setGameMode(GameMode.SURVIVAL);
                    }
                }.runTaskLater(Tazpvp.getInstance(), 60L);
            }
        }
    }
}
