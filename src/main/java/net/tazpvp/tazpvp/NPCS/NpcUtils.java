package net.tazpvp.tazpvp.NPCS;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataType;

public class NpcUtils {
    public static void spawn(Villagers vil) {
        Villager v = (Villager) vil.location.getWorld().spawnEntity(vil.location, EntityType.VILLAGER);
        v.setAI(false);
        v.setProfession(vil.profession);
        v.setVillagerType(vil.biome);
        v.setCustomName(vil.name);
        v.setCustomNameVisible(true);
        v.setSilent(true);
        v.setInvulnerable(true);
        v.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER, vil.id);
        v.getPersistentDataContainer().set(PdcUtils.pitchKey, PersistentDataType.INTEGER, vil.pitch);
        Bukkit.getLogger().info(v.getName() +" created");
    }

    public static void remove(int id, World w) {
        w.getEntities().forEach((e) -> {
            if (e.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER)) {
                if (e.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "id"), PersistentDataType.INTEGER) == id) {
                    e.remove();
                }
            }
        });
    }

    public static void removeAll() {
        World w = Bukkit.getWorld("arena");
        World b = Bukkit.getWorld("ban");
        for (Villagers vil : Villagers.values()) {
            remove(vil.id, w);
            remove(vil.id, b);
        }
    }

    public static void spawnAll() {
        for (Villagers vil : Villagers.values()) {
            spawn(vil);
        }
    }

    public static void refresh() {
        World w = Bukkit.getWorld("arena");
        World b = Bukkit.getWorld("ban");
        for (Villagers vil : Villagers.values()) {
            remove(vil.id, w);
            remove(vil.id, b);
            spawn(vil);
        }
    }
}
