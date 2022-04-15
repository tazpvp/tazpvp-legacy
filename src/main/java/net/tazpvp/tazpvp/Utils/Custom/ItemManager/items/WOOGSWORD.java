package net.tazpvp.tazpvp.Utils.Custom.ItemManager.items;

import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Items;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.SwordItem;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WOOGSWORD extends SwordItem {
    public WOOGSWORD() {
        super(Items.WOOGSWORD);
    }

    @Override
    public boolean execute(Player p, String name, EntityDamageByEntityEvent e) {
        if (super.execute(p, name, e)) {
            e.setDamage(Items.WOOGSWORD.getDamage());
            return true;
        }
        return false;
    }
}
