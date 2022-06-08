package net.tazpvp.tazpvp.Cosmetics.Particle.Particles;

import net.tazpvp.tazpvp.Cosmetics.Particle.ParticleEffect;
import net.tazpvp.tazpvp.Utils.Functionality.MathUtils;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TwoHorizontalParticle extends ParticleEffect {
    float step = 0;
    private final Player player;
    private final Color start;
    private final Color end;
    public TwoHorizontalParticle(Player p, Color color1, Color color2) {
        this.player = p;
        this.start = color1;
        this.end = color2;
    }

    @Override
    public void onUpdate() {
        for (int i = 0; i < 2; i++) {
            double inc = (2 * Math.PI) / 100;
            double toAdd = 0;
            if (i == 1)
                toAdd = 3.5;
            double angle = step * inc + toAdd;
            Vector v = new Vector();
            v.setX(Math.cos(angle));
            v.setZ(Math.sin(angle));
            if (i == 0) {
                MathUtils.rotateAroundAxisZ(v, 180);
            } else {
                MathUtils.rotateAroundAxisZ(v, 90);
            }
            player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().clone().add(0, 1, 0).add(v), 1, new Particle.DustOptions(Color.RED, 1));
        }
        step += 3;
    }
}
