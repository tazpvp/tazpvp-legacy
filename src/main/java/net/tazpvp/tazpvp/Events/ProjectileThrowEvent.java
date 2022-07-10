package net.tazpvp.tazpvp.Events;

import org.bukkit.entity.EnderSignal;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileThrowEvent implements Listener {
    @EventHandler
    public void throwEvent(ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof EnderSignal) {
            e.setCancelled(true);
        }
    }
}
