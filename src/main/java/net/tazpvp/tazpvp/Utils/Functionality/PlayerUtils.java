package net.tazpvp.tazpvp.Utils.Functionality;

import net.tazpvp.tazpvp.Tazpvp;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import java.io.IOException;
import java.net.URL;

import static net.tazpvp.tazpvp.Utils.Functionality.ChatUtils.sendSurround;

public class PlayerUtils {
    public static void healPlayer(Player p) {
        p.setHealth(getMaxHealth(p));
        p.setFoodLevel(20);
        p.setExhaustion(0.0f);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
    }

    public static void setMaxHealth(Player p, double maxHealth) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
    }
    public static double getMaxHealth(Player p) {
        return p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    }

    public static void kitPlayer(Player p) {
        p.getInventory().clear();

        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack bow = new ItemStack(Material.BOW);
        ItemStack pickaxe = new ItemStack(Material.WOODEN_PICKAXE);
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 16);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 5);
        ItemStack block = new ItemStack(Material.OAK_PLANKS, 64);
        ItemStack arrow = new ItemStack(Material.ARROW, 32);
        
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.setUnbreakable(true);
        helmet.setItemMeta(helmetMeta);
        ItemMeta chestplateMeta = chestplate.getItemMeta();
        chestplateMeta.setUnbreakable(true);
        chestplate.setItemMeta(chestplateMeta);
        ItemMeta leggingsMeta = leggings.getItemMeta();
        leggingsMeta.setUnbreakable(true);
        leggings.setItemMeta(leggingsMeta);
        ItemMeta bootsMeta = boots.getItemMeta();
        bootsMeta.setUnbreakable(true);
        boots.setItemMeta(bootsMeta);
        ItemMeta swordMeta = sword.getItemMeta();
        swordMeta.setUnbreakable(true);
        swordMeta.setDisplayName(ChatColor.WHITE + "Poor Sword");
        sword.setItemMeta(swordMeta);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setUnbreakable(true);
        bow.setItemMeta(bowMeta);
        ItemMeta pickaxeMeta = pickaxe.getItemMeta();
        pickaxeMeta.setUnbreakable(true);
        pickaxe.setItemMeta(pickaxeMeta);

        

        PlayerInventory inv = p.getInventory();
        inv.setHelmet(helmet);
        inv.setChestplate(chestplate);
        inv.setLeggings(leggings);
        inv.setBoots(boots);
        inv.setItem(0, sword);
        inv.setItem(1, bow);
        inv.setItem(2, pickaxe);
        inv.setItem(3, gapple);
        inv.setItem(4, steak);
        inv.setItem(5, block);
        inv.setItem(8, arrow);
    }

    public static String ignToUUID(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/"+name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if(UUIDJson.isEmpty()) return "error";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            String UUID = UUIDObject.get("id").toString();
            UUID = java.util.UUID.fromString(
                    UUID.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5")
            ).toString();
            return UUID;
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }
    public static String setLPRankCommand(Player p, String rank) {
        return "pex user " + p.getName() + " group set " + rank;
    }

    public static void rebirthPlayer(Player p) {
        Inventory inv = p.getInventory();
        Inventory enc = p.getEnderChest();

        if(Tazpvp.punishmentManager.isBanned(p)) {
            sendSurround(p, ChatColor.RED + "" + ChatColor.BOLD + "UNBANNED " + ChatColor.WHITE + "You are now released from the dead.");
        }
        Tazpvp.statsManager.initPlayer(p);
        Tazpvp.playerWrapperStatsManager.wipeSwords(p);
        Tazpvp.perkManager.initPerks(p);
        Tazpvp.punishmentManager.removeBan(p);
        Tazpvp.punishmentManager.removeMute(p);
        enc.clear();
        inv.clear();

        p.setHealth(20);
        p.setFoodLevel(20);
        Tazpvp.boolManager.setHasRebirthed(p, true);

        for (Player op : Bukkit.getOnlinePlayers()) {
            sendSurround(op, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "REBIRTH" + ChatColor.WHITE + p + ChatColor.DARK_PURPLE + " rebirthed as a stronger warrior");
            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
            p.sendTitle(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "REBIRTH", ChatColor.LIGHT_PURPLE + "You awake as a new person.", 10, 100, 10);
        }
    }

    public static void skullPlayer(Player p) {
        ItemStack head = new ItemBuilder(ItemUtils.skull(p)).setName(ChatColor.YELLOW + p.getName() + "'s head");
        p.getInventory().addItem(head);
    }
//
//    // https://www.geeksforgeeks.org/insert-a-string-into-another-string-in-java/
//    public static String insertString(String originalString, String stringToBeInserted, int index) {
//        String newString = new String();
//        for (int i = 0; i < originalString.length(); i++) {
//            newString += originalString.charAt(i);
//            if (i == index) {
//                newString += stringToBeInserted;
//            }
//        }
//        return newString;
//    }
}
