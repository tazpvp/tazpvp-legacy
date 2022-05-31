package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.commandmanager.CommandHook;

import java.util.ArrayList;
import java.util.List;

public class NpcCMD {
    @CommandHook("npc_create")
    public void npcCMD(Player p, String name, String profession, String type, int id, EntityType entity) {
        if (entity == EntityType.VINDICATOR){
            Vindicator ban = (Vindicator) p.getWorld().spawnEntity(p.getLocation(), EntityType.VINDICATOR);
            ban.setAI(false);
            ban.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
            ban.setInvulnerable(true);
            ban.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER, id);
            p.sendMessage("NPC created!");
        } else if (entity == EntityType.WANDERING_TRADER) {
            WanderingTrader shop = (WanderingTrader) p.getWorld().spawnEntity(p.getLocation(), EntityType.WANDERING_TRADER);
            shop.setAI(false);
            shop.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
            shop.setInvulnerable(true);
            shop.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER, id);
            p.sendMessage("NPC created!");
        } else if (entity == EntityType.VILLAGER) {
            Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
            v.setAI(false);
            v.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
            v.setProfession(Villager.Profession.valueOf(profession.toUpperCase()));
            v.setVillagerType(Villager.Type.valueOf(type.toUpperCase()));
            v.setInvulnerable(true);
            v.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER, id);
            p.sendMessage("NPC created!");
        } else {
            p.sendMessage("no");
        }
    }

    @CommandHook("npc_rename")
    public void npcRenameCMD(Player p, String name, String New){
        p.getWorld().getEntities().stream().filter(e -> e instanceof Villager).forEach(e -> {
            if (e.getCustomName() != null) {
                if (e.getCustomName().equalsIgnoreCase(name)) {
                    e.setCustomName(ChatColor.translateAlternateColorCodes('&', New));
                }
            }
        });
        p.sendMessage("Villager removed!");
    }

    @CommandHook("npc_list")
    public void npcListCMD(Player p){
        List<ArmorStand> npcs = new ArrayList<>();
        p.getWorld().getEntities().stream().filter(e -> e instanceof Villager).forEach(e -> {
            if (e.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER)) {
                npcs.add((ArmorStand) e);
            }
            p.sendMessage();
        });
        for (ArmorStand a : npcs) {
            p.sendMessage(a.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER) + ": " + a.getCustomName());
        }
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
        p.getWorld().getEntities().stream().filter(e -> e instanceof Vindicator).forEach(e -> {
            if (e.getCustomName() != null) {
                if (e.getCustomName().equalsIgnoreCase(name)) {
                    e.remove();
                }
            }
        });
        p.getWorld().getEntities().stream().filter(e -> e instanceof WanderingTrader).forEach(e -> {
            if (e.getCustomName() != null) {
                if (e.getCustomName().equalsIgnoreCase(name)) {
                    e.remove();
                }
            }
        });
        p.sendMessage("NPC removed!");
    }
}