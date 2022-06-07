package net.tazpvp.tazpvp.Utils.Custom.Sword;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRandomSword {
    public static String whichRarity() {
        int x = new Random().nextInt(100);
        if (x <= 50) {
            return ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON";
        } else if (x <= 75) {
            return ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON";
        } else if (x <= 90) {
            return ChatColor.AQUA + "" + ChatColor.BOLD + "RARE";
        } else if (x <= 98) {
            return ChatColor.RED + "" + ChatColor.BOLD + "EPIC";
        } else {
            return ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY";
        }
    }

    public static Items getRandomSword() {
        String rarity = whichRarity();
        List<Items> swords = new ArrayList<>();
        for (Items i : Items.values()) {
            if (i.getRarity().equals(rarity)) {
                swords.add(i);
            }
        }
        return swords.get(new Random().nextInt(swords.size()));
    }
}
