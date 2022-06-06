package net.tazpvp.tazpvp.Utils.Variables;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class configUtils {
    public static final Location spawn = new Location(Bukkit.getWorld(Tazpvp.configFile.getString("spawn.world")),
            Tazpvp.configFile.getDouble("spawn.x"),
            Tazpvp.configFile.getDouble("spawn.y"),
            Tazpvp.configFile.getDouble("spawn.z"),
            (float)Tazpvp.configFile.getDouble("spawn.yaw"),
            (float)Tazpvp.configFile.getDouble("spawn.pitch"));

}
