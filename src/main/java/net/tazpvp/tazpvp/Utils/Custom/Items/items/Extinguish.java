package net.tazpvp.tazpvp.Utils.Custom.Items.items;

import net.tazpvp.tazpvp.Utils.Custom.Items.ConsumableItem;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Extinguish extends ConsumableItem {
    public Extinguish() {
        super(Items.EXTINGUISH, 3);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }
        p.setFireTicks(0);
        return false;
    }
}
