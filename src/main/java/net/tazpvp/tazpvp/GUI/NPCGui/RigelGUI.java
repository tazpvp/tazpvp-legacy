package net.tazpvp.tazpvp.GUI.NPCGui;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class RigelGUI {
    private InventoryGUI gui;


    public RigelGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "TestGUI"));
        addItems();
        gui.open(p);
    }

    public void addItems(){
        gui.fill(0, 26, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        ItemButton button = ItemButton.create(new ItemBuilder(Material.BLAZE_POWDER)
                .setName("Rebirth")
                .setLore(ChatColor.GREEN + "Click to Rebirth"), e -> {
            Player p = (Player) e.getWhoClicked();
          if (Tazpvp.statsManager.getLevel(p) >= 100) {
              Tazpvp.statsManager.setLevel(p, 0);
              //run more rebirth functionality later
          } else {
              p.sendMessage(ChatColor.GREEN + "You cannot reberth yet");
          }
        });
        gui.addButton(12, button);

        ItemButton buttonbutton = ItemButton.create(new ItemBuilder(Material.SPRUCE_DOOR)
                .setName("go home")
                .setLore(ChatColor.GREEN + "Click to go to spawn"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.teleport(configUtils.spawn);
        });
        gui.addButton(14, buttonbutton);

        gui.update();
    }
}
