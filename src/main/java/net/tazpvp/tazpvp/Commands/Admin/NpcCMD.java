package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.PdcUtils;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.commandmanager.CommandHook;

import java.util.ArrayList;
import java.util.List;

public class NpcCMD implements CommandListener {
    @CommandHook("npc_create")
    public void npcCMD(Player p, String name, String profession, String type, int id, int entity, int pitch) {
        if (entity == 1){
            Vindicator ban = (Vindicator) p.getWorld().spawnEntity(p.getLocation(), EntityType.VINDICATOR);
            ban.setAI(false);
            newNPC(p, ban, name, id, pitch);
        } else if (entity == 2) {
            WanderingTrader shop = (WanderingTrader) p.getWorld().spawnEntity(p.getLocation(), EntityType.WANDERING_TRADER);
            shop.setAI(false);
            newNPC(p, shop, name, id, pitch);
        } else if (entity == 3) {
            Villager v = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
            v.setAI(false);
            v.setProfession(Villager.Profession.valueOf(profession.toUpperCase()));
            v.setVillagerType(Villager.Type.valueOf(type.toUpperCase()));
            newNPC(p, v, name, id, pitch);
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
        p.getWorld().getEntities().stream().filter(e -> e instanceof Villager || e instanceof Vindicator || e instanceof WanderingTrader).forEach(e -> {
            if (e.getCustomName() != null) {
                if (e.getCustomName().equalsIgnoreCase(name)) {
                    e.remove();
                }
            }
        });
        p.sendMessage("NPC removed!");
    }

    public void newNPC(Player p, Entity e, String name, int id, int pitch){
        e.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        e.setInvulnerable(true);
        e.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER, id);
        e.getPersistentDataContainer().set(PdcUtils.pitchKey, PersistentDataType.INTEGER, pitch);
        p.sendMessage("NPC created!");
    }
}