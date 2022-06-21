package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CommandEvent implements Listener {
    @EventHandler
    public void onCMD(org.bukkit.event.player.PlayerCommandPreprocessEvent e){
        if (Tazpvp.punishmentManager.isBanned(e.getPlayer())) {
            Bukkit.getLogger().info(e.getMessage());
            for (String cmd : Tazpvp.allowedCmds) {
                if (!Tazpvp.allowedCmds.contains(cmd)) {
                    if (!e.getMessage().startsWith(cmd)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.RED + "You cannot use commands while you are banned.");
                        break;
                    }
                }
            }
        }
    }
}
