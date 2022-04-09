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
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.logging.Logger;

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
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                        Player d = (Player) ((EntityDamageByEntityEvent) e).getDamager();
                        Tazpvp.lastDamage.put(p.getUniqueId(), d.getUniqueId());
                    }
//                    if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
//                        NamespacedKey key = new NamespacedKey(Tazpvp.getInstance(), "custom");
//                        ItemStack is = p.getInventory().getItemInMainHand();
//                        ItemMeta itemMeta = is.hasItemMeta() ? is.getItemMeta() : Bukkit.getItemFactory().getItemMeta(is.getType());
//                        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
//                        int value = 0;
//                        if (container.has(key, PersistentDataType.INTEGER)) {
//                            value = container.get(key, PersistentDataType.INTEGER);
//                        }
//                        for (Items item : Items.values()) {
//                            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(item.getName())) {
//                                e.setDamage(item.getDamage());
//                            }
//                        }
//                    }
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

            if (Tazpvp.bounty.containsKey(d.getUniqueId())) {
                Tazpvp.statsManager.addMoney(p, Tazpvp.bounty.get(d.getUniqueId()));
                Tazpvp.bounty.remove(d.getUniqueId());
                p.sendMessage(ChatColor.GOLD + "You have received " + ChatColor.RED + Tazpvp.bounty.get(d.getUniqueId()) + ChatColor.GOLD + " for killing " + ChatColor.RED + d.getName());
            }

            Tazpvp.statsManager.addKills(d, 1);
        }

        Tazpvp.statsManager.addDeaths(p, 1);

        if (p.hasMetadata("LastDamager")) {
            p.removeMetadata("LastDamager", Tazpvp.getInstance());
        }

        for (int i = 0; i < 50; i++) {
            p.getWorld().playEffect(p.getLocation().add(0, 1, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
        }

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
