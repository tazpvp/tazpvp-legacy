package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;

public class SwordCollection {
    private final InventoryGUI gui;
    public SwordCollection(Player p){
        gui = new InventoryGUI(Bukkit.createInventory(null, 5*9, ChatColor.BLUE + "" + ChatColor.BOLD + "WEAPONRY"));
        setitems();
        gui.open(p);
    }

    public void setitems(){
        gui.fill(0, 5*9, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        int i = 10;
        for (Items item : Items.values()) {
            ItemButton tool = ItemButton.create(ItemBuilder.maekItem(item), e -> {
                e.setCancelled(true);
            });
            gui.addButton(i, tool);

            if (i == 16 || i == 25) {
                i += 2;
            }
            i++;

        }

        gui.update();
    }
}
