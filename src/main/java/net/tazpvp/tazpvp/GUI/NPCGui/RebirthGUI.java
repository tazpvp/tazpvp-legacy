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

public class RebirthGUI {
    private InventoryGUI gui;
    String prefix = ChatColor.DARK_PURPLE + "[NPC] Rigel: " + ChatColor.LIGHT_PURPLE;


    public RebirthGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "DEPTHS MENU"));
        addItems();
        gui.open(p);
    }

    public void addItems(){
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));

        ItemButton button = ItemButton.create(new ItemBuilder(Material.TOTEM_OF_UNDYING).setName(ChatColor.LIGHT_PURPLE + "REBIRTH" + ChatColor.GRAY + "Lvl 100 Req.")
            .setLore(ChatColor.LIGHT_PURPLE + "Exchange levels for:\n" +
                    ChatColor.DARK_PURPLE + "≻ +5 Exp Per Kill\n" +
                    ChatColor.DARK_PURPLE + "≻ +1 Extra Heart\n" +
                    ChatColor.DARK_PURPLE + "≻ Infinite Arrows\n")
            , e -> {
            Player p = (Player) e.getWhoClicked();
          if (Tazpvp.statsManager.getLevel(p) >= 100) {
              Tazpvp.statsManager.setLevel(p, 0);
              //run more rebirth functionality later
          } else {
              p.sendMessage(prefix + "You do not have enough levels to rebirth!");
          }
        });
        gui.addButton(11, button);

        ItemButton perks = ItemButton.create(new ItemBuilder(Material.GLOW_ITEM_FRAME).setName("Perks").setLore(ChatColor.GREEN + "view perks"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            new PerkGUI(p);
        });
        gui.addButton(13, button);

        ItemButton buttonbutton = ItemButton.create(new ItemBuilder(Material.DARK_OAK_DOOR).setName("go home").setLore(ChatColor.GREEN + "Click to go to spawn"), e -> {
            Player p = (Player) e.getWhoClicked();
            p.teleport(configUtils.spawn);
            p.sendMessage(prefix + "Welcome back.");
        });
        gui.addButton(15, buttonbutton);

        gui.update();
    }
}
