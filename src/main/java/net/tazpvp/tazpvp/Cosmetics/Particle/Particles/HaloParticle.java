package net.tazpvp.tazpvp.Cosmetics.Particle.Particles;

import net.tazpvp.tazpvp.Cosmetics.Particle.ParticleEffect;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class HaloParticle extends ParticleEffect {
    float step = 0;
    private final Player player;
    private final Color start;
    private final Color end;
    public HaloParticle(Player p, Color color, Color color2) {
        this.player = p;
        this.start = color;
        this.end = color2;
    }

    @Override
    public void onUpdate() {
        double inc = (2 * Math.PI) / 100;
        double angle = step * inc;
        Vector v = new Vector();
        Vector v2 = new Vector();
        v.setX(Math.cos(angle) * 0.4);
        v.setZ(Math.sin(angle) * 0.4);
        v2.setX(-(Math.cos(angle) * 0.4));
        v2.setZ(-(Math.sin(angle) * 0.4));
        Particle.DustTransition dust = new Particle.DustTransition(start, end, 1);
        player.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, player.getLocation().clone().add(v).add(0, 2, 0), 1, dust);
        player.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, player.getLocation().clone().add(v2).add(0, 2, 0), 1, dust);
        step += 3;
    }
}
