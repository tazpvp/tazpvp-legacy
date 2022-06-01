package net.tazpvp.tazpvp.GUI.Template.MutliGuiPrototype;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class TestOne {
    private InventoryGUI gui;

    public TestOne(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "TestGUI"));
        addItems();
        gui.open(p);
    }

    public void addItems(){
        gui.fill(0, 26, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        ItemButton button = ItemButton.create(new ItemBuilder(Material.STONE)
                .setName("Open sub menu!"), e -> {
            secondGui();
        });
        gui.addButton(13, button);

        gui.update();
    }

    public void secondGui() {
        gui.clear();
        gui.fill(0, 26, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        ItemButton btn1 = ItemButton.create(new ItemBuilder(Material.DIAMOND_SHOVEL)
                .setName("Button 1!"), e -> {
            e.getWhoClicked().closeInventory();
        });
        gui.addButton(12, btn1);

        ItemButton btn2 = ItemButton.create(new ItemBuilder(Material.LEATHER)
                .setName("Button 2!"), e -> {
            e.getWhoClicked().closeInventory();
        });
        gui.addButton(14, btn2);

        gui.update();
    }
}
