package net.tazpvp.tazpvp.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;

public class DrinkMilkEvent implements Listener {
    @EventHandler
    public void onDrnkmil(PlayerItemConsumeEvent e) {
        if (e.getItem().getType() == Material.MILK_BUCKET) {
            for (PotionEffect po : e.getPlayer().getActivePotionEffects()) {
                e.getPlayer().removePotionEffect(po.getType());
            }
            e.setItem(null);
        }
    }
}
