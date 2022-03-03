package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.configUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location spawn = configUtils.spawn;

        if (p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + p.getName());
        } else {
            Tazpvp.statsManager.initPlayer(p);
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + p.getName());
            p.sendTitle(ChatColor.GOLD + "Welcome to " + ChatColor.YELLOW + "TazSpree", ChatColor.GOLD + "Type /help to get started", 10, 100, 10);
        }
        p.teleport(spawn);
    }
}
