package net.tazpvp.tazpvp.Utils.Functionality;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.Random;

public class PerkUtils {
    static Random rand = new Random();

    public static void excavatorPerk(Player p){
        if (!Tazpvp.perkManager.getExcavatorPerk(p)) return;
        Tazpvp.statsManager.addExp(p, 1);
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        if (Tazpvp.statsManager.checkLevelUp(p)) {
            Tazpvp.statsManager.levelUp(p, 1);
        }
    }

    public static void archerPerk(Player p) {
        if (!Tazpvp.perkManager.getArcherPerk(p)) return;
        if (rand.nextInt(11) <= 1) {
            p.getInventory().addItem(new ItemBuilder(Material.ARROW));
        }
    }

    public static void builderPerk(Player p, ItemStack item) {
        if (!Tazpvp.perkManager.getBuilderPerk(p)) return;
        if (rand.nextInt(11) <= 1) {
            p.getInventory().addItem(item);
        }
    }

    public static void gobblePerk(Player p) {
        if (!Tazpvp.perkManager.getGobblePerk(p)) return;
        p.setFoodLevel(20);
    }

    public static void agilityPerk(Player p) {
        if (!Tazpvp.perkManager.getAgilityPerk(p)) return;
        p.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SPEED, 20*3, 2));
    }

    public static void tankPerk(Player p) {
        if (!Tazpvp.perkManager.getTankPerk(p)) return;
        if (rand.nextInt(11) <= 1) {
            p.setAbsorptionAmount(4);
        }
    }

}
