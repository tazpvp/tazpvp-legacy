package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.milkbowl.vault.chat.Chat;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.buyRank;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import static net.tazpvp.tazpvp.Utils.buyRank.RankBuying;

public class ServerStore {
    private final InventoryGUI gui;
    public ServerStore(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 9*5, "main"));
        setitems();
        gui.open(p);
    }

    public void createShopButton(ItemStack item, int slot, int price, String name, String description){
        ItemBuilder b = new ItemBuilder(item).setName(name).setLore(description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price);
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            RankBuying(p, price);
        });
        gui.addButton(slot, button);
    }

    public void setitems(){
        gui.fill(0, 9*5, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        ItemBuilder VIP
        ItemButton VIP = ItemButton.create(new ItemBuilder(Material.MUSIC_DISC_CHIRP).setName(ChatColor.RED + "" + ChatColor.BOLD + "VIP " + ChatColor.GRAY + "250 Credits").setLore(vip), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            RankBuying(p);
            buyRank.rank = "vip";
        });
        gui.addButton(11, VIP);
        ItemButton MVP = ItemButton.create(new ItemBuilder(Material.MUSIC_DISC_WAIT).setName(ChatColor.RED + "" + ChatColor.BOLD + "MVP " + ChatColor.GRAY + "250 Credits"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            RankBuying(p);
            buyRank.rank = "mvp";
        });
        gui.addButton(12, MVP);
        ItemButton MVP2 = ItemButton.create(new ItemBuilder(Material.MUSIC_DISC_PIGSTEP).setName(ChatColor.RED + "" + ChatColor.BOLD + "MVP+ " + ChatColor.GRAY + "250 Credits"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            RankBuying(p);
            buyRank.rank = "mvp+";
        });
        gui.addButton(13, MVP2);
        ItemButton UNBAN = ItemButton.create(new ItemBuilder(Material.TNT_MINECART).setName(ChatColor.RED + "" + ChatColor.BOLD + "UNBAN").setLore(ChatColor.GRAY + "Purchase an unban.\n" + ChatColor.DARK_AQUA + "100 Credits"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
        });
        gui.addButton(21, UNBAN);

        gui.update();
    }
}
