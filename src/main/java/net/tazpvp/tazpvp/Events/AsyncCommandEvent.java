package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AsyncCommandEvent implements Listener {
    @EventHandler
    public void onCMD(PlayerCommandPreprocessEvent e){
        if (Tazpvp.punishmentManager.isBanned(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("You is banned!");
        }
    }
}
