package net.tazpvp.tazpvp.NPCS;

import org.bukkit.*;
import org.bukkit.entity.Villager;

public enum Villagers {
    SHOP(ChatColor.GOLD + "" + ChatColor.BOLD + "Maxim", Villager.Profession.FARMER, Villager.Type.SAVANNA, 1, 1, new Location(Bukkit.getWorld("arena"), -7, 101, 8, -135, 0)),
    MENU(ChatColor.RED + "" + ChatColor.BOLD + "Lorenzo", Villager.Profession.CLERIC, Villager.Type.JUNGLE, 3, 1, new Location(Bukkit.getWorld("arena"), 8, 101, 8, 135, 0)),
    MINER(ChatColor.YELLOW + "" + ChatColor.BOLD + "Caesar", Villager.Profession.WEAPONSMITH, Villager.Type.JUNGLE, 2, 1, new Location(Bukkit.getWorld("arena"), -1.2, 96, 176.3, -154, 0)),
    SHARDS(ChatColor.AQUA + "" + ChatColor.BOLD + "Bub", Villager.Profession.MASON, Villager.Type.SNOW, 5, 1, new Location(Bukkit.getWorld("arena"), 18.5, 80, 103.5, -90, 0)),
    REBIRTH(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Rigel", Villager.Profession.FLETCHER, Villager.Type.SWAMP, 6, 1, new Location(Bukkit.getWorld("arena"), -9, 101, -12, -45, 0)),
    REBIRTH2(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Rigel", Villager.Profession.FLETCHER, Villager.Type.SWAMP, 6, 1, new Location(Bukkit.getWorld("ban"), -1.5, 78, 11, -180, 0)),
    BOW(ChatColor.GREEN + "" + ChatColor.BOLD + "Hrank", Villager.Profession.FLETCHER, Villager.Type.PLAINS, 8, 1, new Location(Bukkit.getWorld("arena"), 29.5, 97, 109, 0, 0));

    public final String name;
    public final Villager.Profession profession;
    public final Villager.Type biome;
    public final int id;
    public final int pitch;
    public final Location location;


    //shop id 1
    //upgrade id 2
    //store id 3
    //achievements id 4
    //bub id 5
    //rigel id 6

    Villagers(String name, Villager.Profession profession, Villager.Type biome, int id, int pitch, Location location) {
        this.name = name;
        this.profession = profession;
        this.biome = biome;
        this.id = id;
        this.pitch = pitch;
        this.location = location;
    }
}
