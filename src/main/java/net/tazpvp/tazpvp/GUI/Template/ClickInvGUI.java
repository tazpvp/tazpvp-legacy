package net.tazpvp.tazpvp.GUI.Template;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.RecycleUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.Locale;

public class ClickInvGUI {
    private InventoryGUI gui;
    private Inventory inv;

    public ClickInvGUI(Player p) {
        inv = Bukkit.createInventory(null, 9 * 5, "Recycle Guiad");
        gui = new InventoryGUI(inv);
        addItems(p);
        gui.open(p);
    }

    public void addItems(Player p) {
        int start = 0;
        for (ItemStack i : p.getInventory().getContents()) {
            ItemButton b = ItemButton.create(i, e -> {
                if (canBeSold(i)) {
                    confirmRecycle(p, i);
                }
            });
            gui.addButton(b, start);
            start++;
        }
    }

    public boolean canBeSold(ItemStack i) {
        if (i.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Rookie Sword")) return false;
        return i.getType().toString().toLowerCase(Locale.ROOT).endsWith("sword");
    }

    public void confirmRecycle(Player p, ItemStack i) {
        gui.clear();
        gui.fill(0, 9 * 5, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        ItemButton confirm = ItemButton.create(new ItemBuilder(Material.GREEN_CONCRETE).setName(ChatColor.GREEN + "CONFIRM").setLore(ChatColor.RED + "" + ChatColor.BOLD + "THIS IS PERMANENT"), e -> {
            RecycleUtils.recycleSword(p, i);
            p.closeInventory();
            new BukkitRunnable() {
                @Override
                public void run() {
                    new ClickInvGUI(p);
                }
            }.runTaskLater(Tazpvp.getInstance(), 2);
        });

        ItemButton cancel = ItemButton.create(new ItemBuilder(Material.RED_CONCRETE).setName(ChatColor.RED + "CANCEL"), e -> {
            p.closeInventory();
            new BukkitRunnable() {
                @Override
                public void run() {
                    new ClickInvGUI(p);
                }
            }.runTaskLater(Tazpvp.getInstance(), 2);
        });

        ItemButton item = ItemButton.create(i, e->{});

        gui.addButton(confirm, 20);
        gui.addButton(item, 22);
        gui.addButton(cancel, 24);
    }
}
