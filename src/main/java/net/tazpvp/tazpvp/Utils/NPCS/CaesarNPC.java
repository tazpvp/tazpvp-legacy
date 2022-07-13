package net.tazpvp.tazpvp.Utils.NPCS;

import net.tazpvp.tazpvp.GUI.NPCGui.MineGUI;
import net.tazpvp.tazpvp.Managers.PlayerStats.AchievementManager;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static net.tazpvp.tazpvp.Tazpvp.sellables;
import static net.tazpvp.tazpvp.Utils.Functionality.AchieveUtils.Achieve;

public class CaesarNPC {
    public static void clickMiner(Player p) {
        String prefix = ChatColor.YELLOW + "[NPC] Caesar " + ChatColor.WHITE;
        Material userItem = p.getInventory().getItemInMainHand().getType();
        if (!userItem.name().toLowerCase().endsWith("_pickaxe") && !sellables.containsKey(userItem)) {
            p.sendMessage(prefix + "Hey there traveler! Sell me your ores, or hand me your pickaxe to view what upgrades I can offer.");
        } else if (sellables.containsKey(p.getInventory().getItemInMainHand().getType())) {
            int amount = p.getInventory().getItemInMainHand().getAmount();
            int price = sellables.get(p.getInventory().getItemInMainHand().getType());
            sellOre(p, amount, price);
        } else { new MineGUI(p); }
    }

    public static void sellOre(Player p, int amount, double price) {
        String prefix = ChatColor.YELLOW + "[NPC] Caesar " + ChatColor.WHITE;
        p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        Tazpvp.statsManager.addCoins(p, (int) (amount * price));
        p.sendMessage(prefix + "Great doing business! Here, take " + ChatColor.GRAY + (amount * price));
        if (!Tazpvp.achievementManager.statsFile.getBoolean(p.getUniqueId() + ".business")) {
            Achieve(p, "Business", "business", 1, 20);
        }
    }
}
