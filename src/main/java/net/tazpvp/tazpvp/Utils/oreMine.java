package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class oreMine implements Listener {

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode() == GameMode.SURVIVAL) {
            e.setCancelled(true);
            Block b = e.getBlock();
            Material block = b.getType();
            if (Tazpvp.blocks.contains(block)) {
                if (block == Material.DEEPSLATE_GOLD_ORE) {
                    oreRespawn(p, block, Material.RAW_GOLD, 350L, b);
                } else if (block == Material.DEEPSLATE_REDSTONE_ORE) {
                    oreRespawn(p, block, Material.REDSTONE, 350L, b);
                } else if (block == Material.DEEPSLATE_IRON_ORE) {
                    oreRespawn(p, block, Material.RAW_IRON, 350L, b);
                } else if (block == Material.DEEPSLATE_LAPIS_ORE) {
                    oreRespawn(p, block, Material.LAPIS_LAZULI, 350L, b);
                } else if (block == Material.DEEPSLATE_EMERALD_ORE) {
                    oreRespawn(p, block, Material.EMERALD, 350L, b);
                }
            }
        }
    }

    public void oreRespawn(Player p, Material block, Material block2, Long dur, Block b){
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
