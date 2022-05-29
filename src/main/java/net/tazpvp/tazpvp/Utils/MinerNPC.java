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
            p.sendMessage("");
        } else if (sellables.contains(p.getMainHand())){
            ItemStack hand = p.getInventory().getItemInMainHand();
            int amount = p.getInventory().getItemInMainHand().getAmount();
            Material ore = hand.getType();
            if (Objects.equals(hand, new ItemStack(Material.GOLD_ORE, amount))){ sellOre(p, ore, amount, 1);}
            else if (Objects.equals(hand, new ItemStack(Material.REDSTONE_ORE, amount))){ sellOre(p, ore, amount, 1);}
            else if (Objects.equals(hand, new ItemStack(Material.IRON_ORE, amount))){ sellOre(p, ore, amount, 1);}
            else if (Objects.equals(hand, new ItemStack(Material.LAPIS_ORE, amount))){ sellOre(p, ore, amount, 1);}
            else if (Objects.equals(hand, new ItemStack(Material.EMERALD_ORE, amount))){ sellOre(p, ore, amount, 1);}
        } else { new MineGUI(p); }
    }

    public static void sellOre(Player p, Material ore, int amount, double price) {
        if (Objects.equals(p.getMainHand(), new ItemStack(ore, amount))) {
            p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            Tazpvp.statsManager.addMoney(p, (int) (amount * price));
            p.sendMessage(ChatColor.YELLOW + "[NPC] Miner: " + ChatColor.WHITE + "Great doing business! Here, take " + ChatColor.GRAY + (amount * price));
        }
    }
}
