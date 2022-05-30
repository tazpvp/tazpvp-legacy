package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.milkbowl.vault.chat.Chat;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.buyRank;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import static net.tazpvp.tazpvp.Utils.ItemStackUtils.hideFlag;
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

        ChatColor r = ChatColor.RED; ItemStack vipmat = new ItemStack(Material.MUSIC_DISC_CHIRP); hideFlag(vipmat);
        ItemBuilder vip = new ItemBuilder(vipmat).setName(ChatColor.RED+""+ChatColor.BOLD+"VIP " + ChatColor.GRAY + 250 + " Credits")
                .setLore(
                        r+"〡Enderchest access",
                        r+"〡Hat command",
                        r+"〡Nickname command",
                        r+"〡Votekick command",
                        r+"〡Invsee command",
                        r+"〡Vanish command",
                        r+"〡RGB Armor Colors",
                        r+"〡Durable Blocks",
                        r+"〡Anti-spam Bypass",
                        r+"〡No Lag-back",
                        r+"〡RGB Blocks",
                        r+"〡VIP++ discord rank"
                );
        createShopButton(vip, 11, 250);
        ChatColor gr = ChatColor.GREEN; ItemStack mvpmat = new ItemStack(Material.MUSIC_DISC_WAIT); hideFlag(vipmat);
        ItemBuilder mvp = new ItemBuilder(mvpmat).setName(ChatColor.GREEN+""+ChatColor.BOLD+"MVP " + ChatColor.GRAY + 450 + " Credits")
                .setLore(
                        gr+"〡Enderchest access",
                        gr+"〡Hat command",
                        gr+"〡Nickname command",
                        gr+"〡Votekick command",
                        gr+"〡Invsee command",
                        gr+"〡Vanish command",
                        gr+"〡RGB Armor Colors",
                        gr+"〡Durable Blocks",
                        gr+"〡Anti-spam Bypass",
                        gr+"〡No Lag-back",
                        gr+"〡RGB Blocks",
                        gr+"〡VIP++ discord rank"
                );
        createShopButton(mvp, 12, 250);
        ChatColor go = ChatColor.GOLD; ItemStack mvp2mat = new ItemStack(Material.MUSIC_DISC_PIGSTEP); hideFlag(vipmat);
        ItemBuilder mvp2 = new ItemBuilder(mvp2mat).setName(ChatColor.GOLD+""+ChatColor.BOLD+"MVP+ " + ChatColor.GRAY + 950 + " Credits")
                .setLore(
                        go+"〡Enderchest access",
                        go+"〡Hat command",
                        go+"〡Nickname command",
                        go+"〡Votekick command",
                        go+"〡Invsee command",
                        go+"〡Vanish command",
                        go+"〡RGB Armor Colors",
                        go+"〡Durable Blocks",
                        go+"〡Anti-spam Bypass",
                        go+"〡No Lag-back",
                        go+"〡RGB Blocks",
                        go+"〡VIP++ discord rank"
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
