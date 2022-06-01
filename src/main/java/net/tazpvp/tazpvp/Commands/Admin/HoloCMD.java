package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.commandmanager.CommandHook;

import java.util.ArrayList;
import java.util.List;

public class HoloCMD implements Listener, CommandListener {
    @CommandHook("holo_create")
    public void holoCreate(Player p, int id, String text) {
        double x =  p.getLocation().getBlock().getLocation().getX();
        double y = p.getLocation().getY();
        double z = p.getLocation().getBlock().getLocation().getZ();
        ArmorStand a = (ArmorStand) p.getWorld().spawnEntity(new Location(p.getWorld(), x + 0.5, y, z + 0.5), EntityType.ARMOR_STAND);
        a.setInvisible(true);
        a.setCanPickupItems(false);
        a.setInvulnerable(true);
        a.setSmall(true);
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

    @CommandHook("holo_edit")
    public void holoRemove(Player p, int id, String text) {
        p.getWorld().getEntities().stream().filter(e -> e instanceof ArmorStand).forEach(e -> {
            if (e.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER)) {
                if (e.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER) == id) {
                    e.setCustomName(ChatColor.translateAlternateColorCodes('&', text));
                }
            }
        });
        p.sendMessage("Hologram removed!");
    }

    @EventHandler
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof ArmorStand) {
            if (e.getRightClicked().getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER)) {
                if (e.getPlayer().hasPermission("tazpvp.holo")) {
                    e.getPlayer().sendMessage("ID: " + e.getRightClicked().getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "hid"), PersistentDataType.INTEGER));
                }
            }
        }
    }
}
