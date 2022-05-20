package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
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

            RankPurchase(p, p, "vip");
        });
        gui.addButton(11, VIP);
        ItemButton MVP = ItemButton.create(new ItemStack(Material.MUSIC_DISC_WAIT), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            RankPurchase(p, p, ChatColor.YELLOW + "mvp");
        });
        gui.addButton(12, MVP);
        ItemButton MVP2 = ItemButton.create(new ItemStack(Material.MUSIC_DISC_PIGSTEP), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            RankPurchase(p, p, ChatColor.YELLOW + "mvp+");
        });
        gui.addButton(13, MVP2);

        gui.update();
    }

    public void RankPurchase(OfflinePlayer buyer, OfflinePlayer recipient, String rank) {
        p.sendMessage("Gift or buy?");
        public boolean buying = true;
        BukkitRunnable
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        if (type == 1) { Bukkit.broadcastMessage(ChatColor.YELLOW + buyer.getName() + ChatColor.GOLD + " just purchased " + ChatColor.YELLOW + "[" +rank.toUpperCase()+ "]" + ChatColor.GOLD + " in the store!");
        } else { Bukkit.broadcastMessage(ChatColor.YELLOW + buyer.getName() + ChatColor.GOLD + " has gifted " + ChatColor.YELLOW + "[" +rank.toUpperCase()+ "]" + ChatColor.GOLD + " to " + ChatColor.YELLOW + recipient.getName());}
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.playSound(pl.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
        }
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "luckperms user" + recipient.getName() + " group add " + rank;
        Bukkit.dispatchCommand(console, command);
        public boolean buying = false;
    }
}
