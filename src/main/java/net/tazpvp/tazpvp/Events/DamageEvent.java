package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class DamageEvent implements Listener {
    public static List<ItemStack> items = Arrays.asList(
            new ItemStack(Material.WOODEN_SWORD),
            new ItemStack(Material.STONE_SWORD),
            new ItemStack(Material.GOLDEN_SWORD),
            new ItemStack(Material.IRON_SWORD),
            new ItemStack(Material.DIAMOND_SWORD),
            new ItemStack(Material.NETHERITE_SWORD)
    );
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e instanceof EntityDamageByEntityEvent pe) {    
            Entity enemy = pe.getDamager();
            Player p = (Player) pe.getEntity();
            if (!(enemy instanceof Player attacker)) {
                return;
            } else if (p.hasMetadata("NPC") || attacker.hasMetadata("NPC")) {
                return;
            } else if (!p.getWorld().getName().equalsIgnoreCase("arena")) {
                return;
            } else if (items.contains(attacker.getInventory().getItemInMainHand())) {
                Tazpvp.statsManager.addExp(attacker, 1);
                if (Tazpvp.statsManager.getExp(attacker) >= Tazpvp.statsManager.getExpLeft(attacker)) {
                    if (Tazpvp.statsManager.checkLevelUp(attacker)) {
                        Tazpvp.statsManager.levelUp(attacker);
                        Tazpvp.statsManager.initScoreboard(attacker);
                    } else {
                        ItemStack item = attacker.getInventory().getItemInMainHand();
                        if (item.hasItemMeta()) {
                            if (item.getItemMeta().hasDisplayName()) {
                                for (Items i : Items.values()) {
                                    if (i.getName().equals(item.getItemMeta().getDisplayName())) {
                                        Tazpvp.statsManager.addExp(attacker, i.getExp());
                                        break;
                                    }
                                }
                            }
                        }
                        Tazpvp.statsManager.addExp(attacker, 1);
                    }
                }
            }
        }
    }

    public static void LevelUp(Player p, int number) {
        Tazpvp.statsManager.addLevel(p, 1*number);
        p.sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬\n" +
            ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat Lvl. " + ChatColor.AQUA + Tazpvp.statsManager.getLevel(p) + ChatColor.DARK_AQUA + "\n" + "\n" +
            ChatColor.GREEN + "" + ChatColor.BOLD + "  REWARDS\n" +
            ChatColor.DARK_GRAY + "  +" + ChatColor.BLUE + "1 Point\n" +
            ChatColor.DARK_GRAY + "  +" + ChatColor.GOLD + "60 Coins\n" +
            ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        );
        Tazpvp.statsManager.addMoney(p, 60*number);
        Tazpvp.statsManager.setExp(p, 0);
        Tazpvp.statsManager.setExpLeft(p, (Tazpvp.statsManager.getExpLeft(p)*1.05)*number);
        Tazpvp.statsManager.addShards(p, 1*number);
        p.setLevel(Tazpvp.statsManager.getLevel(p));
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1 );
    }
}
