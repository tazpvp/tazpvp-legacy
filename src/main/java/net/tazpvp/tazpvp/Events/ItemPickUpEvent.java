package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemPickUpEvent implements Listener {
    @EventHandler
    public void onItemPickUp(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player p) {
            if (e.getItem().getItemStack().getType() == Material.AMETHYST_SHARD) {
                new BukkitRunnable(){
                    public void run(){
                        p.getInventory().remove(Material.AMETHYST_SHARD);
                        p.sendMessage(ChatColor.DARK_AQUA + "You picked up a shard. " + ChatColor.AQUA + "+1 Shard");
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
                        Tazpvp.statsManager.addShards(p, 1);

                    }
                }.runTaskLater(Tazpvp.getInstance(), 5);
            }
        }
    }
}
