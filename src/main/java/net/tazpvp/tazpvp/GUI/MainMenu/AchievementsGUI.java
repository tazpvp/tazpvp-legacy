package net.tazpvp.tazpvp.GUI.MainMenu;

import net.tazpvp.tazpvp.Achievements.Achievements;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.List;

public class AchievementsGUI {
    private InventoryGUI gui;

    public AchievementsGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "ACHIEVEMENTS"));
        addItems(p);
        gui.open(p);
    }

    public void addItems(Player p) {
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        int i = 10;
        for (Achievements ach : Achievements.values()) {
            String name = ach.getName();
            List<String> lore = ach.getLore();
            Material material = Material.MINECART;

            if (Tazpvp.achievementManager.statsFile.getBoolean((p.getUniqueId().toString() + ach.getStatsFileName()))) {
                material = Material.CHEST_MINECART;
                lore.add(ChatColor.GREEN + "Completed!");
            } else {
                lore.add(ChatColor.RED + "Incomplete!");
            }

            ItemButton tool = ItemButton.create(new ItemBuilder(material).setName(name).setLore(String.valueOf(lore)), e -> {
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
