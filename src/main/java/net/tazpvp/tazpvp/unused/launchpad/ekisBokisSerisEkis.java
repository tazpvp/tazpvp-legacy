package net.tazpvp.tazpvp.unused.launchpad;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class ekisBokisSerisEkis {
    public void sixtyNine(Player p) {
        for (int i = 0; i < 100; i++) {
            p.getWorld().playEffect(p.getLocation().add(0, i, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
            p.getWorld().playEffect(p.getLocation().add(0, i, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
            p.getWorld().playEffect(p.getLocation().add(0, i, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
            p.getWorld().playEffect(p.getLocation().add(0, i, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
        }
    }
}
