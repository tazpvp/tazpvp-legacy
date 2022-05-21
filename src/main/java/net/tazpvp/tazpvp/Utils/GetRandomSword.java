package net.tazpvp.tazpvp.Utils;

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
}
