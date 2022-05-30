package net.tazpvp.tazpvp.GUI;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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
        createButton(p);
        gui.open(p);
    }

    public void createButton(Player p) {
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));


        ItemStack upgrade = new ItemBuilder(Material.CHEST_MINECART).setName(ChatColor.DARK_AQUA + "Trade").setLore(ChatColor.GRAY + "Click to sell your ores.");

        ItemButton button = ItemButton.create(new ItemBuilder(Material.DIRT), e -> {
            e.getWhoClicked().closeInventory();

        });
        ItemButton button2 = ItemButton.create(upgrade, e -> {

        });

        ItemStack pickaxe = getPickaxe(p);
        if (pickaxe == null) {
            p.sendMessage(ChatColor.RED + "You do not have a pickaxe in your inventory!");
        }
        assert pickaxe != null;
        ItemBuilder pickaxeBuilder = new ItemBuilder(pickaxe).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(ChatColor.GRAY + "Click to upgrade your pickaxe.", "1 Shard");



        ItemButton button3 = ItemButton.create(pickaxeBuilder, e -> {
            if (e.getWhoClicked() instanceof Player pl) {
                if (Tazpvp.statsManager.getShards(pl) >= 1) {
                    Tazpvp.statsManager.addShards(pl, -1);
                    if (getPickaxe(pl).getType() == Material.WOODEN_PICKAXE) {
                        getPickaxe(pl).setType(Material.STONE_PICKAXE);
                    } else if (getPickaxe(pl).getType() == Material.STONE_PICKAXE) {
                        getPickaxe(pl).setType(Material.IRON_PICKAXE);
                    } else if (getPickaxe(pl).getType() == Material.IRON_PICKAXE) {
                        getPickaxe(pl).setType(Material.GOLDEN_PICKAXE);
                    } else if (getPickaxe(pl).getType() == Material.GOLDEN_PICKAXE) {
                        getPickaxe(pl).setType(Material.DIAMOND_PICKAXE);
                    } else if (getPickaxe(pl).getType() == Material.DIAMOND_PICKAXE) {
                        pl.sendMessage(ChatColor.RED + "You already have the best pickaxe!");
                    } else {
                        pl.sendMessage(ChatColor.RED + "You already have the best pickaxe!");
                    }
                } else {
                    pl.sendMessage(ChatColor.YELLOW + "[NPC] Miner: " + ChatColor.WHITE + "You don't have enough shards to upgrade your pickaxe.");
                    pl.closeInventory();
                    pl.playSound(pl.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                }

            }
        });

        gui.addButton(11, button);
        gui.addButton(13, button2);
        gui.addButton(15, button3);
        gui.update();
    }

    public ItemStack getPickaxe(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item.getType().name().toLowerCase().endsWith("_pickaxe")) {
                return item;
            }
        }
        return null;
    }
}
