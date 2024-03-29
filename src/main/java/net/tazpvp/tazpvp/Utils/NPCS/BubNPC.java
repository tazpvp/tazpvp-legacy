package net.tazpvp.tazpvp.Utils.NPCS;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BubNPC {
    String prefix = ChatColor.DARK_AQUA + "[NPC] Bub: " + ChatColor.AQUA;
    public void convertHeadsToShards(Player p) {
        if (p.getInventory().getItemInMainHand().getType().equals(Material.PLAYER_HEAD)) {
            ItemStack item = p.getInventory().getItemInMainHand();
            p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            Tazpvp.statsManager.addShards(p, item.getAmount());
            for (int i = 0; i < item.getAmount(); i++) {
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
            p.sendMessage(prefix + "You gave me " + ChatColor.DARK_AQUA +  item.getAmount() + ChatColor.AQUA + " heads. Take "  + ChatColor.DARK_AQUA + item.getAmount()  + ChatColor.AQUA + " shards.");
        } else {
            p.sendMessage(prefix + "I trade player heads for shards, hold them in your hand next time bud.");
        }
    }
}
