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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AcceptGuildGUI {
    private InventoryGUI gui;

    public AcceptGuildGUI(Player p, Guild g) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 3 * 9, "Accept Guild Invite"));
        addItems(p, g);
        gui.open(p);
    }

    public void addItems(Player p, Guild g) {
        gui.fill(0, 3 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        ItemButton guildView = ItemButton.create(new ItemBuilder(g.getIcon()).setName(ChatColor.GREEN + g.name()).setLore(ChatColor.GRAY + g.description()), e -> {});

        LinkedHashMap<UUID, Double> guilds = GuildUtils.sortedGuilds();
        List<UUID> uuidList = new LinkedList<>(guilds.keySet());
        int index = uuidList.indexOf(g.getID());
        index++;

        ItemButton stats = ItemButton.create(new ItemBuilder(Material.ARMOR_STAND).setName(ChatColor.YELLOW + "Stats").setLore(
                ChatColor.DARK_GREEN + "Kills: " + ChatColor.GREEN + g.getKills(),
                ChatColor.DARK_GREEN + "Ranking: " + ChatColor.GREEN + "#" + index
        ), e -> {});

        ItemButton accept = ItemButton.create(new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName(ChatColor.GREEN + "Accept").setLore(ChatColor.GRAY + "You can always get invited later"), e -> {
            GuildUtils.acceptInvite(p);
        });

        gui.addButton(11, guildView);
        gui.addButton(12, stats);
        gui.addButton(14, accept);

        gui.update();
    }
}
