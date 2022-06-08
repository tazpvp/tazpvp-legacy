package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Managers.CombatLogManager;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import net.tazpvp.tazpvp.Utils.Functionality.DeathUtils;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.*;
import org.bukkit.entity.Arrow;
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
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;
import java.util.WeakHashMap;

import static net.tazpvp.tazpvp.Utils.Functionality.PerkUtils.*;
import static net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils.setHealth;

public class DeathEvent implements Listener {
    public WeakHashMap<Player, Long> cooldowns = new WeakHashMap<Player, Long>();

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (p.getWorld().getName().equals("arena")) {
                if (p.getLocation().distance(configUtils.spawn) < 25) {
                    e.setCancelled(true);
                    return;
                }
            }
            double fd = 0;
            if (e instanceof EntityDamageByEntityEvent) {
                if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player d) {
                    ItemStack item = d.getInventory().getItemInMainHand();
                    if (item.hasItemMeta()) {
                        NamespacedKey key = PdcUtils.key;
                        ItemMeta meta = item.getItemMeta();
                        PersistentDataContainer container = meta.getPersistentDataContainer();

                        if (container.has(key, PersistentDataType.DOUBLE)){
                            fd = container.get(key, PersistentDataType.DOUBLE);
                        }
                    }
                } else {
                    fd = e.getFinalDamage();
                }
            } else {
                fd = e.getFinalDamage();
            }
            if (fd == 0) {
                fd = e.getFinalDamage();
            }
            if (p.getHealth() - fd <= 0) {
                e.setCancelled(true);
                if (e instanceof EntityDamageByEntityEvent) { // Code that requires a damager should4 go here
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player) {
                        if (Tazpvp.duelLogic.isInDuel(p)) {
                            Tazpvp.duelLogic.duelEnd(p);
                            e.setCancelled(true);
                            return;
                        }
                        DeathFunction(p, (Player) ((EntityDamageByEntityEvent) e).getDamager(), true);
                    } else if (((EntityDamageByEntityEvent) e).getDamager() instanceof Arrow a){ // arrowwwwwww
                        if (a.getShooter() instanceof Player) {
                            if (Tazpvp.duelLogic.isInDuel(p)) {
                                Tazpvp.duelLogic.duelEnd(p);
                                e.setCancelled(true);
                                return;
                            }
                            DeathFunction(p, (Player) a.getShooter(), true);
                        }
                    } else { //this will run if a mob kills a player, etc. creeper boom
                        DeathFunction(p, null, false);
                    }
                } else { // this will run if a player dies with no damager player
                    DeathFunction(p, null, false);
                }
            } else {
                if (e instanceof EntityDamageByEntityEvent) {
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player d) {
                        Tazpvp.lastDamage.put(p.getUniqueId(), d.getUniqueId());

                        CombatLogManager.combatLog.put(p.getUniqueId(), 10L);
                        CombatLogManager.combatLog.put(d.getUniqueId(), 10L);

                        ItemStack item = d.getInventory().getItemInMainHand();
                        if (item.hasItemMeta()) {
                            NamespacedKey key = PdcUtils.key;
                            ItemMeta meta = item.getItemMeta();
                            PersistentDataContainer container = meta.getPersistentDataContainer();

                            if (container.has(key, PersistentDataType.DOUBLE)){
                                double foundValue = container.get(key, PersistentDataType.DOUBLE);
                                itemDamage(foundValue, (EntityDamageByEntityEvent) e);
                                return;
                            }
                        }

                        customItems((EntityDamageByEntityEvent) e);

                    }
                }
            }
        }
    }

    public void customItems(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player d) {
            ItemStack item = d.getInventory().getItemInMainHand();
            // do stuff
        }
    }

    public void itemDamage(Double id, EntityDamageByEntityEvent e) {
        for (Items item : Items.values()) {
            if (item.getStoredID() == id) {
                Player d = (Player) e.getDamager();
                double cooldownTime = item.getCooldown(); // Get number of seconds from wherever you want
                if(cooldowns.containsKey(d)) {
                    double secondsLeft = (((double)cooldowns.get(d)/1000)+cooldownTime) - ((double)System.currentTimeMillis()/1000);
                    if(secondsLeft>0) {
                        // Still cooling down
                        e.setDamage((double) item.getDamage()/4);
                        //d.sendTitle(ChatColor.RED + "Your weapon is recharging", null, 10, 20, 10);
                        cooldowns.put(d, System.currentTimeMillis());
                        return;
                    }
                }
                // No cooldown found or cooldown has expired, save new cooldown
                cooldowns.put(d, System.currentTimeMillis());
                // Do Command Here
                e.setDamage(item.getDamage());
                if (d.getVelocity().getY() < 0) {
                    e.setDamage(item.getDamage() * 1.5F);
                }

            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            DeathFunction(e.getEntity(), e.getEntity().getKiller(), true);
            e.setDeathMessage(null);
        } else {
            DeathFunction(e.getEntity(), null, true);
            e.setDeathMessage(null);
        }
    }

    public void DeathFunction(Player p, @Nullable Player d, boolean dropHead) {
        DeathUtils deathUtils = new DeathUtils(p, d);
        if (d != null) { //code will run if a player kills another player
            if (dropHead) {
                deathUtils.dropHead(10, 2);
            }

            if (Bukkit.getOnlinePlayers().size() < 20) {
                deathUtils.sendDeathMessageAll();
            } else {
                deathUtils.sendDeadDeathMessage();
                deathUtils.sendDeathMessage(d);
            }

            deathUtils.bountyCheck();

            deathUtils.statsUpdate();

            deathUtils.applyHealth();
            gobblePerk(d);
            agilityPerk(d);
            tankPerk(d);
        }

        deathUtils.kys();
    }


}
