package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Mobs.CZombie;
import net.tazpvp.tazpvp.Mobs.MobLocations;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.DeathUtils;
import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.WeakHashMap;

import static net.tazpvp.tazpvp.Tazpvp.vanished;
import static net.tazpvp.tazpvp.Utils.Functionality.AchieveUtils.Achieve;
import static net.tazpvp.tazpvp.Utils.Functionality.PerkUtils.*;

public class DeathEvent implements Listener {
    public WeakHashMap<Player, Long> cooldowns = new WeakHashMap<Player, Long>();

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (Tazpvp.fallDamageImmune.contains(p)) {
                    Tazpvp.fallDamageImmune.remove(p);
                    e.setCancelled(true);
                    return;
                }
            }
            if (p.getWorld().getName().equals("arena")) {
                if (p.getLocation().distance(configUtils.spawn) < 25) {
                    e.setCancelled(true);
                    return;
                }
            }
            if (e instanceof EntityDamageByEntityEvent h) {
                if (h.getDamager() instanceof Player damager) {
                    if (vanished.contains(damager)) {
                        damager.sendMessage(ChatColor.RED + "You cannot hit people while in vanish.");
                        e.setCancelled(true);
                        return;
                    }
                }
            }
            if (p.hasMetadata("goingToSpawn")) {
                p.removeMetadata("goingToSpawn", Tazpvp.getInstance());
                p.sendMessage(ChatColor.GREEN + "Teleportation cancelled, damage taken.");
            }
            double fd = e.getFinalDamage();
            if (p.getHealth() - fd <= 0) {
                e.setCancelled(true);
                if (e instanceof EntityDamageByEntityEvent) { // Code that requires a damager should4 go here{
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
                    if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                        if (Tazpvp.duelLogic.isInDuel(p)) {
                            if (Tazpvp.duelLogic.getDuel(p).getPlayer1() == p) {
                                e.getEntity().teleport(Tazpvp.duelLogic.getDuel(p).getPlayer2().getLocation());
                            } else {
                                e.getEntity().teleport(Tazpvp.duelLogic.getDuel(p).getPlayer1().getLocation());
                            }
                            Tazpvp.duelLogic.duelEnd(p);
                            e.setCancelled(true);
                            return;
                        } else if (p.getWorld().getName().equals("arena")) {
                            p.teleport(configUtils.spawn);
                        }
                    }

                    if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
                        if (Tazpvp.duelLogic.isInDuel(p)) {
                            Tazpvp.duelLogic.duelEnd(p);
                            e.setCancelled(true);
                            return;
                        }
                        if (Tazpvp.lastDamage.get(p.getUniqueId()) != null) {
                            if (Bukkit.getOfflinePlayer(Tazpvp.lastDamage.get(p.getUniqueId())).isOnline()) {
                                DeathFunction(p, Bukkit.getPlayer(Tazpvp.lastDamage.get(p.getUniqueId())), false);
                            }
                        }
                    }

                    if (Tazpvp.duelLogic.isInDuel(p)) {
                        Tazpvp.duelLogic.duelEnd(p);
                        e.setCancelled(true);
                        return;
                    }
                    DeathFunction(p, null, false);
                }
            } else if (p.getWorld() == Bukkit.getWorld("arena")) {
                if (e instanceof EntityDamageByEntityEvent ee) {
                    if ((ee).getDamager() instanceof Player d) {

                        Tazpvp.lastDamage.put(p.getUniqueId(), d.getUniqueId());

                        CombatTag.putInCombat(d);
                        CombatTag.putInCombat(p);

                        ItemStack item = d.getInventory().getItemInMainHand();

                        // Now just a check if item is a sword - but overkill
                        if (item.hasItemMeta()) {
                            NamespacedKey key = PdcUtils.key;
                            ItemMeta meta = item.getItemMeta();
                            PersistentDataContainer container = meta.getPersistentDataContainer();
                            if (container.has(key, PersistentDataType.DOUBLE)){
                                return;
                            }
                        }

                        customItems((EntityDamageByEntityEvent) e);

                    } else if (ee.getDamager() instanceof Arrow) {
                        if (((Arrow) ee.getDamager()).getShooter() instanceof Player d) {
                            if (!p.equals(d)) {
                                Tazpvp.lastDamage.put(p.getUniqueId(), d.getUniqueId());

                                CombatTag.putInCombat(p);
                                CombatTag.putInCombat(d);
                            }
                        }
                    }
                }
            }
        }
    }

    public void customItems(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getEntity();
        if (e.getDamager() instanceof Player d) {
            ItemStack item = d.getInventory().getItemInMainHand();
            ItemStack offhand = d.getInventory().getItemInOffHand();
            // do stuff
            if (item.getType().equals(Material.INK_SAC)){
                removeOne(d, true);
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2 * 20, 1));
            } else if (offhand.getType().equals(Material.SHIELD)) {
                removeOne(d, false);
            }
        }
    }

    public void removeOne(Player p, boolean mainHand) {
        ItemStack item = (mainHand) ? p.getInventory().getItemInMainHand() : p.getInventory().getItemInOffHand();
        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            if (mainHand) {
                p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            } else {
                p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }
        }
    }

//    public void itemDamage(Double id, EntityDamageByEntityEvent e) { // sadness :'(
//        for (Items item : Items.values()) {
//            if (item.getStoredID() == id) {
//                Player d = (Player) e.getDamager();
//                double cooldownTime = item.getCooldown(); // Get number of milis from wherever you want
//                if(cooldowns.containsKey(d)) {
//                    double secondsLeft = (((double)cooldowns.get(d))+cooldownTime) - ((double)System.currentTimeMillis());
//                    if(secondsLeft>0) {
//                        e.setDamage((double) item.getDamage()/4);
//                        cooldowns.put(d, System.currentTimeMillis()); // Ntdi no longer is sad bc this is ass code
//                        return;
//                    }
//                }
//                // No cooldown found or cooldown has expired, save new cooldown
//                cooldowns.put(d, System.currentTimeMillis());
//                // Do Command Here
//                e.setDamage(item.getDamage());
//                if (d.getVelocity().getY() < 0) {
//                    e.setDamage(item.getDamage() * 1.5F); // lol nice check too bad idc
//                }
//                return;
//            }
//        }
//    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Zombie z) {
            e.setDroppedExp(0);
            e.getDrops().clear();
            new BukkitRunnable() {
                @Override
                public void run() {
                    new CZombie().createZombie();
                }
            }.runTaskLater(Tazpvp.getInstance(), (long) (20 * MobLocations.ZOMBIE.respawnTime())); //huh
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (Tazpvp.duelLogic.isInDuel(e.getEntity())) {
            Tazpvp.duelLogic.duelEnd(e.getEntity());
            return;
        }
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
            if (p == d) {
                deathUtils.sendDeathMessageAll(true);
                deathUtils.statsUpdate();
                deathUtils.kys();
                return;
            }
            if (dropHead) {
                deathUtils.dropHead(100, 5);
            }

            if (Bukkit.getOnlinePlayers().size() < 10) {
                deathUtils.sendDeathMessageAll(false);
            } else {
                deathUtils.sendDeadDeathMessage();
                deathUtils.sendDeathMessage(d);
            }

            if (d.getHealth() <= 1) {
                Achieve(d, "Risk", "risk", 1, 12);
            }

            deathUtils.bountyCheck();

            deathUtils.statsUpdate();

            GuildUtils.addKillToGuild(d);
            GuildUtils.addDeathToGuild(p);

            deathUtils.applyHealth();
            gobblePerk(d);
            agilityPerk(d);
            tankPerk(d);
        }

        deathUtils.kys();
    }


}
