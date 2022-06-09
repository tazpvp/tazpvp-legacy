package net.tazpvp.tazpvp.GUI.NPCGui;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.BowUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class BowGUI {
    private InventoryGUI gui;

    public BowGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 4 * 9, ChatColor.BLUE + "" + ChatColor.BLUE + "BOWS AND ARMOR"));
        makeItems(p);
        gui.open(p);
    }

    public void makeItems(Player p) {
        gui.fill(0, 4 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));
        ItemButton Bow = ItemButton.create(new ItemBuilder(Material.BOW).setName(ChatColor.GOLD + "" + ChatColor.BOLD + "BOW UPGRADES").setLore(ChatColor.GRAY + "Only 1 upgrade can be", ChatColor.GRAY + "selected at a time."), e -> {});
        ItemButton Armor = ItemButton.create(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "ARMOR UPGRADES").setLore(ChatColor.GRAY + "Only 1 upgrade can be", ChatColor.GRAY + "selected at a time."), e -> {});

        ItemButton power = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.BLUE + "Power").setLore(ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "6 Shards"), e -> {
            doChecks(p, Enchantment.ARROW_DAMAGE);
        });

        ItemButton punch = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.BLUE + "Punch").setLore("1 shard"), e -> {
            doChecks(p, Enchantment.ARROW_KNOCKBACK);
        });

        ItemButton flame = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.BLUE + "Flame").setLore("1 shard"), e -> {
            doChecks(p, Enchantment.ARROW_FIRE);
        });
        ItemButton thorns = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.BLUE + "Thorns").setLore("1 shard"), e -> {
            doChecksR(p, Enchantment.THORNS, 1);
        });

        ItemButton prot = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.BLUE + "Protection").setLore("1 shard"), e -> {
            doChecksR(p, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        });

        ItemButton ff = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.BLUE + "Feather Falling").setLore("1 shard"), e -> {
            doChecksR(p, Enchantment.PROTECTION_FALL, 4);
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

    public void doChecks(Player p, Enchantment ench) {
        if (BowUtils.getBow(p) == null) {
            p.sendMessage(ChatColor.YELLOW + "[Hrank]" + ChatColor.WHITE + " You do not have a bow!");
            p.closeInventory();
            return;
        }
        if (Tazpvp.statsManager.getShards(p) >= 1) {
            Tazpvp.statsManager.addShards(p, -1);
            BowUtils.applyEnchant(ench, 1, BowUtils.getBow(p));
            p.closeInventory();
        } else {
            p.sendMessage(ChatColor.YELLOW + "[Hrank]" + ChatColor.WHITE + " You do not have enough shards!");
            p.closeInventory();
        }
    }

    public void doChecksR(Player p, Enchantment ench, int level) {
        if (BowUtils.getArmor(p)[1].getType() == Material.AIR) {
            p.sendMessage(ChatColor.YELLOW + "[Hrank]" + ChatColor.WHITE + " You do not have armor!");
        }
        if (Tazpvp.statsManager.getShards(p) >= 1) {
            Tazpvp.statsManager.addShards(p, -1);
            BowUtils.apllyEnchant(BowUtils.getArmor(p), ench, level);
            p.closeInventory();
        } else {
            p.sendMessage(ChatColor.YELLOW + "[Hrank]" + ChatColor.WHITE + " You do not have enough shards!");
            p.closeInventory();
        }
    }
}
