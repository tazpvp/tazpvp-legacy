package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CMDCancelEvent implements Listener {
    @EventHandler
    public void onCMD(org.bukkit.event.player.PlayerCommandPreprocessEvent e){
        if (Tazpvp.punishmentManager.isBanned(e.getPlayer())) {
            if (!e.getMessage().startsWith("/pm")){
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "You cannot use commands while you are banned.");
            }
        }
    }
}
