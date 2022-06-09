package net.tazpvp.tazpvp.Cosmetics.Particle.Particles;

import net.tazpvp.tazpvp.Cosmetics.Particle.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StaticSpingParticle extends ParticleEffect {
    private static final List<Color> COLORS = new ArrayList<>();

    static {
        COLORS.add(Color.GREEN);
        COLORS.add(new Color(0, 128, 0));
        COLORS.add(new Color(0, 74, 0));
        COLORS.add(new Color(0, 36, 0));
    }

    private final double RADIUS = 1.1; // radius between player and rods
    private final double ROD_HEIGHT = 1; // Height of each height
    private final int TOTAL_COLUMNS = 8; // Amount of rods (columns)
    private final double BASE_HEIGHT = 0.4; // Added to avoid rods in the floor.
    private final double MIN_HEIGHT = 0; // Min height...
    private final double MAX_HEIGHT = 0.6; // Max height...
    private final double HEIGHT_STEP = 0.03; // Height step...
    private final double MAX_HEIGHT_DIFF = 0.5; // Max height diff between columns
    private final double HEIGHT_DIFF_STEP = 0.04; // Height diff step...

    private boolean heightDirectionUp; // Indicates whether the "overall" height is going up or down
    private boolean hoveringDirectionUp; // Indicates whether the height diff between columns is going up or down (gives dynamism)
    private double height = 0; // Current height
    private double angle = 0; // Current angle
    private double heightDiffFactor = MAX_HEIGHT_DIFF; // Height diff between columns. Variates over time with hoveringDirectionUp.
    private final Location location = new Location(Bukkit.getWorld("arena"), 8.5, 101, -10.5);

    @Override
    public void onUpdate() {
        if (heightDirectionUp) {
            if (height < MAX_HEIGHT) height += HEIGHT_STEP;
            else heightDirectionUp = false;
        } else {
            if (height > MIN_HEIGHT) height -= HEIGHT_STEP;
            else heightDirectionUp = true;
        }
        if (hoveringDirectionUp) {
            if (heightDiffFactor < MAX_HEIGHT_DIFF) heightDiffFactor += HEIGHT_DIFF_STEP;
            else hoveringDirectionUp = false;
        } else {
            if (heightDiffFactor > -MAX_HEIGHT_DIFF) heightDiffFactor -= HEIGHT_DIFF_STEP;
            else hoveringDirectionUp = true;
        }

        drawColumns(height, angle);

        angle += Math.toRadians(1);
    }

    private void drawColumns(Double height, double suppAngle) {
        int cycles = TOTAL_COLUMNS / COLORS.size();
        double workingSpace = 2 * Math.PI / cycles; // Each cycle has its angle span.
        double startAngle = 0; // Step angle for each cycle.
        Vector v = new Vector(0, 0, 0);
        Location loc;

        for (int i = 0; i < cycles; i++) {
            double angleStep = startAngle; // Angle for each column.
            for (int j = 0; j < COLORS.size(); j++) {
                v.setX(Math.cos(angleStep + suppAngle) * RADIUS);
                v.setZ(Math.sin(angleStep + suppAngle) * RADIUS);
                v.setY(BASE_HEIGHT + Math.sin(angleStep * 3) * heightDiffFactor); // The height of the columns is a sine wave.
                loc = location.add(v);

                drawParticleLine(loc, loc.clone().add(0, ROD_HEIGHT, 0), (int) ROD_HEIGHT * 5, COLORS.get(j));

                angleStep += workingSpace / COLORS.size();
                height += (i >= 3 && i <= 5) ? heightDiffFactor : -heightDiffFactor;
            }
            startAngle += workingSpace;
        }
    }

    public void drawParticleLine(Location from, Location to, int particles, Color color) {
        drawParticleLine(from, to, particles, color.getRed(), color.getGreen(), color.getBlue());
    }

    public void drawParticleLine(Location from, Location to, int particles, int r, int g, int b) {
        Location location = from.clone();
        Location target = to.clone();
        Vector link = target.toVector().subtract(location.toVector());
        float length = (float) link.length();
        link.normalize();

        float ratio = length / particles;
        Vector v = link.multiply(ratio);
        Location loc = location.clone().subtract(v);
        int step = 0;
        for (int i = 0; i < particles; i++) {
            if (step >= (double) particles)
                step = 0;
            step++;
            loc.add(v);
            location.getWorld().spawnParticle(Particle.REDSTONE, loc, 128, new Particle.DustOptions(org.bukkit.Color.fromRGB(r, g, b), 1));
        }
    }
}
