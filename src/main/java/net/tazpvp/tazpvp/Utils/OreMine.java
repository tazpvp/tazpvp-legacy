package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class OreMine implements Listener {
    public static void oreRespawn(Player p, Material block, Material block2, Long dur, Block b){
        if (p.getInventory().getItemInMainHand().getType().name().toLowerCase().endsWith("_pickaxe")) {
            ItemStack i = p.getInventory().getItemInMainHand();
            if (i.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) && i.containsEnchantment(Enchantment.SILK_TOUCH)){
                p.getInventory().addItem(new ItemStack(block2, 2));
                return;
            } else if (i.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)){
                p.getInventory().addItem(new ItemStack(block, 2));
                return;
            } else if (i.containsEnchantment(Enchantment.SILK_TOUCH)){
                p.getInventory().addItem(new ItemStack(block2, 1));
                return;
            } else {
                p.getInventory().addItem(new ItemStack(block, 1));
            }
        } else {
            p.getInventory().addItem(new ItemStack(block));
        }

        b.setType(Material.BEDROCK);
        new BukkitRunnable() {
            @Override
            public void run() {
                b.setType(block);
            }
        }.runTaskLater(Tazpvp.getInstance(), dur);
    }
}
