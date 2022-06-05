package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Utils.Functionality.PerkUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class BowShootEvent implements Listener {
    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof Arrow) {
            PerkUtils.archerPerk((Player) ((Arrow) e.getEntity()).getShooter());
        }
    }
}
