package net.tazpvp.tazpvp.Utils.NPCS;

import net.tazpvp.tazpvp.GUI.NPCGui.MineGUI;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static net.tazpvp.tazpvp.Tazpvp.sellables;

public class CaesarNPC {
    public static void clickMiner(Player p) {
        int amount = p.getInventory().getItemInMainHand().getAmount();
        int price = sellables.get(p.getInventory().getItemInMainHand().getType());
        String prefix = ChatColor.YELLOW + "[NPC] Caesar " + ChatColor.WHITE;
        if (!p.getInventory().getItemInMainHand().getType().name().toLowerCase().endsWith("_pickaxe") && !sellables.containsKey(p.getInventory().getItemInMainHand().getType())) {
            p.sendMessage(prefix + "Hey there traveler! Sell me your ores, or hand me your pickaxe to view what upgrades I can offer.");
        } else if (sellables.containsKey(p.getInventory().getItemInMainHand().getType())) {
            sellOre(p, amount, price);
        } else { new MineGUI(p); }
    }

    public static void sellOre(Player p, int amount, double price) {
        String prefix = ChatColor.YELLOW + "[NPC] Caesar " + ChatColor.WHITE;
        p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        Tazpvp.statsManager.addCoins(p, (int) (amount * price));
        p.sendMessage(prefix + "Great doing business! Here, take " + ChatColor.GRAY + (amount * price));
    }
}
