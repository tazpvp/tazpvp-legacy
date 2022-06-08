package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Ranks.RankUtils;
import net.tazpvp.tazpvp.Utils.Ranks.RenameSwordUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;


import static net.tazpvp.tazpvp.Utils.Functionality.IA.ItemStackUtils.hideFlag;

public class ServerStore {
    private final InventoryGUI gui;
    private final TextComponent link = new TextComponent(ChatColor.GREEN + "" + ChatColor.BOLD + "STORE LINK: " + ChatColor.WHITE + "" + ChatColor.BOLD + "CLICK HERE");

    public ServerStore(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 9*5, ChatColor.BLUE + "" + ChatColor.BOLD + "STORE " + ChatColor.DARK_RED + "25% SALE"));
        link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://store.tazpvp.net/"));

        setitems();
        gui.open(p);
    }

    public void createShopButton(ItemBuilder b, int slot, int price, String rank){
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            giftOrBuy(p, price, rank);
        });
        gui.addButton(slot, button);
    }

    public void setitems(){
        gui.fill(0, 9*5, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

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
        createShopButton(vip, 29, 250, "vip");
        ChatColor gr = ChatColor.GREEN; ItemStack mvpmat = new ItemStack(Material.MUSIC_DISC_WAIT); hideFlag(mvpmat);
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
        createShopButton(mvp, 20, 250, "mvp");
        ChatColor go = ChatColor.GOLD; ItemStack mvp2mat = new ItemStack(Material.MUSIC_DISC_PIGSTEP); hideFlag(mvp2mat);
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
        createShopButton(mvp2, 11, 250, "mvp+");
        ItemButton CREDITS = ItemButton.create(new ItemBuilder(Material.CHEST_MINECART).setName(ChatColor.GREEN + "" + ChatColor.BOLD + "BUY CREDITS").setLore(ChatColor.GRAY + "Store link."), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            p.spigot().sendMessage(link);

        });
        gui.addButton(22, CREDITS);

        ItemButton ITEMRENAME = ItemButton.create(new ItemBuilder(Material.ANVIL).setName(ChatColor.RED + "" + ChatColor.BOLD + "Rename Item").setLore(ChatColor.GRAY + "Rank Required."), e -> {
            Player p = (Player) e.getWhoClicked();
            if (RenameSwordUtil.getSwordToRename(p) == null) {
                p.sendMessage(ChatColor.RED + "You do not have a sword to rename.");
            } else {
                RenameSwordUtil.renameChecks(p, RenameSwordUtil.getSwordToRename(p));
            }
        });
        gui.addButton(15, ITEMRENAME);

        ItemButton CUSTOMPREFIX = ItemButton.create(new ItemBuilder(Material.NAME_TAG).setName(ChatColor.BLUE + "" + ChatColor.BOLD + "Custom Prefix").setLore(ChatColor.GRAY + "Rank Required."), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
        });
        gui.addButton(24, CUSTOMPREFIX);

        ItemButton PARTICLES = ItemButton.create(new ItemBuilder(Material.BLAZE_POWDER).setName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Particles!").setLore(ChatColor.GRAY + "Rank Required."), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
        });
        gui.addButton(33, PARTICLES);

        gui.update();
    }

    public void giftOrBuy(Player p, int price, String rank){
        gui.clear();
        gui.fill(0, 9*5, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        createGiftOrBuyBTN(p, new ItemBuilder(Material.SLIME_BLOCK).setName(ChatColor.AQUA + "" + ChatColor.BOLD + "Buy").setLore(ChatColor.DARK_AQUA + "Purchase for yourself."), price, rank, 21, true);

        createGiftOrBuyBTN(p, new ItemBuilder(Material.HONEY_BLOCK).setName(ChatColor.AQUA  + "" + ChatColor.BOLD + "Gift").setLore(ChatColor.GRAY + "Purchase for another person."), price, rank, 23, false);

        gui.update();
    }

    public void createGiftOrBuyBTN(Player p, ItemStack item, int price, String rank, int slot, boolean buying) {
        ItemButton btn = ItemButton.create(item, e -> {
            if (Tazpvp.statsManager.getCoins(p) >= price) {
                if (buying) {
                    Tazpvp.statsManager.addCoins(p, -price);
                    RankUtils.rankBuy(p, rank);
                    p.closeInventory();
                } else {
                    p.closeInventory();
                    RankUtils.rankGift(p, rank, price);
                }
            } else {
                p.sendMessage(ChatColor.RED + "You don't have enough money!");
                p.closeInventory();
            }
        });
        gui.addButton(btn, slot);
    }
}
