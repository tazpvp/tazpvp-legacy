package net.tazpvp.tazpvp.Utils.Functionality;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AchieveUtils {
    public static void Achieve(Player p, String name, String s, int shards, int coins) {
        if (!Tazpvp.achievementManager.getAchievement(p, s)) {
            Tazpvp.achievementManager.setAchievement(p, s, true);
            Tazpvp.statsManager.addShards(p, shards);
            Tazpvp.statsManager.addCoins(p, coins);
            p.sendMessage("");
            Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + ChatColor.DARK_AQUA + " has completed the " + ChatColor.AQUA + name + ChatColor.DARK_AQUA + " achievement!");
            p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "REWARD " + ChatColor.GRAY + shards + " Shards, " + coins + " Coins");
            p.sendMessage("");
            p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
        }
    }
}
