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
    String prefix = ChatColor.GOLD + "[NPC] Maxim" + ChatColor.WHITE;

    public ShopGUI(Player p){
        gui = new InventoryGUI(Bukkit.createInventory(null, 9*4, ChatColor.BLUE + "" + ChatColor.BOLD + "SHOP"));
        addItems();
        gui.open(p);
    }

    public void createShopButton(int slot, int price, ItemStack item, String name, String description, boolean rankRequired, boolean cIDRequired, Double cID){
        ItemBuilder b = new ItemBuilder(item).setName(ChatColor.GRAY + name).setLore(ChatColor.BLUE + description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price);
        if(rankRequired) b.setLore(ChatColor.BLUE + description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price, "", ChatColor.GREEN + "Rank Required");
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
        gui.fill(0, 9*4, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        createShopButton(10, 34, new ItemStack(Material.ENDER_EYE, 1),"Agility", "Speed Boost", false, true, 1.0);
        createShopButton(11, 8, new ItemStack(Material.GLOW_SQUID_SPAWN_EGG, 1),"Extinguisher","Feel the mist", false, true, 2.0);
        createShopButton(12, 32, new ItemStack(Material.COBWEB, 5),"Insta-Web","Slow down enemies.", false, false, null);
        createShopButton(13, 18, new ItemStack(Material.INK_SAC, 1),"Inker","Morb people", false, false, null);
        createShopButton(13, 12, new ItemStack(Material.MILK_BUCKET, 1),"Milk","Refreshing", false, false, null);
        createShopButton(15, 9, new ItemBuilder(Material.SHIELD, 1).setDurability(335),"Rusty shield","One time use", false, false, null);
        createShopButton(16, 28, new ItemStack(Material.OAK_PLANKS, 64),"Planks","Placeable Blocks", false, false, null);

        createShopButton(19, 33, new ItemStack(Material.GOLDEN_AXE, 1),"Axe","Break Wood", false, false, null);
        createShopButton(20, 26, new ItemStack(Material.SHEARS, 1),"Scissors","Break Wool", false, false, null);
        createShopButton(21, 13, new ItemStack(Material.ARROW, 5),"Arrow","Bow Projectiles", false, false, null);
        createShopButton(22, 15, new ItemStack(Material.COOKED_BEEF, 5),"Steak","Arbies", false, false, null);
        createShopButton(23, 15, new ItemStack(Material.GOLDEN_CARROT, 1),"Gold Carrot","Healthy Choice", false, false, null);
        createShopButton(24, 27, new ItemStack(Material.GOLDEN_APPLE, 1),"Gold Apple","Not Steroids", false, false, null);
        createShopButton(25, 17, new ItemStack(Material.BLUE_WOOL, 64),"Blue Blocks","Drip", true, false, null);







        gui.update();
    }
}
