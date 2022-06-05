package net.tazpvp.tazpvp.Utils.Functionality;

import org.bukkit.entity.Player;

public class PerkUtils {
    public static void fatPerk(Player p){
        if (p.getHealth() > 19) {
            p.setHealthScale(22);
            p.setHealth(22);
        } else {
            p.setHealth(p.getHealth() + 2);
        }
    }
}
