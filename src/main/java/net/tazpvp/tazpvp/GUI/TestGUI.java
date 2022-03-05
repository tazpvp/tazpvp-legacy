package net.tazpvp.tazpvp.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class TestGUI {
    private InventoryGUI gui;

    public TestGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "TestGUI"));
        addItems();
        gui.open(p);
    }

    public void addItems(){
        gui.fill(0, 26, new ItemStack(Material.GRASS_BLOCK, 1));
        ItemButton button = ItemButton.create(new ItemBuilder(Material.STONE)
                .setName("Test")
                .setCount(2)
                .setDurability(3), e -> {
            e.getWhoClicked().sendMessage("hhhhhhhhhhhhhh");
        });
        gui.addButton(13, button);

        gui.update();
    }
}
