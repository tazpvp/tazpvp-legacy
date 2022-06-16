package net.tazpvp.tazpvp.Utils.Variables;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class configUtils {
    public static final Location spawn = new Location(Bukkit.getWorld(Tazpvp.config.getString("spawn.world")),
            Tazpvp.config.getDouble("spawn.x"),
            Tazpvp.config.getDouble("spawn.y"),
            Tazpvp.config.getDouble("spawn.z"),
            (float)Tazpvp.config.getDouble("spawn.yaw"),
            (float)Tazpvp.config.getDouble("spawn.pitch"));

}
