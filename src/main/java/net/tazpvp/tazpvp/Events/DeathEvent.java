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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathEvent implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (p.getHealth() - e.getFinalDamage() <= 0) {
                e.setCancelled(true);
                Location deadLoc = p.getLocation();

                if (e instanceof EntityDamageByEntityEvent) { // Code that requires a damager should go here
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                        Player d = ((Player) ((EntityDamageByEntityEvent) e).getDamager());

                        Bukkit.broadcastMessage(ChatColor.RED + d.getName() + ChatColor.GOLD + " has killed " + ChatColor.RED + p.getName());

                        Tazpvp.statsManager.addKills(d, 1);
                    } else { //this will run if a mob kills a player, etc. creeper boom
                        Bukkit.broadcastMessage(ChatColor.RED + p.getName() + ChatColor.GOLD + " has died to a " + e.getCause().toString().toLowerCase());
                    }
                }

                Tazpvp.statsManager.addDeaths(p, 1);

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
