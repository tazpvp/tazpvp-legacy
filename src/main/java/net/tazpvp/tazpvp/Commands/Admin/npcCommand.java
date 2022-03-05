package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.unused.NPC.NPC;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class npcCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p){
            if (p.hasPermission("tazpvp.*")){
                if (args.length == 1){
                    if (args[0].equalsIgnoreCase("spawn")){
                        NPC npc = new NPC(p.getName(), p.getLocation(), UUID.randomUUID().toString());
                        Tazpvp.npcs.add(npc);
                        p.sendMessage("NPC Spawned!");
                    }
                }
            }
        }
        return true;
    }
}
