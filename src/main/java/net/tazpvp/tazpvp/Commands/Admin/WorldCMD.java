package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.World;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;
import org.bukkit.ChatColor;

public class WorldCMD {
    @CommandHook("world_list")
    public void worldList(Player p) {
        p.sendMessage(ChatColor.GOLD + "Worlds:");
        for (World w : p.getServer().getWorlds()) {
            p.sendMessage(ChatColor.RED + " - " + w.getName());
        }
    }
    @CommandHook("world_tp")
    public void worldTp(Player p, World world) {
        p.sendMessage(ChatColor.GOLD + "Teleporting to " + world.getName());
        p.teleport(world.getSpawnLocation());
    }
}
