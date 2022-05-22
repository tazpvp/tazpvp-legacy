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
import org.bukkit.scheduler.BukkitRunnable;

public class buyRank implements Listener {
    public static int type;
    public static String rank;
    public static Player recipient = null;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        Player p = e.getPlayer();
        if (Tazpvp.Buying.contains(p.getUniqueId())){
            e.setCancelled(true);
            if (msg.contains("buy")){
                type = 1;
                Tazpvp.Buying.remove(p.getUniqueId());
                RankGive(p, p);
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
            RankGive(p, recipient);
            Tazpvp.Gifting.remove(p);
        }
    }

    public static void RankBuying(Player p) {
        p.sendMessage("Gift or buy?");
        Tazpvp.Buying.add(p.getUniqueId());
        new BukkitRunnable() {
            @Override
            public void run(){
                if (Tazpvp.Buying.contains(p.getUniqueId())) {
                    Tazpvp.Buying.remove(p.getUniqueId());
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
