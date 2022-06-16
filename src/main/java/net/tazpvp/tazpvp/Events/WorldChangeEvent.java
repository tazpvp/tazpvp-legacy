package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Utils.Functionality.IA.ArmorManager;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class WorldChangeEvent implements Listener {
    @EventHandler
    public void onPlayerWorldChange(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getHelmet().containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL)) {
            if (p.getInventory().getHelmet().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 4) {
                ArmorManager.setPlayerContents(p, true);
            }
        }
    }
}
