package net.tazpvp.tazpvp.Utils.Custom.ItemManager.items;

import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Items;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.SwordItem;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Temp2Item extends SwordItem {
    public Temp2Item() {
        super(Items.TEMPLATE2);
    }

    @Override
    public boolean execute(Player p, String name, EntityDamageByEntityEvent e) {
        if (super.execute(p, name, e)) {
            e.setDamage(Items.TEMPLATE2.getDamage());
            return true;
        }
        return false;
    }
}
