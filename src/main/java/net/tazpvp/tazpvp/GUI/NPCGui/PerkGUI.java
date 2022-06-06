package net.tazpvp.tazpvp.GUI.NPCGui;

import net.tazpvp.tazpvp.Managers.PlayerStats.PerkManager;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class PerkGUI {
    private InventoryGUI gui;


    public PerkGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "PERKS"));
        addItems();
        gui.open(p);
    }

    public void createShopButton(String name, int slot, ItemStack item, int price, String description, int id){
        ItemBuilder b = new ItemBuilder(item).setName(name).setLore(description, ChatColor.GRAY + "Cost: " + ChatColor.AQUA + price + " Shards");
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            if (Tazpvp.statsManager.getShards(p) >= price){
                if (id == 1) { Tazpvp.perkManager.setFatPerk(p, true);
                } else if (id == 2) { Tazpvp.perkManager.setExcavatorPerk(p, true);
                } else if (id == 3) { Tazpvp.perkManager.setBuilderPerk(p, true);
                } else if (id == 4) { Tazpvp.perkManager.setArcherPerk(p, true);
                } else if (id == 5) { Tazpvp.perkManager.setGobblePerk(p, true);
                } else if (id == 6) { Tazpvp.perkManager.setAgilityPerk(p, true);
                } else if (id == 7) { Tazpvp.perkManager.setTankPerk(p, true); }
                Tazpvp.statsManager.addShards(p, -price);
                ItemStack itemstack = new ItemBuilder(item).setName(name).setLore(description);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            } else {
                p.sendMessage(ChatColor.RED + "You don't have enough money!");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            }
        });
        gui.addButton(slot, button);
    }

    public void addItems(){
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        createShopButton(ChatColor.YELLOW + "Fat", 10, new ItemStack(Material.SLIME_BALL), 5, ChatColor.GRAY + "Gain an extra heart on kill.", 1);
        createShopButton(ChatColor.BLUE + "Excavator", 11, new ItemStack(Material.GOLDEN_PICKAXE), 7, ChatColor.GRAY + "Gain Exp for mining.", 2);
        createShopButton(ChatColor.LIGHT_PURPLE + "Builder", 12, new ItemStack(Material.SCAFFOLDING), 4, ChatColor.GRAY + "Chance to duplicate your placed block.", 3);
        createShopButton( ChatColor.GREEN + "Archer", 13, new ItemStack(Material.BOW), 6, ChatColor.GRAY + "Chance to duplicate arrow on shoot.", 4);
        createShopButton(ChatColor.AQUA + "Gobble", 14, new ItemStack(Material.CAKE), 7, ChatColor.GRAY + "Replenish your hunger on kill.", 5);
        createShopButton(ChatColor.RED + "Agility", 15, new ItemStack(Material.FEATHER), 5, ChatColor.GRAY + "Gain speed on kill.", 6);
        createShopButton(ChatColor.GOLD + "Tank", 16, new ItemStack(Material.NETHERITE_CHESTPLATE), 6, ChatColor.GRAY + "Chance to get 2 absorption hearts on kill.", 7);
    }
}
