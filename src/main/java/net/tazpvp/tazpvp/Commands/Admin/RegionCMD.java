package net.tazpvp.tazpvp.Commands.Admin;

import com.sk89q.worldguard.WorldGuard;
import net.tazpvp.tazpvp.Commands.CommandListener;
import org.bukkit.World;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class RegionCMD implements CommandListener {
    @CommandHook("region")
    public void region(Player p){
        World w = p.getWorld();
        p.sendMessage(WorldGuard.getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) w).getRegions().toString() + "");
    }
}
