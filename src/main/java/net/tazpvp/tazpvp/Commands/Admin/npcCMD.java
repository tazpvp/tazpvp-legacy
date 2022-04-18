package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Utils.PlayerUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class npcCMD {
    public void npcCMD(CommandSender sender) {
        if (sender instanceof Player p){
            Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
            v.setCustomName("YourCustomName");
            p.sendMessage("Villager created!");
            //TODO: change to redlib and register
        }
    }
}