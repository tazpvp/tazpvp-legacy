package net.tazpvp.tazpvp.Utils.Custom.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Items {
    GRAPPLING_HOOK(ChatColor.WHITE + "Grappling Hook", Material.FISHING_ROD),
    BUTTER(ChatColor.WHITE + "Butter", Material.GOLD_INGOT),
    AGILITY(ChatColor.WHITE + "Agility", Material.ENDER_EYE),
    EXTINGUISH(ChatColor.WHITE + "Extinguish Flower", Material.RED_TULIP),
    SQUID_LAUNCHER(ChatColor.WHITE + "Tactical Squid Launcher", Material.GOLDEN_HOE),
    RIDEPEARL(ChatColor.WHITE + "Ridable Pearl", Material.ENDER_EYE),
    FIREGUN(ChatColor.WHITE + "Fireballz Gun", Material.FIRE_CHARGE),
    LEVFEATHER(ChatColor.WHITE + "Lethal Injection", Material.FEATHER),
    FIREBALL(ChatColor.WHITE + "Fireballz", Material.FIRE_CHARGE),
    HAMMER(ChatColor.WHITE + "Hammer", Material.GOLDEN_SHOVEL),
    CREDITPAPER(ChatColor.WHITE + "Paper", Material.PAPER),
    GHEAD(ChatColor.GOLD + "Golden Head", Material.SKELETON_SKULL);

    public final String display;
    public final Material item;
    Items(String name, Material item) {
        this.display = name;
        this.item = item;
    }
}