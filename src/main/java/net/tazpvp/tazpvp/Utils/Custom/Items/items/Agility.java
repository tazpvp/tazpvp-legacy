package net.tazpvp.tazpvp.Utils.Custom.Items.items;

import net.tazpvp.tazpvp.Utils.Custom.Items.ConsumableItem;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Agility extends ConsumableItem {
    public Agility() {
        super(Items.AGILITY, 6);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack, double cID) {
        if(super.execute(p, itemStack, cID)) {
            return true;
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*3, 2));
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
        return false;
    }
}
