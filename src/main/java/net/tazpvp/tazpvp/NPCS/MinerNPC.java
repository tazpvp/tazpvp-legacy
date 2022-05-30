package net.tazpvp.tazpvp.NPCS;

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
        } else if (sellables.containsKey(p.getInventory().getItemInMainHand().getType())) {
            sellOre(p, p.getInventory().getItemInMainHand().getAmount(), sellables.get(p.getInventory().getItemInMainHand().getType()));
        } else { new MineGUI(p); }
    }

    public static void sellOre(Player p, int amount, double price) {
        p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        Tazpvp.statsManager.addMoney(p, (int) (amount * price));
        p.sendMessage(ChatColor.YELLOW + "[NPC] Miner: " + ChatColor.WHITE + "Great doing business! Here, take " + ChatColor.GRAY + (amount * price));
    }
}
