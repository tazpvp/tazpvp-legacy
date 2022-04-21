package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import redempt.redlib.commandmanager.CommandHook;

public class npcCMD {
    @CommandHook("npc_create")
    public void npcCMD(Player p, String name) {
        Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
        v.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        v.setAI(false);
        p.sendMessage("Villager created!");
    }
}