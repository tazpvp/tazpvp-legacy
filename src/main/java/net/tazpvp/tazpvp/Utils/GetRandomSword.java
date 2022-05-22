package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRandomSword {
    public static String whichRarity() {
        int x = new Random().nextInt(100);
        if (x <= 50) {
            return "C";
        } else if (x <= 75) {
            return "U";
        } else if (x <= 90) {
            return "R";
        } else if (x <= 98) {
            return "E";
        } else {
            return "L";
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
