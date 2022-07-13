package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class Achievements {
    private InventoryGUI gui;
    private Player p;

    public Achievements(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "ACHIEVEMENTS"));
        this.p = p;
        addItems();
        gui.open(p);
    }

    public void createButton(int slot, int shards, int coins, String name, String description, String description2, String statsFileName) {
        String isComplete = Tazpvp.achievementManager.getAchievement(p, statsFileName) ? ChatColor.AQUA + "Complete" : ChatColor.RED + "Incomplete";
        Material isComplete2 = Tazpvp.achievementManager.getAchievement(p, statsFileName) ? Material.CHEST_MINECART : Material.MINECART;
        ItemStack item = new ItemStack(isComplete2, 1);
        ItemButton icon = ItemButton.create(new ItemBuilder(item)
            .setName(ChatColor.DARK_AQUA + name)
            .setLore(ChatColor.GRAY + description, ChatColor.GRAY + description2, " ", ChatColor.YELLOW + "Prize: ", ChatColor.GOLD + "" + coins + " Coins", ChatColor.DARK_AQUA + "" + shards + " Shards", "", isComplete)
            , e -> {
            e.setCancelled(true);
        });
        gui.addButton(slot, icon);
    }

    public void addItems() {
        gui.fill(0, 27, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        createButton(10, 5, 220, "Hoarder","Collect every sword","from the wheel.","hoarder");
        createButton(11, 1, 20, "Business","Trade with Caesar","at the mines.","business");
        createButton(12, 5, 380, "Bowling","Get a kill streak","of 100.","bowling");
        createButton(13, 8, 235, "Legend","Get to level","100.","legend");
        createButton(14, 1, 12, "Risk","Kill a player while","at low health.","risk");
        createButton(15, 1, 20, "Sneaky","Grab the shard","from the generator.","sneaky");
        createButton(16, 1, 32, "Brave","Win your first","duel.","brave");
    }
}
