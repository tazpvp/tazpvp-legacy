package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;

public class ServerStore {
    public static boolean buying;
    public static int type;
    public static String rank;
    public static Player recipient = null;
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

            RankBuying(p);
            rank = "vip";

        });
        gui.addButton(11, VIP);
        ItemButton MVP = ItemButton.create(new ItemStack(Material.MUSIC_DISC_WAIT), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            RankBuying(p);
            rank = "mvp";
        });
        gui.addButton(12, MVP);
        ItemButton MVP2 = ItemButton.create(new ItemStack(Material.MUSIC_DISC_PIGSTEP), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();

            RankBuying(p);
            rank = "mvp+";
        });
        gui.addButton(13, MVP2);

        gui.update();
    }

    //yes

    public static void RankBuying(Player p) {
        p.sendMessage("Gift or buy?");
        buying = true;
        new BukkitRunnable() {
            @Override
            public void run(){
                if (!buying) {
                    buying = true;
                    p.sendMessage("no msg");
                }
            }
        }.runTaskLater(Tazpvp.getInstance(), 100L);
    }
    public static void RankGive(Player buyer, Player recipient) {
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        if (type == 1) { Bukkit.broadcastMessage(ChatColor.YELLOW + buyer.getName() + ChatColor.GOLD + " just purchased " + ChatColor.YELLOW + "[" +rank.toUpperCase()+ "]" + ChatColor.GOLD + " in the store!");
        } else { Bukkit.broadcastMessage(ChatColor.YELLOW + buyer.getName() + ChatColor.GOLD + " has gifted " + ChatColor.YELLOW + "[" +rank.toUpperCase()+ "]" + ChatColor.GOLD + " to " + ChatColor.YELLOW + recipient.getName()); }
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");

        for (Player pl : Bukkit.getOnlinePlayers()) { pl.playSound(pl.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1); }
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        String command = "luckperms user" + recipient.getName() + " group add " + rank;
        Bukkit.dispatchCommand(console, command);
    }
}
