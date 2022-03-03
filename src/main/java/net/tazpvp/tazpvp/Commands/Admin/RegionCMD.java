package net.tazpvp.tazpvp.Commands.Admin;

import com.sk89q.worldguard.WorldGuard;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegionCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player p && commandSender.hasPermission("tazspree.*")) {
            World w = p.getWorld();
            p.sendMessage(WorldGuard.getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) w).getRegions().toString() + "");
        }
        return true;
    }
}
