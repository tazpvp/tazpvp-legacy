package net.tazpvp.tazpvp.GUI.NPCGui;

import com.google.common.collect.Lists;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MineGUI {
    private InventoryGUI gui;

    public MineGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "PICKAXE UPGRADES"));
        createButton(p);
        gui.open(p);
    }

    public void createButton(Player p) {
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        ItemStack pickaxe = getPickaxe(p);
        if (pickaxe == null) {
            p.sendMessage(ChatColor.RED + "You do not have a pickaxe in your inventory!");
            p.closeInventory();
            return;
        }

        ItemButton button = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.GRAY + "Auto Smelt I").setLore(ChatColor.GRAY + "1 coins"), e -> {
            updatePickaxeItem(p, pickaxe, Enchantment.SILK_TOUCH, ChatColor.GRAY + "Auto Smelt I", 1);
        });


        ItemButton button2 = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.GRAY + "Double Ores I").setLore(ChatColor.GRAY + "1 coins"), e -> {
            updatePickaxeItem(p, pickaxe, Enchantment.LOOT_BONUS_BLOCKS, ChatColor.GRAY + "Double Ores I", 1);
        });


        ItemBuilder pickaxeBuilder = new ItemBuilder(pickaxe).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(ChatColor.GRAY + "Click to upgrade your pickaxe.", "1 Shard");

        ItemButton button3 = ItemButton.create(pickaxeBuilder, e -> {
            if (e.getWhoClicked() instanceof Player pl) {
                if (Tazpvp.statsManager.getShards(pl) >= 1) {
                    Tazpvp.statsManager.addShards(pl, -1);
                    if (getPickaxe(pl).getType() == Material.WOODEN_PICKAXE) {
                        updatePickaxeType(pl, Material.STONE_PICKAXE);
                    } else if (getPickaxe(pl).getType() == Material.STONE_PICKAXE) {
                        updatePickaxeType(pl, Material.IRON_PICKAXE);
                    } else if (getPickaxe(pl).getType() == Material.IRON_PICKAXE) {
                        updatePickaxeType(pl, Material.GOLDEN_PICKAXE);
                    } else if (getPickaxe(pl).getType() == Material.GOLDEN_PICKAXE) {
                        updatePickaxeType(pl, Material.DIAMOND_PICKAXE);
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

    public void updatePickaxeType(Player p, Material newPIckaxe) {
        getPickaxe(p).setType(newPIckaxe);
        List<String> Lore = new ArrayList<>();
        ItemMeta meta = getPickaxe(p).getItemMeta();
        meta.setLore(Lore);
        getPickaxe(p).setItemMeta(meta);
        getPickaxe(p).removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
        getPickaxe(p).removeEnchantment(Enchantment.SILK_TOUCH);
    }

    public void updatePickaxeItem(Player p, ItemStack pickaxe, Enchantment ench, String lore, int cost) {
        if (pickaxe.containsEnchantment(ench)) {
            p.sendMessage(ChatColor.YELLOW + "[NPC] Miner: " + ChatColor.WHITE + "You already have this enchant.");
            p.closeInventory();
        } else {
            if (Tazpvp.statsManager.getMoney(p) >= cost) {
                Tazpvp.statsManager.addMoney(p, -cost);
                ItemMeta meta = pickaxe.getItemMeta();
                List<String> loreList = meta.getLore();
                if (loreList == null) {
                    loreList = Lists.newArrayList();
                }
                loreList.add(lore);
                meta.setLore(loreList);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                pickaxe.setItemMeta(meta);
                pickaxe.addEnchantment(ench, 1);
                p.sendMessage(ChatColor.YELLOW + "[NPC] Miner: " + ChatColor.WHITE + "Here is your new enchant!");
                p.closeInventory();
            } else {
                p.sendMessage(ChatColor.YELLOW + "[NPC] Miner: " + ChatColor.WHITE + "You do not have enough money!");
                p.closeInventory();
            }
        }
    }
}
