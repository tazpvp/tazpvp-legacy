package net.tazpvp.tazpvp.Enchantments;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.enchants.CustomEnchant;
import redempt.redlib.enchants.trigger.EnchantTrigger;

public class MultiShotENCH extends CustomEnchant {
    public MultiShotENCH() {
        super("mutlishot", 3);
        addTrigger(EnchantTrigger.SHOOT_ARROW, (event, level) -> {
            Player p = event.getEntity().getShooter() instanceof Player ? (Player) event.getEntity().getShooter() : null;
            if (p == null) return;
            Arrow a = (Arrow) event.getEntity();
            for (int i = 0; i < level; i++) {
                p.launchProjectile(a.getClass());
            }
        });
    }
}
