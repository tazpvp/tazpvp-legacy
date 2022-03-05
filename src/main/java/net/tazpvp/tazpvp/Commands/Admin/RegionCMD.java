package net.tazpvp.tazpvp.Commands.Admin;

import com.sk89q.worldguard.WorldGuard;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class RegionCMD {
    @CommandHook("region")
    public void region(Player p){
        World w = p.getWorld();
        p.sendMessage(WorldGuard.getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) w).getRegions().toString() + "");
    }
}
