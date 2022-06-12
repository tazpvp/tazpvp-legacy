package net.tazpvp.tazpvp.Utils.Passive;

import net.tazpvp.tazpvp.Managers.PlayerStats.StatsManager;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Generator {
    public void generator(Plugin plugin){
        ItemStack shard = new ItemStack(Material.AMETHYST_SHARD);
        new BukkitRunnable(){
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() >= 5) {
                    Item i = Objects.requireNonNull(Bukkit.getWorld("arena")).dropItem(new Location(Bukkit.getWorld("arena"), 0.5, 80.5, 94.5), shard);
                    i.setVelocity(new Vector(0, 0, 0));
                    new BukkitRunnable(){
                        public void run() {
                            Bukkit.broadcastMessage("");
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + " A shard will generate underground in " + ChatColor.DARK_PURPLE + "30 " + ChatColor.LIGHT_PURPLE + "seconds.");
                            Bukkit.broadcastMessage("");
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                            }
                        }
                    }.runTaskLater(plugin, 3600); //3 mins
                }
            }
        }.runTaskTimer(plugin, 20*60, 4200); //3.5 mins

        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player p : Tazpvp.afkPlayers){
                    Tazpvp.statsManager.addShards(p, 1);
                    Tazpvp.statsManager.addCoins(p, 30);
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
                    p.sendMessage(ChatColor.GOLD + "AFK REWARD: " + ChatColor.YELLOW + "1 SHARD & 30 COINS");
                }
            }
        }.runTaskTimer(plugin, 1000, 12000);
    }
}
