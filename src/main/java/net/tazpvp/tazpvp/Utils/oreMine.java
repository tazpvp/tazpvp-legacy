package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class oreMine implements Listener {
    public static void oreRespawn(Player p, Material block, Material block2, Long dur, Block b){
        p.getInventory().addItem(new ItemStack(block));
        b.setType(Material.BEDROCK);
        new BukkitRunnable() {
            @Override
            public void run() {
                b.setType(block);
            }
        }.runTaskLater(Tazpvp.getInstance(), dur);
    }
}
