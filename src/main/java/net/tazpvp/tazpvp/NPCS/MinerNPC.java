package net.tazpvp.tazpvp.NPCS;

import net.tazpvp.tazpvp.GUI.NPCGui.MineGUI;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static net.tazpvp.tazpvp.Tazpvp.sellables;

public class MinerNPC {
    public static void clickMiner(Player p) {
        if (!p.getInventory().getItemInMainHand().getType().name().toLowerCase().endsWith("_pickaxe") && !sellables.containsKey(p.getInventory().getItemInMainHand().getType())) {
            p.sendMessage(ChatColor.YELLOW + "[NPC] Caesar: " + ChatColor.WHITE + "Hey there traveler! Sell me your ores, or hand me your pickaxe to view what upgrades I can offer.");
        } else if (sellables.containsKey(p.getInventory().getItemInMainHand().getType())) {
            sellOre(p, p.getInventory().getItemInMainHand().getAmount(), sellables.get(p.getInventory().getItemInMainHand().getType()));
        } else { new MineGUI(p); }
    }

    public static void sellOre(Player p, int amount, double price) {
        p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        Tazpvp.statsManager.addMoney(p, (int) (amount * price));
        p.sendMessage(ChatColor.YELLOW + "[NPC] Caesar: " + ChatColor.WHITE + "Great doing business! Here, take " + ChatColor.GRAY + (amount * price));
    }
}
