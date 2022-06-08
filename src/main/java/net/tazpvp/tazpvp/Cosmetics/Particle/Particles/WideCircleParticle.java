package net.tazpvp.tazpvp.Cosmetics.Particle.Particles;

import net.tazpvp.tazpvp.Cosmetics.Particle.ParticleEffect;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class WideCircleParticle extends ParticleEffect {

    boolean up;
    float height;
    int step;
    private final Player player;
    private final Color start;
    private final Color end;

    public WideCircleParticle(Player player, Color start, Color end) {
        this.player = player;
        this.start = start;
        this.end = end;
    }

    @Override
    public void onUpdate() {
        doStuff();
    }

    public void doStuff() {
        if (up) {
            if (height < 2)
                height += 0.05f;
            else
                up = false;
        } else {
            if (height > 0)
                height -= 0.05;
            else
                up = true;
        }
        double inc = (2 * Math.PI) / 100;
        double angle = step * inc;
        Vector v = new Vector();
        v.setX(Math.cos(angle) * 1.1);
        v.setZ(Math.sin(angle) * 1.1);
        Particle.DustTransition dust = new Particle.DustTransition(start, end, 1);
        player.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, player.getLocation().clone().add(v).add(0, height, 0), 1, dust);
        step += 4;
    }
}
