package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HeadsToShards {
    String prefix = ChatColor.YELLOW + "[NPC] Bub: " + ChatColor.WHITE;
    public void convertHeadsToShards(Player p) {
        if (p.getInventory().getItemInMainHand().getType().equals(Material.PLAYER_HEAD)) {
            ItemStack item = p.getInventory().getItemInMainHand();
            Tazpvp.statsManager.addShards(p, item.getAmount());
            p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            for (int i = 0; i < item.getAmount(); i++) {
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
            p.sendMessage(prefix + "Traded in " + item.getAmount() + " heads for " + item.getAmount() + " shards.");
        } else {
            p.sendMessage(prefix + "I trade head for shards, hold them in your hand next time bud.");
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
        }
    }
}
