package net.tazpvp.tazpvp.Utils.Passive;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static net.tazpvp.tazpvp.Utils.Functionality.ChatUtils.sendSurround;

public class Messages {
    int num = 1;
    public void Text(Plugin plugin){
        new BukkitRunnable(){
            @Override
            public void run() {
                num = (num + 1) % 5;
                Bukkit.broadcastMessage("");
                switch(num){
                    case 0:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "You can join our community by typing " + ChatColor.DARK_AQUA + "/discord");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "You can check out our great deals by typing " + ChatColor.DARK_AQUA + "/buy");
                        break;
                    case 2:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "Want to apply for a staff position? type " + ChatColor.DARK_AQUA + "/apply");
                        break;
                    case 3:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "Want to support us? get our advertisement with " + ChatColor.DARK_AQUA + "/ad");
                        break;
                    case 4:
                        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + " TIP " + ChatColor.GRAY +  "Think they're hacking? Report them with " + ChatColor.DARK_AQUA + "/report");
                        break;
                }
                Bukkit.broadcastMessage("");
            }
        }.runTaskTimer(plugin, 20*4*60, 20*4*60);
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()){
                    sendSurround(p, ChatColor.GREEN + "" + ChatColor.BOLD + "BETA " + ChatColor.DARK_GREEN + "This server is currently in beta testing. Stats will be reset on full release.");
                }
            }
        }.runTaskTimer(plugin, 20*60*2, 20*60*2);
    }
}
