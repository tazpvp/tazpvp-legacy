package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.GUI.MineGUI;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static net.tazpvp.tazpvp.Tazpvp.sellables;

public class MinerNPC {
    public static void clickMiner(Player p) {
        if (!Tazpvp.boolManager.getHasClickedMiner(p)) {
            p.sendMessage(ChatColor.YELLOW + "[NPC] Miner: " + ChatColor.WHITE + "Hey there traveller! Sell me your ores, or take a look at what upgrades I can offer.");
            Tazpvp.boolManager.setHasClickedMiner(p, true);
        } else if (sellables.contains(p.getMainHand())){
            ItemStack hand = p.getInventory().getItemInMainHand();
            int amount = p.getInventory().getItemInMainHand().getAmount();
            Material ore = hand.getType();
            if (ore.equals(Material.DEEPSLATE_GOLD_ORE)){
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.DEEPSLATE_REDSTONE_ORE)) {
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.DEEPSLATE_IRON_ORE)) {
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.DEEPSLATE_LAPIS_ORE)) {
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.DEEPSLATE_EMERALD_ORE)) {
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.RAW_GOLD)) {
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.REDSTONE)) {
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.RAW_IRON)) {
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.LAPIS_LAZULI)) {
                sellOre(p, amount, 1);
            } else if (ore.equals(Material.EMERALD)) {
                sellOre(p, amount, 1);
            }
        } else { new MineGUI(p); }
    }

    public static void sellOre(Player p, int amount, double price) {
        p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        Tazpvp.statsManager.addMoney(p, (int) (amount * price));
        p.sendMessage(ChatColor.YELLOW + "[NPC] Miner: " + ChatColor.WHITE + "Great doing business! Here, take " + ChatColor.GRAY + (amount * price));
    }
}
