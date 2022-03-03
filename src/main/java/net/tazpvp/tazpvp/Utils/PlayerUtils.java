package net.tazpvp.tazpvp.Utils;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class PlayerUtils {
    public static void healPlayer(Player p) {
        setMaxHealth(p, 20.0);
        p.setHealth(20);
        p.setFoodLevel(20);
        p.setExhaustion(0.0f);
        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType());
        }
    }

    public static void setMaxHealth(Player p, double maxHealth) {
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
    }
}
