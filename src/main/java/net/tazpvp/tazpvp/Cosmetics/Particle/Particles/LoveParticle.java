package net.tazpvp.tazpvp.Cosmetics.Particle.Particles;

import net.tazpvp.tazpvp.Cosmetics.Particle.ParticleEffect;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class LoveParticle extends ParticleEffect {
    private final Player player;
    public LoveParticle(Player p) {
        this.player = p;
    }

    @Override
    public void onUpdate() {
        player.getWorld().spawnParticle(Particle.HEART, player.getEyeLocation(), 2, 0.5d, 0.5d, 0.5d);
    }
}
