package net.tazpvp.tazpvp.GUI.Guild;

import net.tazpvp.tazpvp.Guilds.Guild;
import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GuildBrowserGUI {
    private InventoryGUI gui;
    private int pagesNeeded;
    private int numNum = 28;

    public GuildBrowserGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 6 * 9, "Guild Browser"));
        pagesNeeded = (int) Math.ceil(GuildUtils.sortedGuilds().size() / (double) (4 * 7));
        pageChange(p, 0);
        gui.open(p);
    }

    public void addItems(Player p) {
        defaults(p);

        Map<UUID, Double> guilds = GuildUtils.sortedGuilds();

        //pagesNeeded = (int) Math.ceil(guilds.size() / (double) (4 * 7));

    }

    public void pageChange(Player p, int page) {
        defaults(p);
        LinkedHashMap<UUID, Double> guilds = GuildUtils.sortedGuilds();


        int displacement = 10;
        for(int i = (page * numNum); i < Math.min(numNum + (page*numNum), guilds.size()); i++) {
            Guild g = Tazpvp.guildManager.getGuild(UUID.fromString(String.valueOf(guilds.keySet().toArray()[i])));
            String tag = (g.tag() == null) ? "" : ChatColor.YELLOW + " [" + g.tag() + "]";
            ItemButton guildView = ItemButton.create(new ItemBuilder(g.getIcon()).setName(g.name() + tag).setLore(
                    "",
                    ChatColor.WHITE + g.description(),
                    ChatColor.GRAY + "-" + Bukkit.getOfflinePlayer(g.owner().get(0)).getName(),
                    "",
                    ChatColor.GRAY + "Members: " + ChatColor.WHITE + g.allMembers().size(),
                    ChatColor.GRAY + "Kills: " + ChatColor.WHITE + (int) g.getKills()
            ), (e) -> {});

            gui.addButton(displacement, guildView);

            if((i+1) % 7 == 0) {
                displacement += 2;
            }
            displacement++;
        }

        if (page != 0) {
            ItemButton lastPage = ItemButton.create(new ItemBuilder(Material.ARROW).setName(ChatColor.GREEN + "Last Page"), (e) -> {
                if((page - 1) * numNum >= 0) pageChange(p, page - 1);
            });
            gui.addButton(45, lastPage);
        }

        if (page != pagesNeeded - 1) {
            ItemButton nextPage = ItemButton.create(new ItemBuilder(Material.ARROW).setName(ChatColor.GREEN + "Next Page"), (e) -> {
                if((page + 1) * numNum < guilds.size()) pageChange(p, page + 1);
            });

            gui.addButton(53, nextPage);
        }

        gui.update();
    }

    public void defaults(Player p) {
        gui.clear();
        gui.fill(0, 6 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        ItemButton playerGuild;
        if (GuildUtils.isInGuild(p)) {
            Guild g = GuildUtils.getGuild(p);
            playerGuild = ItemButton.create(new ItemBuilder(g.getIcon()).setName(ChatColor.YELLOW + g.name()).setLore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Click to view your guild."), (e) -> {
                p.closeInventory();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        new GuildInfoGUI(p, g);
                    }
                }.runTaskLater(Tazpvp.getInstance(), 2L);
            });
        } else {
            playerGuild = ItemButton.create(new ItemBuilder(Material.MINECART).setName(ChatColor.RED + "You are not in a guild.").setLore(
                    ChatColor.YELLOW + "Click to buy a guild.",
                    ChatColor.GOLD + "$6,000 Coins",
                    ChatColor.AQUA + "10 Credits"
            ), (e) -> {
                p.closeInventory();
                GuildUtils.createGuildAnvilGui(p);
            });
        }
        gui.addButton(4, playerGuild);

        gui.update();
    }

}
