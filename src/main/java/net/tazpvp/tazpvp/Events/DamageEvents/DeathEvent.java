package net.tazpvp.tazpvp.Events.DamageEvents;

import net.tazpvp.tazpvp.Managers.CombatLogManager;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
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
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;
import java.util.WeakHashMap;

public class DeathEvent implements Listener {
    public WeakHashMap<Player, Long> cooldowns = new WeakHashMap<Player, Long>();

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (p.getHealth() - e.getFinalDamage() <= 0) {
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
                            }
                        }
                    }
                }
            }
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
        if (d != null) { //code will run if a player kills another player

            if (dropHead) {
                if (new Random().nextInt(10) <= 2){
                    ItemStack head = new ItemBuilder(ItemUtils.skull(p)).setName(ChatColor.YELLOW + p.getName() + "'s head");
                    Bukkit.getWorld("arena").dropItemNaturally(p.getLocation(), head);
                }
            }

            if (Bukkit.getOnlinePlayers().size() < 20) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (d.getInventory().getItemInMainHand().hasItemMeta()) {
                        String cusName = d.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                        if (Objects.equals(online.getName(), d.getName())) {
                            d.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + p.getName() + ChatColor.DARK_GRAY + " with " + ChatColor.AQUA + cusName + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 15 Experience");
                        } else {
                            online.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.GRAY + d.getName() + ChatColor.DARK_GRAY + " killed " + ChatColor.GRAY + p.getName() + ChatColor.DARK_GRAY + " with " + ChatColor.AQUA + cusName );
                        }
                    } else {
                        if (Objects.equals(online.getName(), d.getName())) {
                            d.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 15 Experience");
                        } else {
                            online.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.GRAY + d.getName() + ChatColor.DARK_GRAY + " killed " + ChatColor.GRAY + p.getName());
                        }
                    }
                }
            } else {
                p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.DARK_GRAY + "You were killed by " + ChatColor.GRAY + d.getName());
            }

            if (Tazpvp.bounty.containsKey(p.getUniqueId())) {
                Tazpvp.statsManager.addCoins(d, Tazpvp.bounty.get(p.getUniqueId()));
                Bukkit.broadcastMessage(ChatColor.AQUA + d.getName() + ChatColor.DARK_AQUA + " collected the " + ChatColor.AQUA + Tazpvp.bounty.get(p.getUniqueId()) + ChatColor.DARK_AQUA + " bounty on " + ChatColor.AQUA + p.getName());
                Tazpvp.bounty.remove(p.getUniqueId());
            }

            Tazpvp.statsManager.addKills(d, 1);
            Tazpvp.statsManager.addExp(d, 15);
            Tazpvp.statsManager.addCoins(d, 7);

            if (d.getHealth() > 14) {
                d.setHealth(20);
            } else {
                d.setHealth(d.getHealth() + 6);
            }


            if (Tazpvp.statsManager.checkLevelUp(d)) {
                Tazpvp.statsManager.levelUp(d, 1);
                Tazpvp.statsManager.initScoreboard(d);
            }

            Tazpvp.statsManager.initScoreboard(d);
        }


        Tazpvp.statsManager.addDeaths(p, 1);
        Tazpvp.statsManager.initScoreboard(p);

        if (p.hasMetadata("LastDamager")) {
            p.removeMetadata("LastDamager", Tazpvp.getInstance());
        }

        p.getWorld().playEffect(p.getLocation().add(0, 1, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);

        p.setGameMode(GameMode.SPECTATOR);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerUtils.healPlayer(p);

                if (Tazpvp.punishmentManager.isBanned(p)) {
                    p.teleport(new Location(Bukkit.getWorld("ban"), 0, 77, 0));
                } else {
                    p.teleport(configUtils.spawn);
                }
                p.setGameMode(GameMode.SURVIVAL);
            }
        }.runTaskLater(Tazpvp.getInstance(), 60L);
    }
}
