package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.tazpvp.tazpvp.Custom.buyRank;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
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
            buyRank.RankBuying(p);
            buyRank.rank = "vip";
        });
        gui.addButton(11, VIP);
        ItemButton MVP = ItemButton.create(new ItemStack(Material.MUSIC_DISC_WAIT), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            buyRank.RankBuying(p);
            buyRank.rank = "mvp";
        });
        gui.addButton(12, MVP);
        ItemButton MVP2 = ItemButton.create(new ItemStack(Material.MUSIC_DISC_PIGSTEP), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            buyRank.RankBuying(p);
            buyRank.rank = "mvp+";
        });
        gui.addButton(13, MVP2);

        gui.update();
    }
}
