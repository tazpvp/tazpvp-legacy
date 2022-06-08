package net.tazpvp.tazpvp.Cosmetics.Particle.Particles;

import net.tazpvp.tazpvp.Cosmetics.Particle.ParticleEffect;
import net.tazpvp.tazpvp.Utils.Functionality.ItemFactory;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Random;

public class CrushedCandyCaneParticle extends ParticleEffect {
    private final Player player;
    private int step;

    public CrushedCandyCaneParticle(Player player) {
        this.player = player;
    }

    @Override
    public void onUpdate() {
        if (step > 360) {
            step = 0;
        }
        Location center = player.getEyeLocation().add(0, 0.6, 0);
        double inc = (2 * Math.PI) / 20;
        double angle = step * inc;
        double x = Math.cos(angle) * 1.1f;
        double z = Math.sin(angle) * 1.1f;
        center.add(x, 0, z);
        for (int i = 0; i < 15; i++) {
            player.getWorld().spawnParticle(Particle.ITEM_CRACK, player.getEyeLocation(), 1, 0.2d, 0.2d, 0.2d,
                    0, ItemFactory.getRandomDye());
        }
        step++;
    }

    public static byte getRandomColor() {
        int random = new Random().nextInt(100);
        if (random > 98) {
            return (byte) 2;
        } else if (random > 49) {
            return (byte) 1;
        } else {
            return (byte) 15;
        }
    }
}
