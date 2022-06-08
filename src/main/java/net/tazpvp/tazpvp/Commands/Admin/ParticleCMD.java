package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Cosmetics.Particle.Particles.WideCircleParticle;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ParticleCMD {
    @CommandHook("particle")
    public void onParticle(Player p, int id, boolean run) {
        if (Tazpvp.particleEffects.containsKey(p)) {
            Tazpvp.particleEffects.remove(p);
        } else {
            Tazpvp.particleEffects.put(p, new WideCircleParticle(p, Color.RED, Color.WHITE));
        }
    }
}
