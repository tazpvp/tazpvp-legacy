package net.tazpvp.tazpvp.GUI;

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

public class ShopGUI {
    private InventoryGUI gui;

    public ShopGUI(Player p){
        gui = new InventoryGUI(Bukkit.createInventory(null, 54, "Shop"));
        addItems();
        gui.open(p);
    }

    public void createShopButton(ItemStack item, int slot, int price, String name, String description, boolean rankRequired){
        ItemBuilder b = new ItemBuilder(item).setName(name).setLore(description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price);
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            if (rankRequired && !p.hasPermission("tazpvp.rank.buy")){
                p.sendMessage(ChatColor.RED + "You do not have permission to buy this item.");
                return;
            }
            if (Tazpvp.statsManager.getMoney(p) >= price){
                Tazpvp.statsManager.addMoney(p, -price);
                p.getInventory().addItem(item);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            } else {
                p.sendMessage(ChatColor.RED + "You don't have enough money!");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            }
        });
        gui.addButton(slot, button);
    }

    public void addItems(){
        gui.fill(0, 54, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));
        //starting at 10
        createShopButton(new ItemStack(Material.GOLD_INGOT, 1), 10, 45, ChatColor.WHITE + "Butter", ChatColor.BLUE + "Health Boost\n", false);
        createShopButton(new ItemStack(Material.ENDER_EYE, 1), 10, 45, ChatColor.WHITE + "Agility", ChatColor.BLUE + "Speed Boost\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$45", false);
        createShopButton(new ItemStack(Material.PRISMARINE_SHARD, 1), 11, 45, ChatColor.WHITE + "Extinguisher", ChatColor.BLUE + "Feel the mist\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$45", false);
        createShopButton(new ItemStack(Material.SNOWBALL, 16), 12, 30, ChatColor.WHITE + "Balls", ChatColor.BLUE + "Brrrr\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$30", false);
        createShopButton(new ItemStack(Material.OAK_PLANKS, 64), 13, 40, ChatColor.WHITE + "Planks", ChatColor.BLUE + "Placeable Blocks\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$40", false);
        createShopButton(new ItemStack(Material.GOLDEN_HOE, 1), 14, 1500, ChatColor.WHITE + "Tactical Squid Launcher", ChatColor.BLUE + "Explosive Squids\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$1500", false);
        createShopButton(new ItemStack(Material.ARROW, 5), 15, 25, ChatColor.WHITE + "Arrow", ChatColor.BLUE + "Bow Projectiles\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$25", false);
        createShopButton(new ItemStack(Material.FEATHER, 1), 16, 150, ChatColor.WHITE + "Lethal Injection", ChatColor.BLUE + "Yikes\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$150", false);
        createShopButton(new ItemStack(Material.COOKIE, 5), 19, 10, ChatColor.WHITE + "Cookie", ChatColor.BLUE + "Yummy\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$10", false);
        createShopButton(new ItemStack(Material.BREAD, 5), 20, 10, ChatColor.WHITE + "Bread", ChatColor.BLUE + "Hot n' Fresh\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$10", false);
        createShopButton(new ItemStack(Material.BAKED_POTATO, 5), 21, 10, ChatColor.WHITE + "Baked Potato", ChatColor.BLUE + "What's that smell?\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$10", false);
        createShopButton(new ItemStack(Material.COOKED_BEEF, 5), 22, 15, ChatColor.WHITE + "Steak", ChatColor.BLUE + "Arbies\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$15", false);
        createShopButton(new ItemStack(Material.GOLDEN_CARROT, 1), 23, 15, ChatColor.WHITE + "Gold Carrot", ChatColor.BLUE + "Healthy Choice\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$15", false);
        createShopButton(new ItemStack(Material.GOLDEN_APPLE, 1), 24, 250, ChatColor.WHITE + "Gold Apple", ChatColor.BLUE + "Not Steroids\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$225", false);
        createShopButton(new ItemStack(Material.GOLDEN_AXE, 1), 28, 20, ChatColor.WHITE + "Axe", ChatColor.BLUE + "Break Wood\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$20", false);
        createShopButton(new ItemStack(Material.SHEARS, 1), 29, 25, ChatColor.WHITE + "Scissors", ChatColor.BLUE + "Break Wool\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$25", false);
        createShopButton(new ItemStack(Material.BLUE_WOOL, 64), 37, 50, ChatColor.WHITE + "Blue Blocks", ChatColor.BLUE + "Drip\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required", true);
        createShopButton(new ItemStack(Material.PURPLE_WOOL, 64), 38, 50, ChatColor.WHITE + "Purple Blocks", ChatColor.BLUE + "Portal?\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required", true);
        createShopButton(new ItemStack(Material.MAGENTA_WOOL, 64), 39, 50, ChatColor.WHITE + "Pink Blocks", ChatColor.BLUE + "Ice cream\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required", true);
        createShopButton(new ItemStack(Material.YELLOW_WOOL, 64), 40, 50, ChatColor.WHITE + "Yellow Blocks", ChatColor.BLUE + "Who peed?\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required", true);
        createShopButton(new ItemStack(Material.GREEN_WOOL, 64), 41, 50, ChatColor.WHITE + "Green Blocks", ChatColor.BLUE + "Bushy\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required", true);
        createShopButton(new ItemStack(Material.BROWN_WOOL, 64), 42, 50, ChatColor.WHITE + "Brown Blocks", ChatColor.BLUE + "Ew\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required", true);
        createShopButton(new ItemStack(Material.RED_WOOL, 64), 43, 50, ChatColor.WHITE + "Red Blocks", ChatColor.BLUE + "u mad?\n" + ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$60\n" + "\n" + ChatColor.GREEN + "Rank Required", true);

        gui.update();
    }
}
