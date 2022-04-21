package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import redempt.redlib.commandmanager.CommandHook;

public class npcCMD {
    @CommandHook("npc_create")
    public void npcCMD(Player p, String name) {
        Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
        v.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        p.sendMessage("Villager created!");
    }
}