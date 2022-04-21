package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import redempt.redlib.commandmanager.CommandHook;

public class NpcCMD {
    @CommandHook("npc_create")
    public void npcCMD(Player p, String name, String profession, String type) {
        Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
        v.setAI(false);
        v.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        v.setProfession(Villager.Profession.valueOf(profession.toUpperCase()));
        v.setVillagerType(Villager.Type.valueOf(type.toUpperCase()));
        p.sendMessage("Villager created!");
    }
    @CommandHook("npc_remove")
    public void npcRemoveCMD(Player p, String name) {
        p.getWorld().getEntities().stream().filter(e -> e instanceof Villager).forEach(e -> {
            if (e.getCustomName() != null) {
                if (e.getCustomName().equalsIgnoreCase(name)) {
                    e.remove();
                }
            }
        });
        p.sendMessage("Villager removed!");
    }
}