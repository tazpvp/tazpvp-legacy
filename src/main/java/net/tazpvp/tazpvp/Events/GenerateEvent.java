package net.tazpvp.tazpvp.Events;

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
                //world.dropItem(new Location(Bukkit.getWorld("arena"), 0, 0, 0, 0, 0));
            }
        }.runTaskTimer(plugin, 20*60, 20*30);

    }



}
