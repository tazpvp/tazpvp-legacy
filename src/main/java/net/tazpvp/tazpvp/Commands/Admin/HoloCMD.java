package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.commandmanager.CommandHook;

import java.util.ArrayList;
import java.util.List;

public class HoloCMD {
    @CommandHook("holo_create")
    public void holoCreate(Player p, int id, String text) {
        ArmorStand a = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        a.setInvisible(true);
        a.setCanPickupItems(false);
        a.setInvulnerable(true);
        a.setSmall(true);
        a.setHealth(99999999);
        a.setGravity(false);
        a.setCustomNameVisible(true);
        a.setCustomName(ChatColor.translateAlternateColorCodes('&', text));

        a.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER, id);
    }

    @CommandHook("holo_remove")
    public void holoRemove(Player p, int id) {
        p.getWorld().getEntities().stream().filter(e -> e instanceof ArmorStand).forEach(e -> {
            if (e.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER)) {
                if (e.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER) == id) {
                    e.remove();
                }
            }
        });
        p.sendMessage("Hologram removed!");
    }

    @CommandHook("holo_list")
    public void holoList(Player p) {
        List<ArmorStand> holos = new ArrayList<>();
        p.getWorld().getEntities().stream().filter(e -> e instanceof ArmorStand).forEach(e -> {
            if (e.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER)) {
                holos.add((ArmorStand) e);
            }
        });

        for (ArmorStand a : holos) {
            p.sendMessage(a.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER) + ": " + a.getCustomName());
        }
    }

    @CommandHook("holo_tp")
    public void holoTeleport(Player p, int id) {
        p.getWorld().getEntities().stream().filter(e -> e instanceof ArmorStand).forEach(e -> {
            if (e.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER)) {
                if (e.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER) == id) {
                    e.teleport(p);
                }
            }
        });
        p.sendMessage("Teleported " + id + " to you!");
    }
}
