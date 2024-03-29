package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.GUI.Guild.GuildBrowserGUI;
import net.tazpvp.tazpvp.GUI.MainMenu.MainGUI;
import net.tazpvp.tazpvp.GUI.NPCGui.BowGUI;
import net.tazpvp.tazpvp.GUI.NPCGui.DepthsGUI;
import net.tazpvp.tazpvp.GUI.NPCGui.ShopGUI;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.NPCS.BubNPC;
import net.tazpvp.tazpvp.Utils.NPCS.RigelNPC;
import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import net.tazpvp.tazpvp.unused.SelectGUI;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import static net.tazpvp.tazpvp.Utils.NPCS.CaesarNPC.clickMiner;

public class NPCEvent implements Listener {
    @EventHandler
    public void onNpcClick(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER || e.getRightClicked().getType() == EntityType.WANDERING_TRADER || e.getRightClicked().getType() == EntityType.VINDICATOR) {
            e.setCancelled(true);
            Entity v = e.getRightClicked();
            if (v.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER)) {
                int id = v.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER);
                int pitch = v.getPersistentDataContainer().get(PdcUtils.pitchKey, PersistentDataType.INTEGER);
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_YES, 1, pitch);


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
                            new BubNPC().convertHeadsToShards(e.getPlayer());
                        } else if (id == 6) {
                            new RigelNPC().clickRigel(e.getPlayer());
                        } else if (id == 7)  {
                            new DepthsGUI(e.getPlayer());
                        } else if (id == 8) {
                            new BowGUI(e.getPlayer());
                        } else if (id == 9) {
                            new GuildBrowserGUI(e.getPlayer());
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
