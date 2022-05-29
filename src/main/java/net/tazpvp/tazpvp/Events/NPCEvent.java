package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.GUI.Bow.SelectGUI;
import net.tazpvp.tazpvp.GUI.MainMenu.MainGUI;
import net.tazpvp.tazpvp.GUI.MineGUI;
import net.tazpvp.tazpvp.GUI.ShopGUI;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.HeadsToShards;
import net.tazpvp.tazpvp.Utils.MinerNPC;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import static net.tazpvp.tazpvp.Utils.MinerNPC.clickMiner;

public class NPCEvent implements Listener {
    @EventHandler
    public void onNpcClick(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER) {
            e.setCancelled(true);
            Villager v = (Villager) e.getRightClicked();
            if (v.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER)) {
                int id = v.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER);

                //shop id 1
                //upgrade id 2
                //store id 3
                //achievements id 4
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (id == 1) {
                            new ShopGUI(e.getPlayer());
                        } else if (id == 2) {
                            clickMiner(e.getPlayer());
                        } else if (id == 3) {
                            new MainGUI(e.getPlayer());
                        } else if (id == 4) {
                            new SelectGUI(e.getPlayer());
                        } else if (id == 5) {
                            new HeadsToShards().convertHeadsToShards(e.getPlayer());
                        } else {
                            e.getPlayer().sendMessage(ChatColor.RED + "Uh oh! You found a super secret error! Report this to Ntdi, 0xEf300 err resp: " + id +".EntityStorage");
                        }
                    }
                } .runTaskLater(Tazpvp.getInstance(),2);

            }
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player && event.getInventory().getType() == InventoryType.MERCHANT) {
            event.setCancelled(true);
        }
    }
}
