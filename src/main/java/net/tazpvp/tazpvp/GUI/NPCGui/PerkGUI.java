package net.tazpvp.tazpvp.GUI.NPCGui;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class PerkGUI {
    private InventoryGUI gui;
    private Player p;
    String prefix = ChatColor.DARK_PURPLE + "[NPC] Rigel: " + ChatColor.LIGHT_PURPLE;


    public PerkGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "PERKS"));
        this.p = p;
        addItems();
        gui.open(p);
    }

    public void createShopButton(String name, int slot, ItemStack item, int price, String description, String statsFileName){
        String isOwned = Tazpvp.perkManager.getStatsString(p, statsFileName)
                ? ChatColor.GREEN + "Unlocked"
                : ChatColor.RED + "Locked";
        ItemBuilder b = new ItemBuilder(item).setName(name).setLore(description, ChatColor.DARK_AQUA + "Cost: " + ChatColor.AQUA + price + " Shards", isOwned);
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            if (Tazpvp.perkManager.getStatsString(p, statsFileName)) {
                p.sendMessage(prefix + "You already have this perk!");
            } else if (Tazpvp.statsManager.getShards(p) >= price){
                Tazpvp.perkManager.setStatsString(p, statsFileName, true);
                Tazpvp.statsManager.addShards(p, -price);
                ItemStack itemstack = new ItemBuilder(item).setName(name).setLore(description);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            } else {
                p.sendMessage(prefix + "You don't have enough money!");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            }
        });
        gui.addButton(slot, button);
    }

    public void addItems(){
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        createShopButton(ChatColor.YELLOW + "Fat", 10, new ItemStack(Material.SLIME_BALL), 5, ChatColor.GRAY + "Gain an extra heart on kill.", "fatPerk");
        createShopButton(ChatColor.BLUE + "Excavator", 11, new ItemStack(Material.GOLDEN_PICKAXE), 7, ChatColor.GRAY + "Gain Exp for mining.", "excavatorPerk");
        createShopButton(ChatColor.LIGHT_PURPLE + "Builder", 12, new ItemStack(Material.SCAFFOLDING), 4, ChatColor.GRAY + "Chance to duplicate your placed block.", "builderPerk");
        createShopButton( ChatColor.GREEN + "Archer", 13, new ItemStack(Material.BOW), 6, ChatColor.GRAY + "Chance to duplicate arrow on shoot.", "archerPerk");
        createShopButton(ChatColor.AQUA + "Gobble", 14, new ItemStack(Material.CAKE), 7, ChatColor.GRAY + "Replenish your hunger on kill.", "gobblePerk");
        createShopButton(ChatColor.RED + "Agility", 15, new ItemStack(Material.FEATHER), 5, ChatColor.GRAY + "Gain speed on kill.", "agilityPerk");
        createShopButton(ChatColor.GOLD + "Tank", 16, new ItemStack(Material.NETHERITE_CHESTPLATE), 6, ChatColor.GRAY + "Chance to get 2 absorption hearts on kill.", "tankPerk");
    }
}
