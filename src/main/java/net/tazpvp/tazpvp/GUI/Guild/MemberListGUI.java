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

import java.util.*;

public class MemberListGUI {
    private InventoryGUI gui;

    public MemberListGUI(Player p, Guild guild) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 4 * 9, guild.name() + " Members"));
        addItems(p, guild);
        gui.open(p);
    }

    public void addItems(Player p, Guild g) {
        gui.fill(0, 4 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        String contributions = ChatColor.GRAY + "Kills: " + ChatColor.WHITE + g.getKillsPlayer(p.getUniqueId());
        String contributions2 = ChatColor.GRAY + "Deaths: " + ChatColor.WHITE + g.getDeathsPlayer(p.getUniqueId());

        ItemButton owner = ItemButton.create(new ItemBuilder(ItemUtils.skull(Bukkit.getOfflinePlayer(g.owner().get(0)))).setName(ChatColor.YELLOW + Bukkit.getOfflinePlayer(g.owner().get(0)).getName()).setLore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Guild Master", contributions, contributions2), (e) ->{});
        List<OfflinePlayer> members = new ArrayList<>();
        for (UUID uuid : g.staff()) {
            OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
            members.add(op);
        }
        for (UUID uuid : g.members()) {
            OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
            members.add(op);
        }
        gui.addButton(4, owner);
        allOthers(members, g, p);

        LinkedHashMap<UUID, Double> killsLB = GuildUtils.sortedGuildKills(g);
        LinkedHashMap<UUID, Double> deathsLB = GuildUtils.sortedGuildDeaths(g);

        List<String> kills = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            if (i >= killsLB.size()) {
                break;
            }
            int place = i + 1;
            OfflinePlayer op = Bukkit.getOfflinePlayer(killsLB.keySet().stream().toList().get(i));
            kills.add(ChatColor.GRAY + "#" + place + " " + ChatColor.WHITE + op.getName() + " - " + ChatColor.GREEN + killsLB.get(op.getUniqueId()) + "\n");
        }
        String[] yes = kills.toArray(new String[0]);

        List<String> deaths = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            if (i >= deathsLB.size()) {
                break;
            }
            int place = i + 1;
            OfflinePlayer op = Bukkit.getOfflinePlayer(killsLB.keySet().stream().toList().get(i));
            deaths.add(ChatColor.GRAY + "#" + place + " " + ChatColor.WHITE + op.getName() + " - " + ChatColor.RED + deathsLB.get(op.getUniqueId()) + "\n");
        }

        String[] no = deaths.toArray(new String[0]);

        ItemButton killsLBBTN = ItemButton.create(new ItemBuilder(Material.OAK_SIGN).setName(ChatColor.RED + "Kills Leaderboard").setLore(yes), (e) ->{});

        ItemButton deathsLBBTN = ItemButton.create(new ItemBuilder(Material.OAK_SIGN).setName(ChatColor.RED + "Deaths Leaderboard").setLore(no), (e) ->{});

        gui.addButton(4 * 9 - 6, killsLBBTN);
        gui.addButton(4 * 9 - 4, deathsLBBTN);

        ItemButton closeBTN = ItemButton.create(new ItemBuilder(Material.ARROW).setName(ChatColor.RED + "Return").setLore(ChatColor.GRAY + "Return to the main screen."), (e) -> {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
            new BukkitRunnable() {
                @Override
                public void run() {
                    new GuildInfoGUI(p, g);
                }
            }.runTaskLater(Tazpvp.getInstance(), 2L);
        });
        gui.addButton(4 * 9 - 1, closeBTN);

        gui.update();
    }

    public void allOthers(List<OfflinePlayer> members, Guild g, Player viewer) {
        int index = 10;
        for (OfflinePlayer p : members) {
            ChatColor nameColor = g.staff().contains(p.getUniqueId()) ? ChatColor.GREEN : ChatColor.GRAY;
            ChatColor rankColor = g.staff().contains(p.getUniqueId()) ? ChatColor.DARK_GREEN : ChatColor.GRAY;
            String contributions = ChatColor.GRAY + "Kills: " + ChatColor.WHITE + g.getKillsPlayer(p.getUniqueId());
            String contributions2 = ChatColor.GRAY + "Deaths: " + ChatColor.WHITE + g.getDeathsPlayer(p.getUniqueId());
            String extraLore = g.hasPerms(viewer) ? ChatColor.RED + "Click me to edit!" : "";
            ItemStack plrItem;
            if (g.hasPerms(viewer)) {
                plrItem = new ItemBuilder(ItemUtils.skull(p)).setName(nameColor + p.getName()).setLore(rankColor + g.getGroup(p.getUniqueId()), contributions, contributions2, "", extraLore);
            } else {
                plrItem = new ItemBuilder(ItemUtils.skull(p)).setName(nameColor + p.getName()).setLore(rankColor + g.getGroup(p.getUniqueId()), contributions, contributions2);
            }
            ItemButton plr = ItemButton.create(plrItem, (e) -> {
                if (!viewer.getUniqueId().equals(p.getUniqueId())) {
                    if (g.isOwner(viewer.getUniqueId())) ownerGui(viewer, p, g);
                    else if (g.isStaff(viewer.getUniqueId())) staffGui(viewer, p, g);
                }
            });

            gui.addButton(index, plr);

            if (index == 16 || index == 25) {
                index += 2;
            }
            index++;
        }

        if (members.isEmpty()) {
            ItemButton emptylmfao = ItemButton.create(new ItemBuilder(Material.OAK_SIGN).setName(ChatColor.WHITE + "Such Lonely (╯°□°）╯︵ ┻━┻").setLore(ChatColor.GRAY + "Invite some members with /guild invite or the paper button"), e->{});
            gui.addButton(22, emptylmfao);
        }

        if (g.hasPerms(viewer)) {
            ItemButton inviteBTN = ItemButton.create(new ItemBuilder(Material.PAPER).setName(ChatColor.GREEN + "Invite Player").setLore(ChatColor.GRAY + "Invite a player to your guild."), (e) -> {
                new InviteGuildGUI(viewer, g);
            });
            gui.addButton(27, inviteBTN);
        }

        gui.update();
    }

    public void ownerGui(Player p, OfflinePlayer target, Guild g) {
        gui.clear();
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
        });
        gui.addButton(4 * 9 - 1, closeBTN);
        gui.update();
    }

    public void staffGui(Player p, OfflinePlayer target, Guild g) {
        gui.clear();
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
        });
        gui.addButton(4 * 9 - 1, closeBTN);
        gui.update();
    }

    public void run(Player p, Sound sound) {
        p.closeInventory();
        p.playSound(p.getLocation(), sound, 1, 1);
        new BukkitRunnable() {
            @Override
            public void run() {
                new MemberListGUI(p, Tazpvp.guildManager.getGuild(Tazpvp.guildManager.getPlayerGuild(p)));
            }
        }.runTaskLater(Tazpvp.getInstance(), 2L);
    }
}
