package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.util.List;
import java.util.UUID;

public class TazloadCMD {
    @CommandHook("tazload")
    public void onTazLoad(Player p) {
        Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "                                                             ");
        Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " RESTART " + ChatColor.WHITE + "Server restart in 60 seconds.");
        Bukkit.broadcastMessage(ChatColor.WHITE + "");
        Bukkit.broadcastMessage(ChatColor.GOLD + " IP: " + ChatColor.YELLOW + "tazpvp.net");
        Bukkit.broadcastMessage(ChatColor.GOLD + " DISCORD: " + ChatColor.YELLOW +  "/discord");
        Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "                                                             ");
        for (List<UUID> l : Tazpvp.duelLogic.duels.keySet()) {
            Tazpvp.duelLogic.duelEnd(Bukkit.getPlayer(l.get(0)));
        }
        Tazpvp.AllowBlocks = false;
        Tazpvp.isRestarting = true;
    }
}
