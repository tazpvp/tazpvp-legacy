package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Utils.Functionality.PerkUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class BowShootEvent implements Listener {
    @EventHandler
    public void onShoot(ProjectileLaunchEvent e) {
        if (e.getEntity() instanceof Arrow) {
            if (e.getEntity().getShooter() instanceof Player p) {
                if (p.hasMetadata("spectating")) {
                    if (spectating(p)) {
                        e.setCancelled(true);
                    }
                }
                PerkUtils.archerPerk(p);
            }
        }
    }

    public boolean spectating(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("spectating");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }
}
