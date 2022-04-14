package net.tazpvp.tazpvp.Events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvent {
    public void onDamage(EntityDamageEvent e) {
        if (e instanceof EntityDamageByEntityEvent pe) {
            Entity enemy = pe.getDamager();
            Player p = (Player) pe.getEntity();
            if (enemy instanceof Player attacker) {
                if (!(p.hasMetadata("NPC") && attacker.hasMetadata("NPC"))) {
                    if(p.getWorld().getName().equalsIgnoreCase("arena")) {
                        if (attacker.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)) {

                        }
                    }
                }
            }

        }
    }
}
