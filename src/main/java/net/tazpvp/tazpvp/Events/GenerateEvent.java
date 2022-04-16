package net.tazpvp.tazpvp.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GenerateEvent {
    public void generator(Plugin plugin){
        ItemStack shard = new ItemStack(Material.PRISMARINE_SHARD);
        new BukkitRunnable(){
            @Override
            public void run() {
                Bukkit.getWorld("arena").dropItemNaturally(new Location(Bukkit.getWorld("arena"), 0, 85,94), shard);
            }
        }.runTaskTimer(plugin, 20*60, 20*60);

    }



}
