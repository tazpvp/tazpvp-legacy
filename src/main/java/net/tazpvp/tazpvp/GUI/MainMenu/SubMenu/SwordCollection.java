package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class SwordCollection {
    private final InventoryGUI gui;
    public SwordCollection(Player p){
        gui = new InventoryGUI(Bukkit.createInventory(null, 5*9, ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "WEAPONRY"));
        setitems();
        gui.open(p);
    }

    public void setitems(){
        gui.fill(0, 5*9, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        int i = 10;
        for (Items item : Items.values()) {
            String name = item.getName();
            String[] lore = item.getLore();
            Material material = item.getMaterial();
            int damage = item.getDamage();
            String rarity = item.getRarity();

            ItemButton tool = ItemButton.create(new ItemBuilder(material).setName(name).setLore(lore), e -> {
                e.setCancelled(true);
            });
            gui.addButton(i, tool);

            if((i+1) % 7 == 0) {
                i += 2;
            }
            i++;
        }

        gui.update();
    }
}
