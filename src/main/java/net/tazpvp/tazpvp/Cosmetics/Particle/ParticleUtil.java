package net.tazpvp.tazpvp.Cosmetics.Particle;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public class ParticleUtil {
    public static WeakHashMap<UUID, ParticleEffect> savd = new WeakHashMap<>();
    public static WeakHashMap<Player, ParticleEffect> inUse = new WeakHashMap<>();

    public void save(Player p) {
        savd.put(p.getUniqueId(), inUse.get(p));
        inUse.remove(p);
    }

    public void load(Player p) {
        inUse.put(p, savd.get(p.getUniqueId()));
        savd.remove(p.getUniqueId());
    }

    public void removeAll(Player p) {
        savd.remove(p.getUniqueId());
        inUse.remove(p);
    }
    public void init(Player p, ParticleEffect pe) {
        removeAll(p);
        inUse.put(p, pe);
    }

    public void onTick() {
        for (ParticleEffect pe : inUse.values()) {
            if (pe != null) {
                pe.onUpdate();
            }
        }
    }
}
