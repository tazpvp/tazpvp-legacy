package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRandomSword {
    public String whichRarity() {
        int x = new Random().nextInt(100);
        if (x <= 50) {
            return "L";
        } else if (x <= 75) {
            return "E";
        } else if (x <= 90) {
            return "R";
        } else if (x <= 98) {
            return "U";
        } else {
            return "C";
        }
    }

    public Items getRandomSword() {
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
