package net.tazpvp.tazpvp.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class EatEvent implements Listener {
    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        if (e.getPlayer().hasMetadata("spectating")) {
            if (spectating(e.getPlayer())) {
                e.setCancelled(true);
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
