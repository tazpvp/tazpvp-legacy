package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.GUI.UnlockSwordGUI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickBlockEvent implements Listener {
    @EventHandler
    public void blockClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            assert b != null;
            if (b.getType() == Material.RESPAWN_ANCHOR) {
                new UnlockSwordGUI(p);
            }
        }
    }
}
