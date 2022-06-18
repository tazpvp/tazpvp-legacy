package net.tazpvp.tazpvp.GUI.NPCGui;

import com.google.common.collect.Lists;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Fun.NumberToRomanNumeral;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class MineGUI {
    private InventoryGUI gui;
    String prefix = ChatColor.YELLOW + "[NPC] Caesar " + ChatColor.WHITE;

    public MineGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "PICKAXE SHOP"));
        createButton(p);
        gui.open(p);
    }

    public void createButton(Player p) {
        gui.fill(0, 27, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        ItemStack pickaxe = getPickaxe(p);
        if (pickaxe == null) {
            p.sendMessage(prefix + "You do not have a pickaxe in your inventory!");
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            return;
        }

        ItemButton button = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.DARK_AQUA + "Auto Smelt").setLore(ChatColor.GRAY + "Automatically refine ores.", ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "178 Coins"), e -> {
            updatePickaxeItem(p, pickaxe, Enchantment.SILK_TOUCH, ChatColor.GRAY + "Auto Smelt I", 178);
        });

        ItemButton button1 = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.DARK_AQUA + "Efficiency").setLore(ChatColor.GRAY + "Mine ores faster.", ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "426 Coins"), e -> {
            updatePickaxeItem(p, pickaxe, Enchantment.DIG_SPEED, ChatColor.GRAY + "Efficiency", 426);
        });


        ItemButton button2 = ItemButton.create(new ItemBuilder(Material.ENCHANTED_BOOK).setName(ChatColor.DARK_AQUA + "Double Ores").setLore(ChatColor.GRAY + "2x ores you mine.", ChatColor.GRAY + "Cost: " + ChatColor.GOLD + "317 Coins"), e -> {
            updatePickaxeItem(p, pickaxe, Enchantment.LOOT_BONUS_BLOCKS, ChatColor.GRAY + "Double Ores I", 317);
        });


        ItemBuilder pickaxeBuilder = new ItemBuilder(pickaxe).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE).setLore(ChatColor.GRAY + "Enhance your pickaxe material.", ChatColor.GRAY + "Cost: " + ChatColor.AQUA + "4 Shards");

        ItemButton button3 = ItemButton.create(pickaxeBuilder, e -> {
            if (e.getWhoClicked() instanceof Player pl) {
                if (Tazpvp.statsManager.getShards(pl) >= 4) {
                    if (getPickaxe(pl).getType() == Material.WOODEN_PICKAXE) {
                        updatePickaxeType(pl, Material.STONE_PICKAXE);
                        Tazpvp.statsManager.addShards(pl, -4);
                    } else if (getPickaxe(pl).getType() == Material.STONE_PICKAXE) {
                        updatePickaxeType(pl, Material.IRON_PICKAXE);
                        Tazpvp.statsManager.addShards(pl, -4);
                    } else if (getPickaxe(pl).getType() == Material.IRON_PICKAXE) {
                        updatePickaxeType(pl, Material.GOLDEN_PICKAXE);
                        Tazpvp.statsManager.addShards(pl, -4);
                    } else if (getPickaxe(pl).getType() == Material.GOLDEN_PICKAXE) {
                        updatePickaxeType(pl, Material.DIAMOND_PICKAXE);
                        Tazpvp.statsManager.addShards(pl, -4);
                    } else if (getPickaxe(pl).getType() == Material.DIAMOND_PICKAXE) {
                        pl.sendMessage(prefix + "You already have the best pickaxe!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    } else {
                        pl.sendMessage(prefix + "You already have the best pickaxe!");
                        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    }
                } else {
                    pl.sendMessage(prefix + "You don't have enough shards to upgrade your pickaxe.");
                    pl.closeInventory();
                    pl.playSound(pl.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                }

            }
        });

        gui.addButton(11, button);
        gui.addButton(12, button1);
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
        p.closeInventory();
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }

    public void efficnencyLevel(Player p, ItemMeta meta) {
        if (meta.getEnchants().containsKey(Enchantment.DIG_SPEED)) {
            int currLevel = meta.getEnchantLevel(Enchantment.DIG_SPEED);
            meta.addEnchant(Enchantment.DIG_SPEED, currLevel + 1, true);
        } else {
            meta.addEnchant(Enchantment.DIG_SPEED,1, true);
        }
    }

    public boolean maxEffyLevel(ItemStack i) {
        if (i.getEnchantments().containsKey(Enchantment.DIG_SPEED)) {
            int currLevel = i.getEnchantmentLevel(Enchantment.DIG_SPEED);
            return currLevel == 5;
        }
        return false;
    }

    public int currEffyLevel(ItemStack i) {
        if (i.getEnchantments().containsKey(Enchantment.DIG_SPEED)) {
            return i.getEnchantmentLevel(Enchantment.DIG_SPEED);
        }
        return 0;
    }


    public void updatePickaxeItem(Player p, ItemStack pickaxe, Enchantment ench, String lore, int cost) {
        if (pickaxe.containsEnchantment(ench) && !ench.equals(Enchantment.DIG_SPEED)) {
            p.sendMessage(prefix + "You already have this enchantment.");
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
        } else if (maxEffyLevel(pickaxe)) {
            p.sendMessage(prefix + "You have reached the max level of this enchantment.");
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
        } else {
            if (Tazpvp.statsManager.getCoins(p) >= cost) {
                Tazpvp.statsManager.addCoins(p, -cost);
                ItemMeta meta = pickaxe.getItemMeta();
                List<String> loreList = meta.getLore();
                if (loreList == null) {
                    loreList = Lists.newArrayList();
                }
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                if ((ench != Enchantment.DIG_SPEED)) {
                    meta.addEnchant(ench, 1, true);
                    loreList.add(lore);
                } else {
                    int nextLvl = currEffyLevel(pickaxe) + 1;
                    efficnencyLevel(p, meta);
                    for (String s : loreList) {
                        if (s.contains(lore)) {
                            loreList.remove(s);
                            break;
                        }
                    }
                    loreList.add(lore + " " + NumberToRomanNumeral.intToRoman(nextLvl));
                }
                meta.setLore(loreList);
                pickaxe.setItemMeta(meta);
                p.sendMessage(prefix + "You enchanted your pickaxe with " + lore);
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            } else {
                p.sendMessage(prefix + "You do not have enough money!");
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            }
        }
    }
}
