package net.tazpvp.tazpvp.unused.launchpad;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LaunchPad  implements Listener {

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getBlock().getType() == Material.COAL_BLOCK) {
            p.getLocation().getDirection().multiply(0).setY(6);
        }
    }
}