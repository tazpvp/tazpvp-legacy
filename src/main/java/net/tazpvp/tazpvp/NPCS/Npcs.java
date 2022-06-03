package net.tazpvp.tazpvp.NPCS;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;

public enum Npcs {
    BUB();

    private final String name;
    private final String profession;
    private final String biome;
    private final int id;
    private final int type;
    private final int pitch;

    Npcs(String name, String profession, String biome, int id, int type, int pitch) {
        this.name = name;
        this.profession = profession;
        this.biome = biome;
        this.id = id;
        this.type = type;
        this.pitch = pitch;
    }
}
