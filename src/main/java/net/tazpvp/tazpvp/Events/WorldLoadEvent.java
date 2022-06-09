package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Utils.NPCS.NpcUtils;
import net.tazpvp.tazpvp.Utils.NPCS.Villagers;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static net.tazpvp.tazpvp.Utils.NPCS.NpcUtils.spawn;

public class WorldLoadEvent implements Listener {
    @EventHandler
    public void onWorldLoad(org.bukkit.event.world.WorldLoadEvent e) {
        Bukkit.getLogger().info("woreddddd " + e.getWorld().getName());
        World ew = e.getWorld();
        for (Villagers vil : Villagers.values()) {
            World w = vil.location.getWorld();
            if (w == ew) {
                spawn(vil);
            }
        }
    }
}
