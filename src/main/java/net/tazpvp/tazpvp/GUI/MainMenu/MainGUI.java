package net.tazpvp.tazpvp.GUI.MainMenu;

import net.tazpvp.tazpvp.GUI.AchievementsGUI;
import net.tazpvp.tazpvp.GUI.MainMenu.SubMenu.ServerStore;
import net.tazpvp.tazpvp.GUI.MainMenu.SubMenu.SwordCollection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class MainGUI {
    private final InventoryGUI gui;
    public MainGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "main"));
        setitems();
        gui.open(p);
    }

    public void setitems(){
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        ItemButton achievements = ItemButton.create(new ItemBuilder(Material.WRITABLE_BOOK).setName("Achievements"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            new AchievementsGUI(p);
        });
        gui.addButton(11, achievements);
        ItemButton collection = ItemButton.create(new ItemBuilder(Material.DIAMOND_SWORD).setName("Collection"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            new SwordCollection(p);
        });
        gui.addButton(13, collection);
        ItemButton store = ItemButton.create(new ItemBuilder(Material.TNT_MINECART).setName("Store").setLore("Ranks and Cosmetics"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            new ServerStore(p);
        });
        gui.addButton(15, store);

        gui.update();
    }
}
