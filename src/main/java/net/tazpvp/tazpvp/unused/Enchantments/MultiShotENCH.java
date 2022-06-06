package net.tazpvp.tazpvp.unused.Enchantments;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import redempt.redlib.enchants.CustomEnchant;
import redempt.redlib.enchants.trigger.EnchantTrigger;

public class MultiShotENCH extends CustomEnchant {
    public MultiShotENCH() {
        super("multishot", 1);
        addTrigger(EnchantTrigger.SHOOT_ARROW, (event, level) -> {
            Player p = event.getEntity().getShooter() instanceof Player ? (Player) event.getEntity().getShooter() : null;
            if (p == null) return;
            p.launchProjectile(Arrow.class);
            

        });
    }
}
