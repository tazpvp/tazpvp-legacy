package net.tazpvp.tazpvp.Utils.Functionality;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;

public class PerkUtils {
    public static void fatPerk(Player p){
        if (!Tazpvp.perkManager.getFatPerk(p)) return;
        if (p.getHealth() > 19) {
            p.setHealthScale(22);
            p.setHealth(22);
        } else {
            p.setHealth(p.getHealth() + 2);
        }
    }
}
