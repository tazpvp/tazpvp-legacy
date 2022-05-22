package net.tazpvp.tazpvp.Events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class BlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.SURVIVAL) {
            Block b = e.getBlock();
            if (!isPlayerPlaced(b)) {
                e.setCancelled(true);
            }
        }
    }

    public boolean isPlayerPlaced(Block b){
        List<MetadataValue> metaDataValues = b.getMetadata("PlayerPlaced");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }

}
