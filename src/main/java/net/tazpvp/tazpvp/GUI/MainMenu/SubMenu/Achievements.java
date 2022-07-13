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

    public void createShopButton(int slot, String name, String description, String description2, String statsFileName) {
        String isComplete = Tazpvp.achievementManager.getAchievement(p, statsFileName) ? ChatColor.AQUA + "Completed!" : ChatColor.RED + "Incomplete!";
        Material isComplete2 = Tazpvp.achievementManager.getAchievement(p, statsFileName) ? Material.CHEST_MINECART : Material.MINECART;
        ItemStack item = new ItemStack(isComplete2, 1);
        ItemButton icon = ItemButton.create(new ItemBuilder(item)
            .setName(ChatColor.DARK_AQUA + name)
            .setLore(ChatColor.GRAY + description, ChatColor.GRAY + description2, " ", isComplete)
            , e -> {
            e.setCancelled(true);
        });
        gui.addButton(slot, icon);
    }

    public void addItems() {
        gui.fill(0, 27, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        createShopButton(10,"Hoarder","Collect every sword","from the wheel.","hoarder");
        createShopButton(11,"Business","Trade with Caesar","at the mines.","business");
        createShopButton(12,"Bowling","Get a kill streak","of 100.","bowling");
        createShopButton(13,"Legend","Get to level","100.","legend");
        createShopButton(14,"Risk","Kill a player while","at low health.","risk");
        createShopButton(15,"Sneaky","Grab the shard","from the generator.","sneaky");
        createShopButton(16,"Brave","Win your first","duel.","brave");
    }
}
