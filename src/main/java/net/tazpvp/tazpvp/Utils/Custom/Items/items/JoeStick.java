package net.tazpvp.tazpvp.Utils.Custom.Items.items;

import net.tazpvp.tazpvp.Utils.Custom.Items.ClickableItem;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class JoeStick extends ClickableItem implements Listener {
    public JoeStick() {
        super(Items.JOESTICK, 1);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if(p.getInventory().getItemInMainHand().getType() == Items.JOESTICK.item) {
                if (e.getEntity() instanceof Player) {
                    Player target = (Player) e.getEntity();
                    if (target.getName().equalsIgnoreCase("joeyface10")) {
                        target.setVelocity(p.getLocation().getDirection().multiply(5));
                    }
                }
            }
        }0
    }

}
