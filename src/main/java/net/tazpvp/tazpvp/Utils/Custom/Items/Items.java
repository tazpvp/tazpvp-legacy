package net.tazpvp.tazpvp.Utils.Custom.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Items {
    AGILITY(ChatColor.AQUA + "Agility", Material.DRAGON_BREATH, 1, "Gives you a speed boost for a short time."),
    EXTINGUISH(ChatColor.AQUA + "Extinguish Flower", Material.BLUE_ORCHID, 2, "Extinguishes yourself."),
    JOESTICK(ChatColor.RED + "Joe Stick", Material.STICK, 3, "Get joey annoyed"),
    RIDEPEARL(ChatColor.AQUA + "Ride Pearl", Material.ENDER_PEARL, 4, "Rideable ender-pearl.");

    public final String name;
    public final String[] lore;
    public final Material item;
    public final double cID;

    Items(String name, Material item, double cID, String... lore) {
        this.name = name;
        this.lore = lore;
        this.item = item;
        this.cID = cID;
    }
}