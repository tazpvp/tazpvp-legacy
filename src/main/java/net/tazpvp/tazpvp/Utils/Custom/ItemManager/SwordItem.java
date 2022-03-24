package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class SwordItem extends Item {
    public SwordItem(Items item) {
        super(item);
    }
    @Override
    public boolean execute(Player p, String name, EntityDamageByEntityEvent e) {
        return true;
    }
}
