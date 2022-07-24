package net.tazpvp.tazpvp.Utils.Functionality;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.Random;

import static net.tazpvp.tazpvp.Utils.Custom.Sword.GetRandomSword.getRandomSword;
import static net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder.maekItem;
import static net.tazpvp.tazpvp.Utils.Functionality.AchieveUtils.Achieve;
import static net.tazpvp.tazpvp.Utils.Functionality.ChatUtils.sendSurround;

public class PerkUtils {
    static Random rand = new Random();

    public static void excavatorPerk(Player p){
        if (!Tazpvp.perkManager.getExcavatorPerk(p)) return;
        Tazpvp.statsManager.addExp(p, 1);
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        if (Tazpvp.statsManager.checkLevelUp(p)) {
            Tazpvp.statsManager.levelUp(p, 1);
            if ((Tazpvp.statsManager.getLevel(p) % 10) == 0) {
                Items unlockedItem;
                if (Tazpvp.statsManager.getLevel(p) <= 30) {
                    unlockedItem = getRandomSword(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
                } else if (Tazpvp.statsManager.getLevel(p) <= 50) {
                    unlockedItem = getRandomSword(ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON");
                } else if (Tazpvp.statsManager.getLevel(p) <= 80) {
                    unlockedItem = getRandomSword(ChatColor.AQUA + "" + ChatColor.BOLD + "RARE");
                } else if (Tazpvp.statsManager.getLevel(p) <= 100) {
                    unlockedItem = getRandomSword(ChatColor.RED + "" + ChatColor.BOLD + "EPIC");
                } else {
                    unlockedItem = getRandomSword(ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY");
                }
                p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                p.sendTitle(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "UNLOCKED", unlockedItem.getName(), 10, 80, 10);
                sendSurround(p, ChatColor.DARK_AQUA + " You unlocked: " + ChatColor.BOLD + unlockedItem.getName());
                p.getInventory().addItem(maekItem(unlockedItem));
                if (Tazpvp.statsManager.getLevel(p) == 100) {
                    Achieve(p, "Legend", "legend", 8, 235);
                }
            }
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
