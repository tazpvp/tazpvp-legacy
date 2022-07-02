package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.GUI.UnlockSwordGUI;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Items.Item;
import net.tazpvp.tazpvp.Utils.Custom.Items.ItemManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class InteractEvent implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            if (b.getType() == Material.RESPAWN_ANCHOR) {
                e.setCancelled(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        new UnlockSwordGUI(p);
                    }
                }.runTaskLater(Tazpvp.getInstance(), 2L);
            }
            return;
        }

        if (runCustomItem(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        if (runCustomItem(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        if (runCustomItem(e.getPlayer())) {
            e.setCancelled(true);
            return;
        }

        if (e.getRightClicked() instanceof Minecart) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent e) {
        if (e.getVehicle() instanceof Minecart) {
            e.setCancelled(true);
        }
    }


    public boolean runCustomItem(Player p) {
        ItemStack inHand = p.getInventory().getItemInMainHand();
        if (inHand.hasItemMeta()) {
            ItemMeta meta = inHand.getItemMeta();
            if (meta.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "cid"), PersistentDataType.DOUBLE)) {
                double cID = meta.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "cid"), PersistentDataType.DOUBLE);
                for (Item item : ItemManager.items) {
                    if (item.cID == cID) {
                        if (item.execute(p, inHand, cID)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
