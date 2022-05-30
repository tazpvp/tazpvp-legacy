package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.milkbowl.vault.chat.Chat;
import net.tazpvp.tazpvp.Utils.buyRank;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

    public void setitems(){
        ChatColor red = ChatColor.RED;
        gui.fill(0, 9*5, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        ItemButton VIP = ItemButton.create(new ItemBuilder(Material.MUSIC_DISC_CHIRP).setName(ChatColor.RED + "" + ChatColor.BOLD + "VIP " + ChatColor.GRAY + "250 Credits")
                        .setLore(red + "〡Enderchest access\n"+red+"〡Hat command\n"+red+"〡Nickname command\n"+red+"〡Votekick command\n"+red+"〡Invsee command\n"+
                                red+"〡Vanish command\n"+red+"〡RGB Armor Colors\n"+red+"〡Durable Blocks\n"+red+"〡Anti-spam Bypass\n"+red+"〡No Lag-back\n"+red+"〡RGB Blocks\n"+red+"〡VIP++ discord rank")
                , e -> {
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
