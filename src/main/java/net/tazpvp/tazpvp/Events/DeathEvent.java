package net.tazpvp.tazpvp.Events;

import com.google.common.base.Supplier;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Item;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.ItemManager;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Items;
import net.tazpvp.tazpvp.Utils.PlayerUtils;
import net.tazpvp.tazpvp.Utils.configUtils;
import net.tazpvp.tazpvp.unused.launchpad.ekisBokisSerisEkis;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.itemutils.ItemBuilder;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.logging.Logger;

public class DeathEvent implements Listener {
    public WeakHashMap<Player, Long> cooldowns = new WeakHashMap<Player, Long>();

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
                    if (((EntityDamageByEntityEvent) e).getDamager() instanceof Player d) {
                        Tazpvp.lastDamage.put(p.getUniqueId(), d.getUniqueId());

                        ItemStack item = d.getInventory().getItemInMainHand();
                        if (item.hasItemMeta()) {
                            if (item.getItemMeta().hasDisplayName()) {
                                String displayName = item.getItemMeta().getDisplayName();
                                itemDamage(displayName, (EntityDamageByEntityEvent) e);
                            }
                        }
                    }
                }
            }
        }
    }

    public void itemDamage(String name, EntityDamageByEntityEvent e) {
        for (Items item : Items.values()) {
            if (item.getName().equals(name)) {
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
        DeathFunction(e.getEntity(), null);
    }

    public void DeathFunction(Player p, @Nullable Player d) {
        if (d != null) { //code will run if a player kills another player

            ItemStack pSkull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta sm = (SkullMeta) pSkull.getItemMeta();
            sm.setOwner(p.getName());
            pSkull.setItemMeta(sm);

            Bukkit.getWorld("arena").dropItemNaturally(p.getLocation(), pSkull);

            if (Bukkit.getOnlinePlayers().size() < 20) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (Objects.equals(online.getName(), d.getName())) {
                        d.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.DARK_GRAY + "You killed " + ChatColor.GRAY + p.getName() + ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 15 Experience");
                    } else {
                        online.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.GRAY + d.getName() + ChatColor.DARK_GRAY + " killed " + ChatColor.GRAY + p.getName());
                    }
                }
            } else {
                p.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.DARK_GRAY + "You were killed by " + ChatColor.GRAY + d.getName());
            }

            if (Tazpvp.bounty.containsKey(p.getUniqueId())) {
                Tazpvp.statsManager.addMoney(d, Tazpvp.bounty.get(p.getUniqueId()));
                Bukkit.broadcastMessage(ChatColor.AQUA + d.getName() + ChatColor.DARK_AQUA + " collected the " + ChatColor.AQUA + Tazpvp.bounty.get(p.getUniqueId()) + ChatColor.DARK_AQUA + " bounty on " + ChatColor.AQUA + p.getName());
                Tazpvp.bounty.remove(p.getUniqueId());
            }

            Tazpvp.statsManager.addKills(d, 1);
            Tazpvp.statsManager.addExp(d, 15);
            Tazpvp.statsManager.addMoney(d, 7);

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

                p.teleport(configUtils.spawn);
                p.setGameMode(GameMode.SURVIVAL);
                PlayerUtils.kitPlayer(p);
            }
        }.runTaskLater(Tazpvp.getInstance(), 60L);
    }
}
