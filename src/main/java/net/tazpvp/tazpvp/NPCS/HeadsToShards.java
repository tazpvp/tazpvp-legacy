package net.tazpvp.tazpvp.NPCS;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.itemutils.ItemBuilder;

public class HeadsToShards {
    String prefix = ChatColor.YELLOW + "[NPC] Bub: " + ChatColor.WHITE;
    public void convertHeadsToShards(Player p) {
        if (p.getInventory().getItemInMainHand().getType().equals(Material.PLAYER_HEAD)) {
            ItemStack item = p.getInventory().getItemInMainHand();
            p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            Tazpvp.statsManager.addShards(p, item.getAmount());
            for (int i = 0; i < item.getAmount(); i++) {
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
            p.sendMessage(prefix + "Traded in " + item.getAmount() + " shards for " + item.getAmount() + " shards.");
        } else if (p.getInventory().getItemInMainHand().getType().equals(Material.PRISMARINE_SHARD)) {
            ItemStack item = p.getInventory().getItemInMainHand();
            p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            Tazpvp.statsManager.addShards(p, item.getAmount());
            for (int i = 0; i < item.getAmount(); i++) {
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            }
            p.sendMessage(prefix + "Traded in " + item.getAmount() + " shards for " + item.getAmount() + " digital shards.");
        } else {
            p.sendMessage(prefix + "I trade player heads for shards, hold them in your hand next time bud.");
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
        }
    }
}
