package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static net.tazpvp.tazpvp.Utils.Functionality.PerkUtils.builderPerk;

public class BlockPlaceEvent implements Listener {
    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPlaceBlock(org.bukkit.event.block.BlockPlaceEvent event) {
        Player p = event.getPlayer();
        int timer = 0;
        Location radius = new Location(Bukkit.getWorld("arena"), 0, 96, 94);

        if (p.getGameMode() == GameMode.SURVIVAL) {
            if (p.getWorld().getName().equals("arena")) {
                if (event.getBlock().getLocation().distance(radius) > 50 || event.getBlock().getLocation().getY() <= 95) {
                    event.setCancelled(true);
                }
            }
            Block b = event.getBlock();
            if (!Tazpvp.isRestarting) {
                Material blockType = event.getBlockPlaced().getType();
                ItemStack blockItem = new ItemStack(blockType, 1);

                builderPerk(p, blockItem);

                if (Tazpvp.blocks.containsKey(blockType)) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "You cannot place ores, sell them to Caesar.");
                    return;
                }

                event.getBlock().setMetadata("breakable", new FixedMetadataValue(Tazpvp.getInstance(), true));

                if (event.getBlock().getType() == Material.COBWEB) {
                    timer = 20 * 2;
                } else if (event.getPlayer().hasPermission("tazpvp.rank")) {
                    timer = 20 * 50;
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
