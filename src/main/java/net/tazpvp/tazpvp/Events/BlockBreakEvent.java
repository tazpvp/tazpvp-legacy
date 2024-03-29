package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static net.tazpvp.tazpvp.Utils.Functionality.OreMine.oreRespawn;
import static net.tazpvp.tazpvp.Utils.Functionality.PerkUtils.excavatorPerk;

public class BlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.SURVIVAL) {
            Block b = e.getBlock();
            Material block = b.getType();
            if (Tazpvp.blocks.containsKey(block)) {
                Material smeltedForm = Tazpvp.blocks.get(block);
                excavatorPerk(p);
                if (block == Material.DEEPSLATE_GOLD_ORE) oreRespawn(p, block, smeltedForm, 350L, b, e);
                else if (block == Material.DEEPSLATE_REDSTONE_ORE) oreRespawn(p, block, smeltedForm, 350L, b, e);
                else if (block == Material.DEEPSLATE_IRON_ORE) oreRespawn(p, block, smeltedForm, 350L, b, e);
                else if (block == Material.DEEPSLATE_LAPIS_ORE) oreRespawn(p, block, smeltedForm, 350L, b, e);
                else if (block == Material.DEEPSLATE_EMERALD_ORE) oreRespawn(p, block, smeltedForm, 350L, b, e);
            } else if(p.getWorld().getName().equals("arena") || p.getWorld().getName().equals("ban")) {
                if (!b.hasMetadata("PlayerPlaced")) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
