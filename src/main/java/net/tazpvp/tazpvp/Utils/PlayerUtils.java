package net.tazpvp.tazpvp.Utils;

import org.apache.commons.io.IOUtils;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

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
            UUID = insertString(UUID, "-", 8);
            UUID = insertString(UUID, "-", 13);
            UUID = insertString(UUID, "-", 18);
            UUID = insertString(UUID, "-", 23);
            return UUID;
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }

    // https://www.geeksforgeeks.org/insert-a-string-into-another-string-in-java/
    public static String insertString(String originalString, String stringToBeInserted, int index) {
        String newString = new String();
        for (int i = 0; i < originalString.length(); i++) {
            newString += originalString.charAt(i);
            if (i == index) {
                newString += stringToBeInserted;
            }
        }
        return newString;
    }
}
