package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class AfkCMD {
    @CommandHook("afk")
    public void afkCMD(Player p) {
        if (Tazpvp.afkPlayers.contains(p)) {
            Tazpvp.afkPlayers.add(p);
            p.sendMessage(ChatColor.YELLOW + "You are now AFK.");
        } else {
            Tazpvp.afkPlayers.remove(p);
            p.sendMessage(ChatColor.YELLOW + "You are no longer AFK.");
        }
    }
}
