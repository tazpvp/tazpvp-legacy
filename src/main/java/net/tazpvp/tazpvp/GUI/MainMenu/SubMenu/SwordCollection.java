package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.tazpvp.tazpvp.Managers.PlayerWrapperManagers.PlayerWrapper;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;

import java.util.List;

public class SwordCollection {
    private final InventoryGUI gui;
    public SwordCollection(Player p){
        gui = new InventoryGUI(Bukkit.createInventory(null, 5*9, ChatColor.BLUE + "" + ChatColor.BOLD + "WEAPONRY"));
        setitems(p);
        gui.open(p);
    }

    public void setitems(Player p){
        gui.fill(0, 5*9, new redempt.redlib.itemutils.ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));
        int i = 10;
        for (Items item : Items.values()) {
            PlayerWrapper pw = Tazpvp.playerWrapperMap.get(p.getUniqueId());
            List<Items> items = pw.getSwords();
            ItemStack itemStack = ItemBuilder.maekItem(item);;
            List<String> lore = new java.util.ArrayList<>(List.of(item.getLore()));
            String addtxt = items.contains(item) ? ChatColor.GREEN + "Unlocked" : ChatColor.RED + "Locked";

            lore.add(ChatColor.DARK_AQUA + "Damage: " + ChatColor.AQUA + item.getDamage());
            lore.add("");
            lore.add(item.getRarity());
            lore.add("");
            lore.add(addtxt);
            String[] split = lore.toString().split(", ");
            split[0] = split[0].replace("[", "");
            split[split.length - 1] = split[split.length - 1].replace("]", "");

            ItemButton tool = ItemButton.create(new redempt.redlib.itemutils.ItemBuilder(item.getMaterial()).setName(item.getName()).setLore(split), e -> {
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
