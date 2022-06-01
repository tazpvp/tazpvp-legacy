package net.tazpvp.tazpvp.Events.PhysicalEvents;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class BlockPlaceEvent implements Listener {
    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPlaceBlock(org.bukkit.event.block.BlockPlaceEvent event) {
        Player p = event.getPlayer();

        if (p.getGameMode() == GameMode.SURVIVAL) {
            if (event.getBlock().getLocation().getY() <= 95) {
                event.setCancelled(true);
            } else {
                Block b = event.getBlock();
                b.setMetadata("PlayerPlaced", new FixedMetadataValue(Tazpvp.getInstance(), true));
                if (!Tazpvp.isRestarting) {
                    Material blockType = event.getBlockPlaced().getType();
                    ArrayList<Material> unreq = new ArrayList<>();
                    unreq.add(Material.DEEPSLATE_GOLD_ORE);
                    unreq.add(Material.DEEPSLATE_REDSTONE_ORE);
                    unreq.add(Material.DEEPSLATE_LAPIS_ORE);
                    unreq.add(Material.DEEPSLATE_EMERALD_ORE);
                    unreq.add(Material.DEEPSLATE_IRON_ORE);

                    if (!unreq.contains(blockType)) {
                    }
                    int timer = 0;

                    event.getBlock().setMetadata("breakable", new FixedMetadataValue(Tazpvp.getInstance(), true));
                    if (blockType == Material.OAK_PLANKS) {
                        timer = 20 * 10;
                    } else if (blockType == Material.DIRT) {
                        event.setCancelled(true);
                    } else {
                        timer = 20 * 20;
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            event.getBlockPlaced().setType(Material.AIR);
                        }
                    }.runTaskLater(Tazpvp.getInstance(), timer);
                } else {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "You cannot place blocks right now.");
                }
            }
        }
    }
}
