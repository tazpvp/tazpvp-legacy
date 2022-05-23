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

public class MainGUI {
    private final InventoryGUI gui;
    public MainGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "main"));
        setitems();
        gui.open(p);
    }

    public void setitems(){
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE).setName("Achievements"));
        ItemButton achievements = ItemButton.create(new ItemStack(Material.WRITABLE_BOOK), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            new SwordCollection(p);
        });
        gui.addButton(11, achievements);
        ItemButton collection = ItemButton.create(new ItemStack(Material.DIAMOND_SWORD).setName("Sword Gallery"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            new AchievementsGUI(p);
        });
        gui.addButton(13, collection);
        ItemButton store = ItemButton.create(new ItemStack(Material.TNT_MINECART).setName("Swag Shop"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            new ServerStore(p);
        });
        gui.addButton(15, store);

        gui.update();
    }
}
