package net.tazpvp.tazpvp.Utils.Custom.Items.items;

import net.tazpvp.tazpvp.Utils.Custom.Items.ConsumableItem;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Agility extends ConsumableItem {
    public Agility() {
        super(Items.AGILITY, 3);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack, double cID) {
        if(super.execute(p, itemStack, cID)) {
            return true;
        }
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*5, 0));
        return false;
    }
}
