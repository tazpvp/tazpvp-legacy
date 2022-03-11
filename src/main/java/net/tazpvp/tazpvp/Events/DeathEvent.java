package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Item;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.ItemManager;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Items;
import net.tazpvp.tazpvp.Utils.PlayerUtils;
import net.tazpvp.tazpvp.Utils.configUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nullable;

public class DeathEvent implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (p.getHealth() - e.getFinalDamage() <= 0) {
                e.setCancelled(true);
                if (e instanceof EntityDamageByEntityEvent) { // Code that requires a damager should4 go here
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                        DeathFunction(p, (Player) ((EntityDamageByEntityEvent) e).getDamager());
                    } else { //this will run if a mob kills a player, etc. creeper boom
                        DeathFunction(p, null);
                    }
                }
            } else {
                if (e instanceof EntityDamageByEntityEvent) {
                    NamespacedKey key = new NamespacedKey(Tazpvp.getInstance(), "custom");
                    ItemStack is = p.getInventory().getItemInMainHand();
                    ItemMeta itemMeta = is.hasItemMeta() ? is.getItemMeta() : Bukkit.getItemFactory().getItemMeta(is.getType());
                    PersistentDataContainer container = itemMeta.getPersistentDataContainer();
                    int value = 0;
                    if (container.has(key, PersistentDataType.INTEGER)) {
                        value = container.get(key, PersistentDataType.INTEGER);
                    }
                    for (Items item : Items.values()) {
                        if (value == 1) {
                            for(Item i : ItemManager.items) {
                                if (i.enumeration.equals(item)) {
                                    i.execute(p, p.getInventory().getItemInMainHand(), (EntityDamageByEntityEvent) e);
                                    Bukkit.getLogger().info("Executed " + i.enumeration.getName());
                                    return;
                                } else {
                                    Bukkit.getLogger().info("3 " + i.enumeration.getName());
                                }
                            }
                        } else {
                            Bukkit.getLogger().info("2 " + item.getName());
                        }
                    }
                } else {
                    Bukkit.getLogger().info("1");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        DeathFunction(e.getEntity(), null);
    }

    public void DeathFunction(Player p, @Nullable Player d) {
        if (d != null) { //code will run if a player kills another player
            Bukkit.broadcastMessage(ChatColor.RED + d.getName() + ChatColor.GOLD + " has killed " + ChatColor.RED + p.getName());

            Tazpvp.statsManager.addKills(d, 1);
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
