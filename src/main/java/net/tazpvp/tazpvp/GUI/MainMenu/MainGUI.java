package net.tazpvp.tazpvp.GUI.MainMenu;

import net.tazpvp.tazpvp.GUI.MainMenu.SubMenu.ServerStore;
import net.tazpvp.tazpvp.GUI.MainMenu.SubMenu.SwordCollection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class MainGUI {
    private final InventoryGUI gui;
    public MainGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "MENU"));
        setitems();
        gui.open(p);
    }

    public void setitems(){
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        ItemButton achievements = ItemButton.create(new ItemBuilder(Material.WRITABLE_BOOK).setName(ChatColor.AQUA + "" + ChatColor.BOLD + "PROGRESS").setLore(ChatColor.GRAY + "Achievement book."), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            new AchievementsGUI(p);
        });
        gui.addButton(11, achievements);
        ItemButton collection = ItemButton.create(new ItemBuilder(Material.DIAMOND_SWORD).setName(ChatColor.YELLOW + "" + ChatColor.BOLD + "WEAPONRY").setLore(ChatColor.GRAY + "View your collected weapons."), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            new SwordCollection(p);
        });
        gui.addButton(13, collection);
        ItemButton store = ItemButton.create(new ItemBuilder(Material.TNT_MINECART).setName(ChatColor.RED + "" + ChatColor.BOLD + "STORE").setLore(ChatColor.GRAY + "Ranks and Cosmetics."), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            new ServerStore(p);
        });
        gui.addButton(15, store);

        gui.update();
    }
}
