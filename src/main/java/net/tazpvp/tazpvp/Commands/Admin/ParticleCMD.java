package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Cosmetics.Particle.Particles.WideCircleParticle;
import net.tazpvp.tazpvp.GUI.MainMenu.SubMenu.ParticlesGUI;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ParticleCMD {
    @CommandHook("particle")
    public void onParticle(Player p, int id, boolean run) {
        new ParticlesGUI(p);
    }
}
