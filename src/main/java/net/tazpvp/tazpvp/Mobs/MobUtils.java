package net.tazpvp.tazpvp.Mobs;

import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class MobUtils {
    public static HashMap<Entity, MobLocations> mobList = new HashMap<>();


    public static void everySec() {
        for (Map.Entry<Entity, MobLocations> entry : mobList.entrySet()) {
            Entity e = entry.getKey();
            MobLocations mobL = entry.getValue();
            if (!entry.getValue().boundingBox().contains(e.getBoundingBox())) {
                e.remove();
                mobList.remove(e);
                new CZombie().createZombie();
            }
        }
    }

    public static void doShitFirstTImeYKYK() {
        for (int i = 0; i < 4; i++) {
            new CZombie().createZombie();
        }
    }
}
