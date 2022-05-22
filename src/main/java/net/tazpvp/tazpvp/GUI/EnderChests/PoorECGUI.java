package net.tazpvp.tazpvp.GUI.EnderChests;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class PoorECGUI implements Listener {
    private final Inventory inv;
    public PoorECGUI(Player p) {
        inv = Tazpvp.playerWrapperMap.get(p.getUniqueId()).getPoorInv();
        p.openInventory(inv);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getType().getDefaultTitle().equals(inv.getType().getDefaultTitle())) {
            Tazpvp.playerWrapperMap.get(e.getPlayer().getUniqueId()).setPoorInv(e.getInventory());
        }
    }
}
