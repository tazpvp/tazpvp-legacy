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

    public void createShopButton(ItemBuilder b, int slot, int price){
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            RankBuying(p, price);
            buyRank.rank = "vip";
        });
        gui.addButton(slot, button);
    }

    public void setitems(){
        gui.fill(0, 9*5, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        ItemBuilder vip = new ItemBuilder(Material.MUSIC_DISC_CHIRP).setName(ChatColor.RED+""+ChatColor.BOLD+"VIP " + ChatColor.GRAY + 250 + " Credits")
                .setLore(ChatColor.RED+
                        "〡Enderchest access",
                        "〡Hat command",
                        "〡Nickname command",
                        "〡Votekick command",
                        "〡Invsee command",
                        "〡Vanish command",
                        "〡RGB Armor Colors",
                        "〡Durable Blocks",
                        "〡Anti-spam Bypass",
                        "〡No Lag-back",
                        "〡RGB Blocks",
                        "〡VIP++ discord rank"
                );
        createShopButton(vip, 11, 250);
        ItemBuilder mvp = new ItemBuilder(Material.MUSIC_DISC_WAIT).setName(ChatColor.GREEN+""+ChatColor.BOLD+"MVP " + ChatColor.GRAY + 450 + " Credits")
                .setLore(ChatColor.GREEN+
                        "〡Enderchest access",
                        "〡Hat command",
                        "〡Nickname command",
                        "〡Votekick command",
                        "〡Invsee command",
                        "〡Vanish command",
                        "〡RGB Armor Colors",
                        "〡Durable Blocks",
                        "〡Anti-spam Bypass",
                        "〡No Lag-back",
                        "〡RGB Blocks",
                        "〡VIP++ discord rank"
                );
        createShopButton(mvp, 12, 250);
        ItemBuilder mvp2 = new ItemBuilder(Material.MUSIC_DISC_PIGSTEP).setName(ChatColor.GOLD+""+ChatColor.BOLD+"MVP+ " + ChatColor.GRAY + 950 + " Credits")
                .setLore(ChatColor.GOLD+
                        "〡Enderchest access",
                        "〡Hat command",
                        "〡Nickname command",
                        "〡Votekick command",
                        "〡Invsee command",
                        "〡Vanish command",
                        "〡RGB Armor Colors",
                        "〡Durable Blocks",
                        "〡Anti-spam Bypass",
                        "〡No Lag-back",
                        "〡RGB Blocks",
                        "〡VIP++ discord rank"
                );
        createShopButton(mvp2, 13, 250);
        ItemButton UNBAN = ItemButton.create(new ItemBuilder(Material.TNT_MINECART).setName(ChatColor.RED + "" + ChatColor.BOLD + "UNBAN").setLore(ChatColor.GRAY + "Purchase an unban.\n" + ChatColor.DARK_AQUA + "100 Credits"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
        });
        gui.addButton(21, UNBAN);

        gui.update();
    }
}
