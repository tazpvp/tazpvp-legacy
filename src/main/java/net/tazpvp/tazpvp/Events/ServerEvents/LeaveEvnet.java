package net.tazpvp.tazpvp.Events.ServerEvents;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvnet implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + e.getPlayer().getName());
        Tazpvp.playerWrapperStatsManager.setPlayerWrapper(e.getPlayer(), Tazpvp.playerWrapperMap.get(e.getPlayer().getUniqueId()));
        Tazpvp.playerWrapperMap.remove(e.getPlayer().getUniqueId());
    }
}
