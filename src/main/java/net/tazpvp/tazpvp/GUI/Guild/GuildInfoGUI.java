package net.tazpvp.tazpvp.GUI.Guild;

import net.tazpvp.tazpvp.Guilds.Guild;
import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class GuildInfoGUI {
    private InventoryGUI gui;

    public GuildInfoGUI(Player p, Guild guild) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 3 * 9, guild.name() + " Info"));
        addItems(p, guild);
        gui.open(p);
    }

    public void addItems(Player p, Guild g) {
        gui.fill(0, 3 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        ItemButton owner = ItemButton.create(new ItemBuilder(ItemUtils.skull(Bukkit.getOfflinePlayer(g.owner().get(0)))).setName(ChatColor.YELLOW + Bukkit.getOfflinePlayer(g.owner().get(0)).getName()).setLore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Guild Master"), (e) ->{});
        ItemButton members = ItemButton.create(new ItemBuilder(Material.ITEM_FRAME).setName(ChatColor.WHITE + "Members").setLore(ChatColor.GRAY + "View all members"), e -> {
            new MemberListGUI(p, g);
        });
        String tag = g.tag() == null ? "" : " " + ChatColor.YELLOW + "[" + g.tag() + "]";

        ItemButton info = (g.isOwner(p.getUniqueId()) ?
                ItemButton.create(new ItemBuilder(g.getIcon()).setName(ChatColor.GREEN + g.name() + tag).setLore(ChatColor.GRAY + g.description(), ChatColor.RED + "Click to edit"), e -> {
                    p.closeInventory();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            new EditGuildGUI(p, g);
                        }
                    }.runTaskLater(Tazpvp.getInstance(), 2L);
                })
                :
                ItemButton.create(new ItemBuilder(g.getIcon()).setName(ChatColor.GREEN + g.name() + tag).setLore(ChatColor.GRAY + g.description()), e -> {}));

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        LinkedHashMap<UUID, Double> guilds = GuildUtils.sortedGuilds();
        List<UUID> uuidList = new LinkedList<>(guilds.keySet());
        int index = uuidList.indexOf(g.getID());
        index++;

        ItemButton stats = ItemButton.create(new ItemBuilder(Material.ARMOR_STAND).setName(ChatColor.YELLOW + "Stats").setLore(
                ChatColor.DARK_GREEN + "Kills: " + ChatColor.GREEN + g.getKills(),
                ChatColor.DARK_GREEN + "Deaths: " + ChatColor.GREEN + g.getDeaths(),
                ChatColor.DARK_GREEN + "KDR: " + ChatColor.GREEN + df.format(g.getKDR()),
                ChatColor.DARK_GREEN + "Ranking: " + ChatColor.GREEN + "#" + index
        ), e -> {});

        ItemButton help = ItemButton.create(new ItemBuilder(Material.OAK_SIGN).setName(ChatColor.YELLOW + "Help").setLore(
                ChatColor.WHITE + "Type a @ before your message to send it to guild chat!",
                ChatColor.WHITE + "Edit members by clicking their head in the members list!"
                        ), e -> {});

        gui.addButton(11, info);
        gui.addButton(13, stats);
        gui.addButton(15, owner);
        gui.addButton(16, members);
        gui.addButton(18, help);
        gui.update();
    }
}
