package net.tazpvp.tazpvp.GUI.NPCGui;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Ranks.RankUtils;
import net.tazpvp.tazpvp.Utils.Ranks.RankWeight;
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
    private Player p;
    String prefix = ChatColor.GOLD + "[NPC] Maxim" + ChatColor.YELLOW;

    public ShopGUI(Player p){
        gui = new InventoryGUI(Bukkit.createInventory(null, 9*4, ChatColor.BLUE + "" + ChatColor.BOLD + "SHOP"));
        addItems();
        this.p = p;
        gui.open(p);
    }

    public void createShopButton(int slot, int price, ItemStack item, String name, String description, boolean cIDRequired, Double cID){
        ItemBuilder b = new ItemBuilder(item).setName(ChatColor.GRAY + name).setLore(ChatColor.BLUE + description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price);
        if(item.getType() == Material.BLUE_WOOL) b.setLore(ChatColor.BLUE + description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price, "", ChatColor.GREEN + "Colored based on your rank.");
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            if (Tazpvp.statsManager.getCoins(p) >= price){
                if (item.getType() == Material.BLUE_WOOL) {
                    if (p.hasPermission("tazpvp.staff")) {
                        item.setType(Material.PURPLE_WOOL);
                    } else if (p.hasPermission("tazpvp.mvp+")){
                        item.setType(Material.YELLOW_WOOL);
                    } else if (p.hasPermission("tazpvp.mvp")) {
                        item.setType(Material.GREEN_WOOL);
                    } else if (p.hasPermission("tazpvp.vip")) {
                        item.setType(Material.RED_WOOL);
                    } else {
                        p.sendMessage(prefix + " VIP rank or above is required to attain this item.");
                        return;
                    }
                }
                Tazpvp.statsManager.addCoins(p, -price);
                ItemStack itemstack = new ItemBuilder(item).setName(ChatColor.GRAY + name).setLore(ChatColor.BLUE + description);
                if (cIDRequired){
                    ItemMeta meta = itemstack.getItemMeta();
                    meta.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "cid"), PersistentDataType.DOUBLE, cID);
                    itemstack.setItemMeta(meta);
                }
                p.getInventory().addItem(itemstack);
                p.sendMessage(prefix + " You purchased " + ChatColor.GOLD + name + ChatColor.YELLOW + " for " + ChatColor.GOLD + "$" + price);
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

        createShopButton(10, 32, new ItemStack(Material.DRAGON_BREATH, 3),"Agility", "Speed Boost", true, 1.0);
        createShopButton(11, 10, new ItemStack(Material.BLUE_ORCHID, 1),"Extinguisher","Feel the mist", true, 2.0);
        createShopButton(12, 28, new ItemStack(Material.COBWEB, 5),"Insta-Web","Slow down enemies.", false, null);
        createShopButton(13, 18, new ItemStack(Material.INK_SAC, 3),"Inker","Morb people", false, null);
        createShopButton(14, 192, new ItemStack(Material.FLINT_AND_STEEL, 1),"Lighter","Burn!", false, null);
        createShopButton(15, 10, new ItemBuilder(Material.SHIELD, 1).setDurability(335),"Rusty shield","One time use", false, null);
        createShopButton(16, 28, new ItemStack(Material.OAK_PLANKS, 64),"Planks","Placeable Blocks", false, null);

        createShopButton(19, 33, new ItemStack(Material.GOLDEN_AXE, 1),"Axe","Break Wood", false, null);
        createShopButton(20, 26, new ItemStack(Material.SHEARS, 1),"Scissors","Break Wool", false, null);
        createShopButton(21, 13, new ItemStack(Material.ARROW, 5),"Arrow","Bow Projectiles", false, null);
        createShopButton(22, 15, new ItemStack(Material.COOKED_BEEF, 5),"Steak","Arbies", false, null);
        createShopButton(23, 15, new ItemStack(Material.GOLDEN_CARROT, 3),"Gold Carrot","Healthy Choice", false, null);
        createShopButton(24, 29, new ItemStack(Material.GOLDEN_APPLE, 1),"Gold Apple","Not Steroids", false, null);
        createShopButton(25, 17, new ItemStack(Material.BLUE_WOOL, 64),"RGB Blocks","Drip", false, null);

        gui.update();
    }
}
