package net.tazpvp.tazpvp.GUI.EnderChests;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EnderChestPoorGUI implements Listener {
    private final Inventory inv;

    public EnderChestPoorGUI(@Nullable Player p) {
        if (p != null) {
            inv = Bukkit.createInventory(null, 9, "Buy a rank!");
            populate(Tazpvp.enderChestManager.getItemContents(p));
            p.openInventory(inv);
        } else {
            inv = null;
        }
    }

    public void populate(ItemStack[] contents) {
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, contents[i]);
        }
    }

    public void updateContents(Player p) {
        ItemStack[] contents = new ItemStack[inv.getSize()];
        for (int i = 0; i < inv.getSize(); i++) {
            contents[i] = inv.getItem(i);
        }
        Tazpvp.enderChestManager.setItemContents(p, contents);
    }

    @EventHandler
    public void guiClose(InventoryCloseEvent e) {
        if (e.getInventory() == inv) {
            updateContents((Player) e.getPlayer());
        }
    }

}
