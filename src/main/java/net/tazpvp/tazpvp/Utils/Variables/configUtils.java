package net.tazpvp.tazpvp.Utils.Variables;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class configUtils {
    public static final Location spawn = new Location(Bukkit.getWorld(Tazpvp.getInstance().config.getString("spawn.world")),
            Tazpvp.getInstance().config.getDouble("spawn.x"),
            Tazpvp.getInstance().config.getDouble("spawn.y"),
            Tazpvp.getInstance().config.getDouble("spawn.z"),
            (float)Tazpvp.getInstance().config.getDouble("spawn.yaw"),
            (float)Tazpvp.getInstance().config.getDouble("spawn.pitch"));

}
