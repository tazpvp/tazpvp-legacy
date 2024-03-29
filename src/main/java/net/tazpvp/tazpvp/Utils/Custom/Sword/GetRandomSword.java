package net.tazpvp.tazpvp.Utils.Custom.Sword;

import org.bukkit.ChatColor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRandomSword {
    public static String whichRarity() {
        int x = new Random().nextInt(101);
        if (x <= 50) {
            return ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON";
        } else if (x <= 80) {
            return ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON";
        } else if (x <= 92) {
            return ChatColor.AQUA + "" + ChatColor.BOLD + "RARE";
        } else if (x <= 97) {
            return ChatColor.RED + "" + ChatColor.BOLD + "EPIC";
        } else {
            return ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY";
        }
    }

    public static Items getRandomSword(@Nullable String rarity) {
        if (rarity == null) {
            rarity = whichRarity();
        }
        List<Items> swords = new ArrayList<>();
        for (Items i : Items.values()) {
            if (i.getRarity().equals(rarity)) {
                swords.add(i);
            }
        }
        return swords.get(new Random().nextInt(swords.size()));
    }
}
