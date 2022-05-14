package net.tazpvp.tazpvp.Utils.Custom.Items.items;

import net.tazpvp.tazpvp.Utils.Custom.Items.ConsumableItem;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GHead extends ConsumableItem {

    public GHead() {
        super(Items.GHEAD, 3);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }
        if(p.getHealth() <= 16) {
            p.setHealth(p.getHealth() + 4);
        } else {
            p.setHealth(20);
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*15, 0));
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*5, 0));
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
        return false;
    }
}