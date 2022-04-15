package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.GUI.ShopGUI;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class NPCClickEvent implements Listener {
    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Villager v) {
            Player p = e.getPlayer();
            String name = v.getCustomName();
            openGui(p, name);
        }
    }

    public void openGui(Player p, String name) {
        if (name.equalsIgnoreCase("§a§lShop")) {
            new ShopGUI(p);
        }
    }
}
