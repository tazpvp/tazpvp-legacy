package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Variables.PdcUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemDropEvent implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        ItemStack i = e.getItemDrop().getItemStack();
        Material m = i.getType();
        if (i.hasItemMeta()){
            NamespacedKey key = PdcUtils.key;
            ItemMeta meta = i.getItemMeta();
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if (container.has(key, PersistentDataType.DOUBLE)){
                if (!p.hasMetadata("triedDrop")) {
                    p.sendMessage(ChatColor.AQUA + "Are you sure you want to drop your sword? Drop again to confirm.");
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    p.setMetadata("triedDrop", new FixedMetadataValue(Tazpvp.getInstance(), true));
                    e.setCancelled(true);
                } else {
                    p.removeMetadata("triedDrop", Tazpvp.getInstance());
                }
            } else if (m.equals(Material.DIAMOND_SWORD)) {
                p.sendMessage(ChatColor.RED + "You cannot drop your starter sword.");
                e.setCancelled(true);
            }
        }
    }
}
