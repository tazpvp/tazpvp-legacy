package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.itemutils.ItemBuilder;

public class buyRank implements Listener {
    public static int type;
    public static String rank;
    public static Player recipient = null;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        Player p = e.getPlayer();
        int price = 1000;
        if (Tazpvp.Buying.containsKey(p.getUniqueId())){
            e.setCancelled(true);
            if (msg.contains("buy")){
                type = 1;
                price = Tazpvp.Buying.get(p.getUniqueId());
                Tazpvp.Buying.remove(p.getUniqueId());
                RankGive(p, p, price);
            } else if (msg.contains("gift")){
                type = 2;
                p.sendMessage("who to gift?");
                Tazpvp.Gifting.add(p.getUniqueId());
                Tazpvp.Buying.remove(p.getUniqueId());
            } else {
                p.sendMessage("That is not a choice.");
            }
        } else if (Tazpvp.Gifting.contains(p)){
            e.setCancelled(true);
            recipient = Bukkit.getPlayer(msg);
            RankGive(p, recipient, price);
            Tazpvp.Gifting.remove(p);
        }
    }

    public static void RankBuying(Player p, int price) {
        p.sendMessage(ChatColor.DARK_PURPLE + "Would you like to " + ChatColor.LIGHT_PURPLE + "'Gift' " + ChatColor.DARK_PURPLE + "or " + ChatColor.LIGHT_PURPLE + "'Buy' " + ChatColor.DARK_PURPLE + "this package?");
        Tazpvp.Buying.put(p.getUniqueId(), price);
        new BukkitRunnable() {
            @Override
            public void run(){
                if (Tazpvp.Buying.containsKey(p.getUniqueId())) {
                    Tazpvp.Buying.remove(p.getUniqueId());
                    p.sendMessage(ChatColor.RED + "You took too long.");
                }
            }
        }.runTaskLater(Tazpvp.getInstance(), 100L);
    }
    public static void RankGive(Player buyer, Player recipient, int price) {
        if (Tazpvp.statsManager.getCredits(buyer) >= price){ Tazpvp.statsManager.addCredits(buyer, -price);
        } else { buyer.sendMessage(ChatColor.RED + "You don't have enough money!"); buyer.playSound(buyer.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1); }

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
