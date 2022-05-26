package net.tazpvp.tazpvp.GUI.Bow;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class SelectGUI {
    private InventoryGUI gui;
    public SelectGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.RED + "Select Your " + ChatColor.YELLOW + "Bow Type"));
        makeItems();
        gui.open(p);
    }

    public void makeItems() {
        gui.fill(0, 27, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" "));

        ItemStack opt1 = new ItemBuilder(Material.BLAZE_POWDER).setName(ChatColor.RED + "Flame").setLore(ChatColor.YELLOW + "Click to go down the flame enchantment path!",
                " ",
                ChatColor.RED + "" + ChatColor.BOLD + "WARNING: THIS IS PERMANENT!");

        ItemStack opt2 = new ItemBuilder(Material.SLIME_BALL).setName(ChatColor.GREEN + "Knockback").setLore(ChatColor.YELLOW + "Click to go down the knockback enchantment path!",
                " ",
                ChatColor.RED + "" + ChatColor.BOLD + "WARNING: THIS IS PERMANENT!");

        ItemButton opt1Button = ItemButton.create(opt1, e -> {
            Player p = (Player) e.getWhoClicked();
            Tazpvp.statsManager.setBowType(p, 1);
            p.closeInventory();
            p.sendMessage("You are now bound to the Knockback enchantment path!", "Open me again to view your enchantment.");
        });

        ItemButton opt2Button = ItemButton.create(opt1, e -> {
            Player p = (Player) e.getWhoClicked();
            Tazpvp.statsManager.setBowType(p, 2);
            p.closeInventory();
            p.sendMessage("You are now bound to the Knockback enchantment path!", "Open me again to view your enchantment.");
        });

        gui.addButton(11, opt1Button);
        gui.addButton(15, opt2Button);

        gui.update();
    }
}
