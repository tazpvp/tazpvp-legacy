package net.tazpvp.tazpvp.Mobs;

import net.minecraft.core.BlockPosition;
import net.minecraft.world.entity.ai.goal.PathfinderGoalGotoTarget;
import net.minecraft.world.level.IWorldReader;
import net.tazpvp.tazpvp.Mobs.Listeners.TickListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftCreature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

public class MobUtil implements TickListener {
    @Override
    public void onTick() {
        World w = Bukkit.getWorld("arena");
        for (Entity e : w.getEntities()) {
            if (e.getPersistentDataContainer().has(new NamespacedKey(Tazpvp.getInstance(), "mob"), PersistentDataType.INTEGER)) {
                if (e.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "mob"), PersistentDataType.INTEGER) != null) {
                    int id = e.getPersistentDataContainer().get(new NamespacedKey(Tazpvp.getInstance(), "mob"), PersistentDataType.INTEGER);
                    if (id == MobLocations.ZOMBIE.id()) {
                        Location raidus = MobLocations.ZOMBIE.location();
                        if (e.getLocation().distance(raidus) > 15) {
                            e.remove();
                            new CZombie().createZombie();
                        }
                    }
                }
            }
        }
    }

}
