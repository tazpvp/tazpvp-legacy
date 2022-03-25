package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class PlaceBlockEvent implements Listener {
    private final Random rand = new Random();

    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPlaceBlock(org.bukkit.event.block.BlockPlaceEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            if (!Tazpvp.isRestarting) {
                Material blockType = event.getBlockPlaced().getType();
                ArrayList<Material> unreq = new ArrayList<>();
                unreq.add(Material.COAL_ORE);
                unreq.add(Material.IRON_ORE);
                unreq.add(Material.GOLD_ORE);
                unreq.add(Material.LAPIS_ORE);
                unreq.add(Material.DIAMOND_ORE);
                unreq.add(Material.EMERALD_ORE);

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
            }
        } else {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot place blocks right now.");
        }
    }
}
