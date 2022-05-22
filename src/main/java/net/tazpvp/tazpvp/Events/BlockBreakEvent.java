package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

import static net.tazpvp.tazpvp.Utils.oreMine.oreRespawn;

public class BlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.SURVIVAL) {
            Block b = e.getBlock();
            Material block = b.getType();
            if (isPlayerPlaced(b)) {
                e.setCancelled(false);
                return;
            } else { e.setCancelled(true);}
            if (Tazpvp.blocks.contains(block)) {
                e.setCancelled(true);
                if (block == Material.DEEPSLATE_GOLD_ORE) { oreRespawn(p, block, Material.RAW_GOLD, 350L, b);
                } else if (block == Material.DEEPSLATE_REDSTONE_ORE) { oreRespawn(p, block, Material.REDSTONE, 350L, b);
                } else if (block == Material.DEEPSLATE_IRON_ORE) { oreRespawn(p, block, Material.RAW_IRON, 350L, b);
                } else if (block == Material.DEEPSLATE_LAPIS_ORE) { oreRespawn(p, block, Material.LAPIS_LAZULI, 350L, b);
                } else if (block == Material.DEEPSLATE_EMERALD_ORE) { oreRespawn(p, block, Material.EMERALD, 350L, b);
                }
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
