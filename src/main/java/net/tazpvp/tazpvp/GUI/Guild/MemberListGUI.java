package net.tazpvp.tazpvp.GUI.Guild;

import net.tazpvp.tazpvp.Guilds.Guild;
import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class MemberListGUI {
    private InventoryGUI gui;

    public MemberListGUI(Player p, Guild guild) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 4 * 9, "Guild Members"));
        addItems(p, guild);
        gui.open(p);
    }

    public void addItems(Player p, Guild g) {
        gui.fill(0, 4 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));
        ItemButton owner = ItemButton.create(new ItemBuilder(ItemUtils.skull(p)).setName(ChatColor.YELLOW + p.getName()).setLore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Guild Master"), (e) ->{});
        List<OfflinePlayer> members = new ArrayList<>();
        for (UUID uuid : g.staff()) {
            OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
            members.add(op);
        }
        for (UUID uuid : g.members()) {
            OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
            members.add(op);
        }
        allOthers(members, g, p);
        gui.update();
    }

    public void allOthers(List<OfflinePlayer> members, Guild g, Player viewer) {
        int index = 10;
        for (OfflinePlayer p : members) {
            ChatColor nameColor = g.staff().contains(p.getUniqueId()) ? ChatColor.GREEN : ChatColor.GRAY;
            ChatColor rankColor = g.staff().contains(p.getUniqueId()) ? ChatColor.DARK_GREEN : ChatColor.GRAY;
            String extraLore = g.hasPerms(p) ? ChatColor.RED + "Click me to edit!" : "";

            ItemStack plrItem = new ItemBuilder(ItemUtils.skull(p)).setName(nameColor + p.getName()).setLore(rankColor + g.getGroup(p.getUniqueId()), extraLore);
            ItemButton plr = ItemButton.create(plrItem, (e) -> {
                if (g.isOwner(p.getUniqueId())) ownerGui(viewer, p, g);
                else if (g.isStaff(p.getUniqueId())) staffGui(viewer, p, g);
            });

            gui.addButton(index, plr);

            if (index == 16 || index == 25) {
                index += 2;
            }
            index++;
        }
        gui.update();
    }

    public void ownerGui(Player p, OfflinePlayer target, Guild g) {
        gui.fill(0, 4 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));
        ItemButton targetBTN = ItemButton.create(new ItemBuilder(ItemUtils.skull(target)).setName(ChatColor.RED + p.getName()).setLore(ChatColor.GRAY + g.getGroup(target.getUniqueId())), (e) ->{});
        gui.addButton(13, targetBTN);
        ItemButton rankBTN;
        if (!g.isStaff(target.getUniqueId())) {
            rankBTN = ItemButton.create(new ItemBuilder(Material.EMERALD_BLOCK).setName(ChatColor.GREEN + "Promote to Staff").setLore(ChatColor.GRAY + "Click me to promote " + target.getName() + " to staff!"), (e) -> {
                GuildUtils.promote(p, target, g);
                run(p, Sound.BLOCK_AMETHYST_BLOCK_PLACE);
            });
        } else {
            rankBTN = ItemButton.create(new ItemBuilder(Material.REDSTONE_BLOCK).setName(ChatColor.RED + "Demote to member").setLore(ChatColor.GRAY + "Click me to demote " + target.getName() + " to member."), (e) -> {
                GuildUtils.demote(p, target, g);
                run(p, Sound.BLOCK_AMETHYST_BLOCK_BREAK);
            });
        }
        gui.addButton(21, rankBTN);

        ItemButton kickBTN = ItemButton.create(new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "Kick").setLore(ChatColor.GRAY + "Kick " + target.getName() + " from the guild!"), (e) -> {
            GuildUtils.kickFromGuild(p, target, g);
            run(p, Sound.BLOCK_AMETHYST_BLOCK_BREAK);
        });

        gui.addButton(23, kickBTN);

        ItemButton closeBTN = ItemButton.create(new ItemBuilder(Material.ARROW).setName(ChatColor.RED + "Return").setLore(ChatColor.GRAY + "Return to the main screen."), (e) -> {
            run(p, Sound.BLOCK_ANVIL_PLACE);
            new BukkitRunnable() {
                @Override
                public void run() {
                    new MemberListGUI(p, Tazpvp.guildManager.getGuild(Tazpvp.guildManager.getPlayerGuild(p)));
                }
            }.runTaskLater(Tazpvp.getInstance(), 2L);
        });
        gui.addButton(4 * 9 - 1, closeBTN);
        gui.update();
    }

    public void staffGui(Player p, OfflinePlayer target, Guild g) {
        gui.fill(0, 4 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));
        ItemButton targetBTN = ItemButton.create(new ItemBuilder(ItemUtils.skull(target)).setName(ChatColor.RED + p.getName()).setLore(ChatColor.GRAY + g.getGroup(target.getUniqueId())), (e) ->{});
        gui.addButton(13, targetBTN);

        ItemButton kickBTN = ItemButton.create(new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "Kick").setLore(ChatColor.GRAY + "Kick " + target.getName() + " from the guild!"), (e) -> {
            GuildUtils.kickFromGuild(p, target, g);
            run(p, Sound.BLOCK_AMETHYST_BLOCK_BREAK);
        });

        gui.addButton(22, kickBTN);

        ItemButton closeBTN = ItemButton.create(new ItemBuilder(Material.ARROW).setName(ChatColor.RED + "Return").setLore(ChatColor.GRAY + "Return to the main screen."), (e) -> {
            run(p, Sound.BLOCK_ANVIL_PLACE);
            new BukkitRunnable() {
                @Override
                public void run() {
                    new MemberListGUI(p, Tazpvp.guildManager.getGuild(Tazpvp.guildManager.getPlayerGuild(p)));
                }
            }.runTaskLater(Tazpvp.getInstance(), 2L);
        });
        gui.addButton(4 * 9 - 1, closeBTN);
        gui.update();
    }

    public void run(Player p, Sound sound) {
        p.closeInventory();
        p.playSound(p.getLocation(), sound, 1, 1);
    }
}