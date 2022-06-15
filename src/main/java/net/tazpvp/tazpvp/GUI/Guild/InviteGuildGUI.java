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
import redempt.redlib.itemutils.ItemUtils;

import java.util.*;

public class InviteGuildGUI {
    private InventoryGUI gui;
    private int pagesNeeded;
    private int numNum = 28;

    public InviteGuildGUI(Player p, Guild guild) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 6 * 9, "Invite to " + guild.name()));
        pageChange(p, 0, guild);
        gui.open(p);
    }

    public void pageChange(Player p, int page, Guild g) {
        defaults(p, g);
        List<Player> playersAvaible = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!GuildUtils.isInGuild(player)) {
                playersAvaible.add(player);
            }
        }

        int displacement = 10;
        for(int i = (page * numNum); i < Math.min(numNum + (page*numNum), playersAvaible.size()); i++) {
            Player plr = playersAvaible.get(i);
            ItemButton plrView = ItemButton.create(new ItemBuilder(ItemUtils.skull(plr)).setName(plr.getName()).setLore(
                    ChatColor.DARK_GRAY + "Level: "  + Tazpvp.statsManager.getLevel(plr),
                    ChatColor.DARK_GRAY + "Kills: "  + Tazpvp.statsManager.getKills(plr),
                    ChatColor.DARK_GRAY + "Death: "  + Tazpvp.statsManager.getDeaths(plr)
            ), (e) -> {
                GuildUtils.invite(p, plr, g);
                p.closeInventory();
            });

            gui.addButton(displacement, plrView);

            if((i+1) % 7 == 0) {
                displacement += 2;
            }
            displacement++;
        }

        if (page != 0) {
            ItemButton lastPage = ItemButton.create(new ItemBuilder(Material.ARROW).setName(ChatColor.GREEN + "Last Page"), (e) -> {
                if((page - 1) * numNum >= 0) pageChange(p, page - 1, g);
            });
            gui.addButton(45, lastPage);
        }

        if (page != pagesNeeded - 1) {
            ItemButton nextPage = ItemButton.create(new ItemBuilder(Material.ARROW).setName(ChatColor.GREEN + "Next Page"), (e) -> {
                if((page + 1) * numNum < playersAvaible.size()) pageChange(p, page + 1, g);
            });

            gui.addButton(53, nextPage);
        }

        gui.update();
    }


    public void defaults(Player p, Guild g) {
        gui.clear();
        gui.fill(0, 6 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        ItemButton instructions = ItemButton.create(new ItemBuilder(Material.OAK_SIGN).setName("Player List").setLore(ChatColor.GRAY + "Click on a Player to invite them to " + g.name()), (e) -> {});

        gui.addButton(4, instructions);

        gui.update();
    }
}
