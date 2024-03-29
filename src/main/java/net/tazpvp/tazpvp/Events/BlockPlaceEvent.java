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
                    return;
                }
            }
            Block b = event.getBlock();
            if (!Tazpvp.isRestarting) {
                Material blockType = event.getBlockPlaced().getType();
                ItemStack blockItem = new ItemStack(blockType, 1);

                if (blockType.equals(Material.PLAYER_HEAD) || blockType.equals(Material.PLAYER_WALL_HEAD)) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "Trade heads for shards at Bub's shop.");
                    return;
                }

                if (!Tazpvp.allowedBlocks.contains(blockType)) {
                    event.setCancelled(true);
                    p.sendMessage(ChatColor.RED + "You cannot place this, if it's an ore, sell it to Caesar at the mine.");
                    return;
                }

                builderPerk(p, blockItem);

                b.setMetadata("breakable", new FixedMetadataValue(Tazpvp.getInstance(), true));
                b.setMetadata("PlayerPlaced", new FixedMetadataValue(Tazpvp.getInstance(), true));

                if (event.getBlock().getType() == Material.COBWEB) {
                    timer = 30;
                } else if (event.getPlayer().hasPermission("tazpvp.rank")) {
                    timer = 20 * 40;
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
