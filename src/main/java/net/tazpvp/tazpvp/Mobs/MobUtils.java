package net.tazpvp.tazpvp.Mobs;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;

public class MobUtils {
    public static WeakHashMap<Entity, MobLocations> mobList = new WeakHashMap<>();


    public static void everySec() {
        if (mobList.size() == 0) {
            return;
        }
        for (Map.Entry<Entity, MobLocations> entry : mobList.entrySet()) {
            Entity e = entry.getKey();
            MobLocations mobL = entry.getValue();
            if (!entry.getValue().boundingBox().contains(e.getBoundingBox())) {
                Mob mob = (Mob) e;
                ((Mob) e).getPathfinder().moveTo(mobL.location());
            }
        }
    }

    public static void doShitFirstTImeYKYK() {
        for (int i = 0; i < 4; i++) {
            new CZombie().createZombie();
        }
    }

    public static void waitAndSpawnNew(double timeToWait, Consumer<Void> consumer) {
        Bukkit.getScheduler().runTaskLater(Tazpvp.getInstance(), () -> {
            consumer.accept(null);
        }, (long) timeToWait * 20);
    }

    public static void clearMobs() {
        for (Map.Entry<Entity, MobLocations> entry : mobList.entrySet()) {
            entry.getKey().remove();
        }
        mobList.clear();
    }
}
