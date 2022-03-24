package net.tazpvp.tazpvp.Utils.Custom.ItemManager.items;

import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Items;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.SwordItem;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class TempItem extends SwordItem {
    public TempItem() {
        super(Items.TEMAPLTE);
    }

    @Override
    public boolean execute(Player p, ItemStack itemstack, EntityDamageByEntityEvent e) {
        if (super.execute(p, itemstack, e)) {
            e.setDamage(Items.TEMAPLTE.getDamage());
            return true;
        }
        return false;
    }
}
