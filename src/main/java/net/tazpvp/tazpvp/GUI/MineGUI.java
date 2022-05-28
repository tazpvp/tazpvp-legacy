package net.tazpvp.tazpvp.GUI;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.Objects;

public class MineGUI {
    private InventoryGUI gui;

    public MineGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.RED + "Miner"));
        createButton();
        gui.open(p);
    }

    public void createButton() {
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        ItemStack pickaxe = new ItemBuilder(Material.GOLDEN_PICKAXE).setName(ChatColor.DARK_AQUA + "Upgrades").setLore(ChatColor.GRAY + "Pickaxe upgrades.");
        ItemMeta pickaxeMeta = pickaxe.getItemMeta();
        pickaxeMeta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pickaxe.setItemMeta(pickaxeMeta);
        ItemStack upgrade = new ItemBuilder(Material.CHEST_MINECART).setName(ChatColor.DARK_AQUA + "Trade").setLore(ChatColor.GRAY + "Click to sell your ores.");

        ItemButton button = ItemButton.create(pickaxe, e -> {
            e.getWhoClicked().closeInventory();

        });
        ItemButton button2 = ItemButton.create(upgrade, e -> {
            Player p = (Player) e.getWhoClicked();
            ItemStack i = p.getItemOnCursor();
            int amount = p.getItemOnCursor().getAmount();
            Material b = i.getType();
            if (Objects.equals(p.getItemOnCursor(), new ItemStack(Material.GOLD_ORE, amount))){ sellOre(p, b, amount, 1);}
            else if (Objects.equals(p.getItemOnCursor(), new ItemStack(Material.REDSTONE_ORE, amount))){ sellOre(p, b, amount, 1);}
            else if (Objects.equals(p.getItemOnCursor(), new ItemStack(Material.IRON_ORE, amount))){ sellOre(p, b, amount, 1);}
            else if (Objects.equals(p.getItemOnCursor(), new ItemStack(Material.LAPIS_ORE, amount))){ sellOre(p, b, amount, 1);}
            else if (Objects.equals(p.getItemOnCursor(), new ItemStack(Material.EMERALD_ORE, amount))){ sellOre(p, b, amount, 1);}
        });
        gui.addButton(12, button);
        gui.addButton(14, button2);
        gui.update();
    }

    public void sellOre(Player p, Material ore, int amount, double price) {
        if (Objects.equals(p.getItemOnCursor(), new ItemStack(ore, amount))) {
            p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            Tazpvp.statsManager.addMoney(p, (int) (amount * price));
            p.sendMessage(ChatColor.GOLD + "[NPC] Miner: " + "Great doing business! Here, take " + ChatColor.AQUA + "" );
        }
    }
}
