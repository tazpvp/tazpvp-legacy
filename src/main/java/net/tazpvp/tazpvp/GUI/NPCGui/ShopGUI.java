package net.tazpvp.tazpvp.GUI.NPCGui;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class ShopGUI {
    private InventoryGUI gui;
    String prefix = ChatColor.GOLD + "[NPC] Maxim " + ChatColor.WHITE;

    public ShopGUI(Player p){
        gui = new InventoryGUI(Bukkit.createInventory(null, 45, ChatColor.BLUE + "" + ChatColor.BOLD + "SHOP"));
        addItems();
        gui.open(p);
    }

    public void createShopButton(ItemStack item, int slot, int price, String name, String description, boolean rankRequired, boolean cIDRequired, Double cID){
        ItemBuilder b = new ItemBuilder(item).setName(name).setLore(description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price);
        if(rankRequired) b.setLore(description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price, "", ChatColor.GREEN + "Rank Required");
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            if (rankRequired && !p.hasPermission("tazpvp.rank.buy")){
                p.sendMessage(prefix + " A rank is required to attain this item.");
                return;
            }
            if (Tazpvp.statsManager.getCoins(p) >= price){
                Tazpvp.statsManager.addCoins(p, -price);
                ItemStack itemstack = new ItemBuilder(item).setName(name).setLore(description);
                if (cIDRequired){
                    ItemMeta meta = itemstack.getItemMeta();
                    meta.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "cid"), PersistentDataType.DOUBLE, cID);
                    itemstack.setItemMeta(meta);
                }
                p.getInventory().addItem(itemstack);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            } else {
                p.sendMessage(prefix + " You don't have enough money!");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            }
        });
        gui.addButton(slot, button);
    }

    public void addItems(){
        gui.fill(0, 45, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        //starting at 10
        createShopButton(new ItemStack(Material.ENDER_EYE, 1), 10, 45, ChatColor.WHITE + "Agility", ChatColor.BLUE + "Speed Boost", false, true, 1.0);
        createShopButton(new ItemStack(Material.GLOW_SQUID_SPAWN_EGG, 1), 11, 45, ChatColor.WHITE + "Extinguisher", ChatColor.BLUE + "Feel the mist", false, true, 2.0);
        createShopButton(new ItemStack(Material.OAK_PLANKS, 64), 12, 40, ChatColor.WHITE + "Planks", ChatColor.BLUE + "Placeable Blocks", false, false, null);
        createShopButton(new ItemStack(Material.ARROW, 5), 13, 13, ChatColor.WHITE + "Arrow", ChatColor.BLUE + "Bow Projectiles", false, false, null);
        createShopButton(new ItemStack(Material.COOKED_BEEF, 5), 14, 15, ChatColor.WHITE + "Steak", ChatColor.BLUE + "Arbies", false, false, null);
        createShopButton(new ItemStack(Material.GOLDEN_CARROT, 1), 15, 15, ChatColor.WHITE + "Gold Carrot", ChatColor.BLUE + "Healthy Choice", false, false, null);
        createShopButton(new ItemStack(Material.GOLDEN_APPLE, 1), 16, 25, ChatColor.WHITE + "Gold Apple", ChatColor.BLUE + "Not Steroids", false, false, null);
        createShopButton(new ItemStack(Material.GOLDEN_AXE, 1), 19, 20, ChatColor.WHITE + "Axe", ChatColor.BLUE + "Break Wood", false, false, null);
        createShopButton(new ItemStack(Material.SHEARS, 1), 20, 25, ChatColor.WHITE + "Scissors", ChatColor.BLUE + "Break Wool", false, false, null);
        createShopButton(new ItemBuilder(Material.SHIELD, 1).setDurability(335), 21, 25, ChatColor.WHITE + "Bad shield", ChatColor.BLUE + "Broken shiled", false, false, null);
        createShopButton(new ItemStack(Material.BLUE_WOOL, 64), 28, 50, ChatColor.WHITE + "Blue Blocks", ChatColor.BLUE + "Drip", true, false, null);
        createShopButton(new ItemStack(Material.PURPLE_WOOL, 64), 29, 50, ChatColor.WHITE + "Purple Blocks", ChatColor.BLUE + "Portal?", true, false, null);
        createShopButton(new ItemStack(Material.MAGENTA_WOOL, 64), 30, 50, ChatColor.WHITE + "Pink Blocks", ChatColor.BLUE + "Ice cream", true, false, null);
        createShopButton(new ItemStack(Material.YELLOW_WOOL, 64), 31, 50, ChatColor.WHITE + "Yellow Blocks", ChatColor.BLUE + "Who peed?", true, false, null);
        createShopButton(new ItemStack(Material.GREEN_WOOL, 64), 32, 50, ChatColor.WHITE + "Green Blocks", ChatColor.BLUE + "Bushy", true, false, null);
        createShopButton(new ItemStack(Material.BROWN_WOOL, 64), 33, 50, ChatColor.WHITE + "Brown Blocks", ChatColor.BLUE + "Ew", true, false, null);
        createShopButton(new ItemStack(Material.RED_WOOL, 64), 34, 50, ChatColor.WHITE + "Red Blocks", ChatColor.BLUE + "u mad?", true, false, null);
        gui.update();//have a nice day
    }
}
