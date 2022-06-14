package net.tazpvp.tazpvp.GUI.Guild;

import net.tazpvp.tazpvp.Guilds.Guild;
import net.tazpvp.tazpvp.Guilds.GuildUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class GuildBrowserGUI {
    private InventoryGUI gui;

    public GuildBrowserGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 6 * 9, "Guild Browser"));
        addItems(p);
        gui.open(p);
    }

    public void addItems(Player p) {
        gui.fill(0, 6 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        ItemButton playerGuild;
        if (GuildUtils.isInGuild(p)) {
            Guild g = GuildUtils.getGuild(p);
            playerGuild = ItemButton.create(new ItemBuilder(g.getIcon()).setName(ChatColor.YELLOW + g.name()).setLore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Click to view your guild."), (e) -> {
                new GuildInfoGUI(p, g);
            });
        } else {
            playerGuild = ItemButton.create(new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "You are not in a guild."), (e) -> {});
        }
        gui.addButton(4, playerGuild);

        gui.update();
    }
}
