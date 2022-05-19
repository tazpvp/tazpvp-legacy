package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;

public class ServerStore {
    private final InventoryGUI gui;
    public ServerStore(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 9*5, "main"));
        setitems();
        gui.open(p);
    }

    public void setitems(){
        gui.fill(0, 9*5, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        ItemButton VIP = ItemButton.create(new ItemStack(Material.MUSIC_DISC_CHIRP), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            new SwordCollection(p);
        });
        gui.addButton(11, VIP);
        ItemButton MVP = ItemButton.create(new ItemStack(Material.DIAMOND_SWORD), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            new SwordCollection(p);
        });
        gui.addButton(12, MVP);
        ItemButton MVP2 = ItemButton.create(new ItemStack(Material.TNT_MINECART), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            new SwordCollection(p);
        });
        gui.addButton(13, MVP2);

        gui.update();
    }

    public void RankPurchase(OfflinePlayer p, String rank) {


    }
}
