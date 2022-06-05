package net.tazpvp.tazpvp.Utils.Functionality;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.Random;

public class PerkUtils {
    static Random rand = new Random();
    public static void fatPerk(Player p){
        if (!Tazpvp.perkManager.getFatPerk(p)) return;
        if (p.getHealth() > 19) {
            p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(22);
            p.setHealth(22);
        } else {
            p.setHealth(p.getHealth() + 2);
        }
    }

    public static void excavatorPerk(Player p){
        if (!Tazpvp.perkManager.getExcavatorPerk(p)) return;
        Tazpvp.statsManager.addExp(p, 1);
    }

    public static void archerPerk(Player p) {
        if (!Tazpvp.perkManager.getArcherPerk(p)) return;
        if (rand.nextInt(10) > 6) {
            p.getInventory().addItem(new ItemBuilder(Material.ARROW));
        }
    }

}
