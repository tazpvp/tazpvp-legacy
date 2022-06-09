package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class DamageEvent implements Listener {
    public void onDamage(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();
        if (Tazpvp.fallDamageImmune.contains(p)) {
            e.setCancelled(true);
            Tazpvp.fallDamageImmune.remove(p);
        }
    }

    public static List<ItemStack> items = Arrays.asList(
            new ItemStack(Material.WOODEN_SWORD),
            new ItemStack(Material.STONE_SWORD),
            new ItemStack(Material.GOLDEN_SWORD),
            new ItemStack(Material.IRON_SWORD),
            new ItemStack(Material.DIAMOND_SWORD),
            new ItemStack(Material.NETHERITE_SWORD)
    );

//    @EventHandler
//    public void onDamage(EntityDamageByEntityEvent e) {
//        if (e.getDamager() instanceof Player attacker && e.getEntity() instanceof Player p) {
//            Entity enemy = e.getDamager();
//            Material item = attacker.getInventory().getItemInMainHand().getType();
//            if (p.hasMetadata("NPC") || attacker.hasMetadata("NPC")) {
//                return;
//            } else if (!p.getWorld().getName().equalsIgnoreCase("arena")) {
//                return;
//            } else if (item == Material.WOODEN_SWORD || item == Material.STONE_SWORD || item == Material.GOLDEN_SWORD || item == Material.IRON_SWORD || item == Material.DIAMOND_SWORD || item == Material.NETHERITE_SWORD) {
//                if (Tazpvp.statsManager.getExp(attacker) >= Tazpvp.statsManager.getExpLeft(attacker)) {
//                    if (Tazpvp.statsManager.checkLevelUp(attacker)) {
//                        Tazpvp.statsManager.levelUp(attacker);
//                        Tazpvp.statsManager.initScoreboard(attacker);
//                    } else {
//                        ItemStack item1 = attacker.getInventory().getItemInMainHand();
//                        if (item1.hasItemMeta()) {
//                            if (item1.getItemMeta().hasDisplayName()) {
//                                for (Items i : Items.values()) {
//                                    if (i.getName().equals(item1.getItemMeta().getDisplayName())) {
//                                        Tazpvp.statsManager.addExp(attacker, i.getExp());
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }


}
