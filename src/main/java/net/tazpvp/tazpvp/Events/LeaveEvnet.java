package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.http.WebSocket;

public class LeaveEvnet implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
//        if (Tazpvp.bounty.containsKey(e.getPlayer().getUniqueId())) {
//            Tazpvp.bounty.remove(e.getPlayer().getUniqueId());
//        }
    }
}
