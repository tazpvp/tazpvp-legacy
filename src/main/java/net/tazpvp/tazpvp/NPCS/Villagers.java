package net.tazpvp.tazpvp.NPCS;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Villager;

public enum Villagers {
    SHOP(ChatColor.GOLD + "" + ChatColor.BOLD + "Maxim", Villager.Profession.ARMORER, Villager.Type.DESERT, 1, 1, new Location());

    private final String name;
    private final Villager.Profession profession;
    private final Villager.Type biome;
    private final int id;
    private final int pitch;
    private final Location location;


    //shop id 1
    //upgrade id 2
    //store id 3
    //achievements id 4
    //bub id 5

    Villagers(String name, Villager.Profession profession, Villager.Type biome, int id, int pitch, Location location) {
        this.name = name;
        this.profession = profession;
        this.biome = biome;
        this.id = id;
        this.pitch = pitch;
        this.location = location;
    }
}
