package net.tazpvp.tazpvp.Mobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public enum MobLocations {
    ZOMBIE(EntityType.ZOMBIE, new Location(Bukkit.getWorld("arena"), -23.5, 86, 212.5, 0, 0), 1);

    public EntityType mob() {
        return mob;
    }

    public Location location() {
        return location;
    }

    public int id() {
        return id;
    }

    private EntityType mob;
    private Location location;
    private int id;

    MobLocations(EntityType mob, Location location, int id) {
        this.mob = mob;
        this.location = location;
        this.id = id;
    }
}
