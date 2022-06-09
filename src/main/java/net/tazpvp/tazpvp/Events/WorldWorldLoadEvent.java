package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Utils.NPCS.NpcUtils;
import net.tazpvp.tazpvp.Utils.NPCS.Villagers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class WorldWorldLoadEvent implements Listener {
    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        if (e.getWorld().getName().equals("ban")) {
            NpcUtils.spawn(Villagers.REBIRTH2);
        }
    }
}
