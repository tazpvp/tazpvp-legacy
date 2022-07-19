package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import static net.tazpvp.tazpvp.Utils.Functionality.AchieveUtils.Achieve;

public class ItemPickUpEvent implements Listener {
    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player p) {
            Inventory inv = p.getInventory();
            if (e.getItem().getItemStack().getType() == Material.AMETHYST_SHARD) {
                new BukkitRunnable(){
                    public void run(){
                        if (inv.contains(Material.AMETHYST_SHARD)) {
                            inv.remove(Material.AMETHYST_SHARD);
                            p.sendMessage(ChatColor.DARK_AQUA + "You picked up a shard. " + ChatColor.AQUA + "+1 Shard");
                            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
                            Tazpvp.statsManager.addShards(p, 1);
                            if (!Tazpvp.achievementManager.statsFile.getBoolean(p.getUniqueId() + ".sneaky")) {
                                Achieve(p, "Sneaky", "sneaky", 1, 20);
                            }
                        }
                    }
                }.runTaskLater(Tazpvp.getInstance(), 1);
            }
        }
    }
}
