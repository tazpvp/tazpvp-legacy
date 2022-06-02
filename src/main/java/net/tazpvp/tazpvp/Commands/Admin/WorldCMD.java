package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Duels.WorldUtils.WorldManageent;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.commandmanager.CommandHook;

public class WorldCMD implements CommandListener {
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

    @CommandHook("world_create")
    public void worldCreate(Player p, String name, String newName) {
        String s = newName + "_mofo";
        World w = new WorldManageent().cloneWorld(name, s);
        p.teleport(new Location(w, 0, 66, 0));

        new BukkitRunnable() {
            @Override
            public void run() {
                p.sendMessage(ChatColor.GOLD + "deleting world");
                new WorldManageent().deleteWorld(s);
            }
        }.runTaskLater(Tazpvp.getInstance(), 20 * 20);
    }
}
