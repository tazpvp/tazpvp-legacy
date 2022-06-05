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

public class amerGUI {
    private InventoryGUI gui;

    public amerGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.RED + "Upgade armero"));
        makeItems(p);
        gui.open(p);
    }

    public void makeItems(Player p) {
        gui.fill(0, 26, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" "));
        ItemButton power = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.AQUA + "THORNS I").setLore("1 shard"), e -> {
            doChecks(p, Enchantment.THORNS, 1);
        });

        ItemButton punch = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.AQUA + "PROTECTION II").setLore("1 shard"), e -> {
            doChecks(p, Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        });

        ItemButton flame = ItemButton.create(new ItemBuilder(Material.KNOWLEDGE_BOOK).setName(ChatColor.AQUA + "FEATHER FALLING IV").setLore("1 shard"), e -> {
            doChecks(p, Enchantment.PROTECTION_FALL, 4);
        });

        gui.addButton(11, power);
        gui.addButton(3, punch);
        gui.addButton(19, flame);
    }

    public void doChecks(Player p, Enchantment ench, int level) {
        BowUtils.getArmor(p);
        if (Tazpvp.statsManager.getShards(p) >= 1) {
            Tazpvp.statsManager.addShards(p, -1);
            BowUtils.apllyEnchant(BowUtils.getArmor(p), ench, level);
            p.closeInventory();
        } else {
            p.sendMessage(ChatColor.RED + "You do not have enough shards!");
            p.closeInventory();
        }
    }
}
