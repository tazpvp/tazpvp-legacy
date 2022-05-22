package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import org.bukkit.scheduler.BukkitRunnable;
public class BlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (!p.getWorld().getName().equals("arena")) {
            e.setCancelled(true);
        }
    }

//    @EventHandler
//    public void placeBlock(BlockPlaceEvent e){
//        if (e.getPlayer().getGameMode() == GameMode.SURVIVAL && e.getPlayer().getWorld().getName().equalsIgnoreCase("arena")){
//            Player p = e.getPlayer();
//            Block b = e.getBlock();
//            b.setMetadata("PlayerPlaced", new FixedMetadataValue(TazPvP.getInstance(), true));
//        }
//    }
//
//    public boolean isPlayerPlaced(Block b){
//        List<MetadataValue> metaDataValues = b.getMetadata("PlayerPlaced");
//        for (MetadataValue metaDataValue : metaDataValues) {
//            return metaDataValue.asBoolean();
//        }
//        return false;
//    }

}
