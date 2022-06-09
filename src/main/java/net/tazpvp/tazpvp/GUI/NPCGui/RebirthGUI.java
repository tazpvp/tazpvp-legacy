package net.tazpvp.tazpvp.GUI.NPCGui;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import static net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils.rebirthPlayer;

public class RebirthGUI {
    private InventoryGUI gui;
    String prefix = ChatColor.DARK_PURPLE + "[NPC] Rigel: " + ChatColor.LIGHT_PURPLE;


    public RebirthGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "DEPTHS MENU"));
        addItems();
        gui.open(p);
    }

    public void addItems(){
        gui.fill(0, 27, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        ItemButton rebirth = ItemButton.create(new ItemBuilder(Material.TOTEM_OF_UNDYING).setName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "REBIRTH" + ChatColor.GRAY + " Lvl 100 Req.")
            .setLore(ChatColor.GRAY + "Warning: resets stats and items.",
                    ChatColor.DARK_PURPLE + "≻ +5 Exp Per Kill",
                    ChatColor.DARK_PURPLE + "≻ +1 Extra Heart",
                    ChatColor.DARK_PURPLE + "≻ Infinite Arrows")
            , e -> {
            Player p = (Player) e.getWhoClicked();
            if (Tazpvp.boolManager.getHasRebirthed(p)) { p.sendMessage(ChatColor.RED + "You have already rebirthed."); return; }
            if (Tazpvp.statsManager.getLevel(p) < 100) { p.sendMessage(ChatColor.RED + "You must be level 100 to rebirth."); p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1); return; }
            p.closeInventory();
            rebirthPlayer(p);
        });
        gui.addButton(11, rebirth);

        ChatColor gray = ChatColor.GRAY;

        ItemButton perks = ItemButton.create(new ItemBuilder(Material.GLOW_ITEM_FRAME).setName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "PERKS")
                .setLore(ChatColor.GRAY + "Permanent boosters."), e -> {
            Player p = (Player) e.getWhoClicked();
            p.closeInventory();
            new PerkGUI(p);
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
        });
        gui.addButton(13, perks);

        ItemButton home = ItemButton.create(new ItemBuilder(Material.DARK_OAK_DOOR).setName(ChatColor.RED + "" + ChatColor.BOLD + "LEAVE").setLore(ChatColor.GRAY + "Return home."), e -> {
            Player p = (Player) e.getWhoClicked();
            if (Tazpvp.punishmentManager.isBanned(p)) {
                p.sendMessage(ChatColor.RED + "Nice try");
                return;
            }
            p.teleport(configUtils.spawn);
            p.sendMessage(prefix + "Welcome back.");
            p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
            p.sendTitle(ChatColor.GREEN + "" + ChatColor.BOLD + "OVERWORLD", ChatColor.DARK_GREEN + "Welcome back.", 10, 100, 10);
        });
        gui.addButton(15, home);

        gui.update();
    }
}
