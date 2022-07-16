package net.tazpvp.tazpvp.GUI.NPCGui;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.BowUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import static net.tazpvp.tazpvp.Utils.Functionality.IA.ItemStackUtils.hideFlag;

public class BowGUI {
    private InventoryGUI gui;
    String prefix = ChatColor.GREEN + "[NPC] Frank" + ChatColor.DARK_GREEN;

    public BowGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 4 * 9, ChatColor.BLUE + "" + ChatColor.BOLD + "BOWS & ARMOR"));
        makeItems(p);
        gui.open(p);
    }

    public void makeItems(Player p) {
        gui.fill(0, 4 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));
        ItemButton Bow = ItemButton.create(new ItemBuilder(Material.BOW).setName(ChatColor.GOLD + "" + ChatColor.BOLD + "BOW UPGRADES"), e -> {});
        ItemStack armorMat = new ItemStack(Material.DIAMOND_CHESTPLATE); hideFlag(armorMat);
        ItemButton Armor = ItemButton.create(new ItemBuilder(armorMat).setName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "ARMOR UPGRADES"), e -> {});

        ItemButton power = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.BLUE + "Power I").setLore(ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "6 Shards", "", ChatColor.RED + "Only 1 upgrade can be", ChatColor.RED + "selected at a time."), e -> {
            doChecks(p, Enchantment.ARROW_DAMAGE, 6);
        });

        ItemButton punch = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.BLUE + "Punch I").setLore(ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "6 Shards", "", ChatColor.RED + "Only 1 upgrade can be", ChatColor.RED + "selected at a time."), e -> {
            doChecks(p, Enchantment.ARROW_KNOCKBACK, 6);
        });

        ItemButton flame = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.BLUE + "Flame I").setLore(ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "5 Shards", "", ChatColor.RED + "Only 1 upgrade can be", ChatColor.RED + "selected at a time."), e -> {
            doChecks(p, Enchantment.ARROW_FIRE, 5);
        });
        ItemButton thorns = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.BLUE + "Thorns I").setLore(ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "4 Shards", "", ChatColor.RED + "Only 1 upgrade can be", ChatColor.RED + "selected at a time."), e -> {
            doChecksR(p, Enchantment.THORNS, 1, 4);
        });

        ItemButton prot = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.BLUE + "Protection II").setLore(ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "5 Shards", "", ChatColor.RED + "Only 1 upgrade can be", ChatColor.RED + "selected at a time."), e -> {
            doChecksR(p, Enchantment.PROTECTION_ENVIRONMENTAL, 2, 5);
        });

        ItemButton ff = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.BLUE + "Feather Falling IV").setLore(ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "2 Shards", "", ChatColor.RED + "Only 1 upgrade can be", ChatColor.RED + "selected at a time."), e -> {
            doChecksR(p, Enchantment.PROTECTION_FALL, 4, 2);
        });

        gui.addButton(11, Bow);
        gui.addButton(15,  Armor);

        gui.addButton(19, power);
        gui.addButton(20, punch);
        gui.addButton(21, flame);

        gui.addButton(23, thorns);
        gui.addButton(24, prot);
        gui.addButton(25, ff);
    }

    public void doChecks(Player p, Enchantment ench, int price) {
        if (BowUtils.getBow(p) == null) {
            p.sendMessage(prefix + " You do not have a bow!");
            p.closeInventory();
            return;
        }
        if (BowUtils.getBow(p).getEnchantments().containsKey(ench)) {
            p.sendMessage(prefix + " You already have this enchantment!");
            p.closeInventory();
            return;
        }
        if (Tazpvp.statsManager.getShards(p) < price) {
            p.sendMessage(prefix + " You do not have enough shards!");
            p.closeInventory();
            return;
        }
        Tazpvp.statsManager.addShards(p, -price);
        BowUtils.applyEnchant(ench, 1, BowUtils.getBow(p), (Tazpvp.boolManager.getHasRebirthed(p)));
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
        p.closeInventory();
    }

    public void doChecksR(Player p, Enchantment ench, int level, int price) {
        for (ItemStack i : BowUtils.getArmor(p)) {
            if (i.getType().equals(Material.AIR)) {
                p.sendMessage(prefix + " You do not have any armor!");
                p.closeInventory();
                return;
            } else if (p.getInventory().getHelmet().getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) >= 2) {
                p.sendMessage(prefix + " You already have this enchantment!");
                p.closeInventory();
                return;
            }
        }
        if (Tazpvp.statsManager.getShards(p) < price) {
            p.sendMessage(prefix + " You do not have enough shards!");
            p.closeInventory();
            return;
        }

        Tazpvp.statsManager.addShards(p, -price);
        BowUtils.apllyEnchant(BowUtils.getArmor(p), ench, level);
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
        p.closeInventory();
    }
}
