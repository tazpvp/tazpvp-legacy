package net.tazpvp.tazpvp.Mobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.util.BoundingBox;

public enum MobLocations {
    ZOMBIE(EntityType.ZOMBIE, new Location(Bukkit.getWorld("arena"), -23.5, 86, 212.5, 0, 0), 1, new BoundingBox(-1, 98, 188, -31, 85, 219));

    public EntityType mob() {
        return mob;
    }

    public Location location() {
        return location;
    }

    public int id() {
        return id;
    }

    public BoundingBox boundingBox() {
        return boundingBox;
    }

    private EntityType mob;
    private Location location;
    private int id;
    private BoundingBox boundingBox;

    MobLocations(EntityType mob, Location location, int id, BoundingBox box) {
        this.mob = mob;
        this.location = location;
        this.id = id;
        this.boundingBox = box;
    }
}
