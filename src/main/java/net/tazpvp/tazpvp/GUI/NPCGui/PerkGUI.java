package net.tazpvp.tazpvp.GUI.NPCGui;

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
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "Perk GUI"));
        addItems();
        gui.open(p);
    }

    public void createShopButton(String name, int slot, ItemStack item, int price, String description){
        ItemBuilder b = new ItemBuilder(item).setName(name).setLore(description, ChatColor.GOLD + "Cost: " + ChatColor.GRAY + "$" + price);
        ItemButton button = ItemButton.create(b, e -> {
            Player p = (Player) e.getWhoClicked();
            if (Tazpvp.statsManager.getShards(p) >= price){
                Tazpvp.statsManager.addShards(p, -price);
                ItemStack itemstack = new ItemBuilder(item).setName(name).setLore(description);
                p.getInventory().addItem(itemstack);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            } else {
                p.sendMessage(ChatColor.RED + "You don't have enough money!");
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            }
        });
        gui.addButton(slot, button);
    }

    public void addItems(){
        gui.fill(0, 26, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        createShopButton("Fat I", 11, new ItemStack(Material.BREAD), 2, "Fat Perk");
    }
}
