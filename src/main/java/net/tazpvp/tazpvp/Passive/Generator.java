package net.tazpvp.tazpvp.Passive;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Generator {
    public void generator(Plugin plugin){
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        new BukkitRunnable(){
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() > 0) {
                    Bukkit.getWorld("arena").dropItemNaturally(new Location(Bukkit.getWorld("arena"), 0.5, 85, 94.5), shard);
                    new BukkitRunnable(){
                        public void run() {
                            Bukkit.broadcastMessage("");
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + " A shard will generate underground in " + ChatColor.DARK_PURPLE + "60 " + ChatColor.LIGHT_PURPLE + "seconds.");
                            Bukkit.broadcastMessage("");
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                            }
                        }
                    }.runTaskLater(plugin, 20*60*5);
                }
            }
        }.runTaskTimer(plugin, 20*60, 20*60*6);

    }
}
