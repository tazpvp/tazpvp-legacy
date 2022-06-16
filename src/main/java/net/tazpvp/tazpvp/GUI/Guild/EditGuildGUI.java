package net.tazpvp.tazpvp.GUI.Guild;

import net.tazpvp.tazpvp.Guilds.Guild;
import net.tazpvp.tazpvp.Tazpvp;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditGuildGUI {
    private InventoryGUI gui;
    private List<Material> icons = new ArrayList<>(Arrays.asList(
            Material.OAK_SIGN, Material.RAW_GOLD, Material.CHORUS_FLOWER, Material.ENCHANTING_TABLE, Material.BEACON, Material.END_PORTAL_FRAME, Material.TURTLE_EGG, Material.WITHER_ROSE, Material.DIRT, Material.TARGET, Material.DIAMOND, Material.EMERALD, Material.LAPIS_LAZULI, Material.AMETHYST_SHARD, Material.GOLDEN_APPLE, Material.NETHERITE_CHESTPLATE, Material.CARROT_ON_A_STICK, Material.LEATHER, Material.LAVA_BUCKET, Material.ENDER_PEARL, Material.ENDER_EYE, Material.SLIME_BALL, Material.GLOW_INK_SAC, Material.PUFFERFISH, Material.MAGMA_CREAM, Material.BLAZE_POWDER, Material.FIRE_CHARGE, Material.DIAMOND_HORSE_ARMOR
    ));

    public EditGuildGUI(Player player, Guild guild) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 3 * 9, "Edit " + guild.name()));
        addItems(player, guild);
        gui.open(player);
    }

    public void addItems(Player p, Guild g) {
        gui.fill(0, 3 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        //11 = guild owner
        //12 = guild icon

        //14 = guild tag
        //15 = guild description

        // 3 * 9 - 1 = go back

        ItemButton owner = ItemButton.create(new ItemBuilder(ItemUtils.skull(Bukkit.getOfflinePlayer(g.owner().get(0)))).setName(ChatColor.YELLOW + Bukkit.getOfflinePlayer(g.owner().get(0)).getName()).setLore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Guild Master"), (e) ->{});

        ItemButton icon = ItemButton.create(new ItemBuilder(g.getIcon()).setName(ChatColor.YELLOW + "Edit Icon"), (e) ->{
            editIcon(p, g);
        });

        String currTag = (g.tag() == null) ? ChatColor.GRAY + "No Tag" : ChatColor.GRAY + "[" + g.tag() + "]";
        ItemButton tag = ItemButton.create(new ItemBuilder(Material.NAME_TAG).setName(ChatColor.YELLOW + "Edit Tag").setLore(currTag,ChatColor.AQUA + "50 credits"), (e) ->{
            editTag(p, g);
        });

        ItemButton desc = ItemButton.create(new ItemBuilder(Material.WRITABLE_BOOK).setName(ChatColor.YELLOW + "Edit Description").setLore(ChatColor.GRAY + g.description()), (e) ->{
            editDesc(p, g);
        });

        ItemButton back = ItemButton.create(new ItemBuilder(Material.ARROW).setName(ChatColor.YELLOW + "Go Back"), (e) ->{
            p.closeInventory();
            new BukkitRunnable() {
                @Override
                public void run() {
                    new GuildInfoGUI(p, g);
                }
            }.runTaskLater(Tazpvp.getInstance(), 1);
            new GuildInfoGUI(p, g);
        });

        gui.addButton(owner, 11);
        gui.addButton(icon, 12);
        gui.addButton(tag, 14);
        gui.addButton(desc, 15);
        gui.addButton(back, 3 * 9 - 1);

        gui.update();
    }

    public void editIcon(Player p, Guild g) {
        p.closeInventory();
        InventoryGUI iconGui = new InventoryGUI(Bukkit.createInventory(null, 6 * 9, "Edit " + g.name()));
        iconGui.fill(0, 6 * 9, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));
        int index = 10;
        for (Material m : icons) {
            ItemButton iconBTN = ItemButton.create(new ItemBuilder(m).setName(ChatColor.YELLOW + m.name()).setLore(ChatColor.GREEN + "Click to set icon", ChatColor.DARK_AQUA + "Cost: " + ChatColor.AQUA + "50 credits"), (e) -> {
                if (Tazpvp.statsManager.getCredits(p) >= 50) {
                    Tazpvp.statsManager.addCredits(p, -50);
                    g.setIcon(m);
                    Tazpvp.guildManager.setGuild(g.getID(), g);
                    p.closeInventory();
                    g.sendAlL(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " has set the guild icon to " + ChatColor.YELLOW + m.name());
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            new EditGuildGUI(p, g);
                        }
                    }.runTaskLater(Tazpvp.getInstance(), 1);

                } else {
                    p.sendMessage(ChatColor.RED + "You do not have enough credits!");
                    p.closeInventory();
                }
            });

            iconGui.addButton(index, iconBTN);

            if (index == 16 || index == 25 || index == 34 || index == 43) {
                index += 2;
            }
            index++;
        }
        iconGui.update();
        new BukkitRunnable() {
            @Override
            public void run() {
                iconGui.open(p);
            }
        }.runTaskLater(Tazpvp.getInstance(), 1);
    }

    public void editTag(Player p, Guild g) {
        p.closeInventory();
        new AnvilGUI.Builder()
                .onComplete((player, text) -> {
                    if (text.startsWith(">")) {
                        text = text.replaceFirst(">", "");
                    }
                    if (text.length() > 7) {
                        p.sendMessage(ChatColor.RED + "Tag must be 7 characters or less!");
                        return AnvilGUI.Response.close();
                    }

                    if (Tazpvp.statsManager.getCredits(p) < 50) {
                        p.sendMessage(ChatColor.RED + "You do not have enough credits!");
                        return AnvilGUI.Response.close();
                    }

                    if (Tazpvp.guildManager.getTakenTags().contains(text)) {
                        p.sendMessage(ChatColor.RED + "Tag is already taken!");
                        return AnvilGUI.Response.close();
                    }
                    if (g.tag() != null) {
                        Tazpvp.guildManager.removeTakenTag(g.tag());
                    }
                    Tazpvp.statsManager.addCredits(p, -50);
                    g.setTag(text);
                    Tazpvp.guildManager.addTakenTag(text);
                    Tazpvp.guildManager.setGuild(g.getID(), g);
                    p.sendMessage(ChatColor.GREEN + "Guild tag set to " + ChatColor.YELLOW + text);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            new EditGuildGUI(p, g);
                        }
                    }.runTaskLater(Tazpvp.getInstance(), 1);
                    return AnvilGUI.Response.close();
                })
                .onClose(player -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    new EditGuildGUI(p, g);
                })
                .text(">")
                .itemLeft(new ItemBuilder(Material.NAME_TAG).setName(ChatColor.GREEN + "Guild Modification"))
                .title(ChatColor.YELLOW + "Guild Tag < 8 char:")
                .plugin(Tazpvp.getInstance())
                .open(p);
    }

    public void editDesc(Player p, Guild g) {
        p.closeInventory();
        new AnvilGUI.Builder()
                .onComplete((player, text) -> {
                    if (text.startsWith(">")) {
                        text = text.replaceFirst(">", "");
                    }

                    g.setDescription(text);
                    Tazpvp.guildManager.setGuild(g.getID(), g);
                    p.sendMessage(ChatColor.GREEN + "Guild description updated to " + ChatColor.YELLOW + text);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            new EditGuildGUI(p, g);
                        }
                    }.runTaskLater(Tazpvp.getInstance(), 1);
                    return AnvilGUI.Response.close();
                })
                .onClose(player -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                    new EditGuildGUI(p, g);
                })
                .text(">")
                .itemLeft(new ItemBuilder(Material.NAME_TAG).setName(ChatColor.GREEN + "Guild Modification"))
                .title(ChatColor.YELLOW + "Guild Description:")
                .plugin(Tazpvp.getInstance())
                .open(p);
    }
}
