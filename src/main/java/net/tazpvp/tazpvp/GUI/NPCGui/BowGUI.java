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
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.RED + "Upgade bow"));
        makeItems(p);
        gui.open(p);
    }

    public void makeItems(Player p) {
        gui.fill(0, 26, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" "));
        ItemButton power = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.AQUA + "Power").setLore("1 shard"), e -> {
            doChecks(p, Enchantment.ARROW_DAMAGE);
        });

        ItemButton punch = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.AQUA + "Punch").setLore("1 shard"), e -> {
            doChecks(p, Enchantment.ARROW_KNOCKBACK);
        });

        ItemButton flame = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.AQUA + "Flame").setLore("1 shard"), e -> {
            doChecks(p, Enchantment.ARROW_FIRE);
        });

        gui.addButton(11, power);
        gui.addButton(3, punch);
        gui.addButton(19, flame);
    }

    public void doChecks(Player p, Enchantment ench) {
        if (BowUtils.getBow(p) == null) {
            p.sendMessage(ChatColor.RED + "You do not have a bow!");
            p.closeInventory();
            return;
        }
        if (Tazpvp.statsManager.getShards(p) >= 1) {
            Tazpvp.statsManager.addShards(p, -1);
            BowUtils.applyEnchant(Enchantment.ARROW_DAMAGE, 1, BowUtils.getBow(p));
            p.closeInventory();
        } else {
            p.sendMessage(ChatColor.RED + "You do not have enough shards!");
            p.closeInventory();
        }
    }
}
