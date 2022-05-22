package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.GUI.UnlockSwordGUI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickBlockEvent implements Listener {
    public void blockClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        Material block = b.getType();
        if (block == Material.RESPAWN_ANCHOR){
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                new UnlockSwordGUI(p);
            }
        }
    }
}
