package net.tazpvp.tazpvp.GUI;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.List;
import java.util.Random;

import static net.tazpvp.tazpvp.Utils.Custom.Sword.GetRandomSword.getRandomSword;
import static net.tazpvp.tazpvp.Utils.Functionality.AchieveUtils.Achieve;

public class UnlockSwordGUI {
    private InventoryGUI gui;
    private boolean isSpinning = false;

    public UnlockSwordGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, ChatColor.BLUE + "" + ChatColor.BOLD + "WHEEL o BLADES"));
        addItems(p);
        gui.open(p);
    }

    public void addItems(Player p) {
        gui.fill(0, 27, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" "));

        ItemButton buySpins = ItemButton.create(new ItemBuilder(Material.CHEST_MINECART)
                .setName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Buy Spins")
                .setCount(1)
                .setLore(ChatColor.DARK_PURPLE + "Cost:", ChatColor.DARK_AQUA + "7 Shards", ChatColor.GOLD + "125 Coins"), e -> {
            if (Tazpvp.statsManager.getCoins(p) >= 125  && Tazpvp.statsManager.getShards(p) >= 7) {
                Tazpvp.statsManager.addCoins(p, -125);
                Tazpvp.statsManager.addShards(p, -7);
                Tazpvp.statsManager.addSpins(p, 1);
                p.sendMessage(ChatColor.DARK_AQUA + "You purchased a spin.");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            } else {
                p.sendMessage(ChatColor.RED + "You do not have enough money.");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
            }
        });
        gui.addButton(11, buySpins);

        ItemButton sword = ItemButton.create(new ItemBuilder(Material.TURTLE_EGG)
                .setName(ChatColor.DARK_GRAY + "Surprise? :o"), e -> {
        });
        gui.addButton(15, sword);

        ItemButton spinSword = ItemButton.create(new ItemBuilder(Material.SUNFLOWER)
                .setName(ChatColor.RED + "" + ChatColor.BOLD + "Spin Me!")
                .setLore(ChatColor.GRAY + "Roll the wheel.", ChatColor.DARK_AQUA + "Spins Left: " + ChatColor.AQUA + Tazpvp.statsManager.getSpins(p)), e -> {
            if (isSpinning) {
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            } else {
                if (Tazpvp.statsManager.getSpins(p) > 0) {
                    Tazpvp.statsManager.setSpins(p, Tazpvp.statsManager.getSpins(p) - 1);
                    p.sendMessage(ChatColor.DARK_AQUA + "You spun the wheel. What will you get?");
                    spinSword(sword, p);
                    isSpinning = true;
                } else {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "You don't have any spins!");
                    e.getWhoClicked().closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_GUITAR, 1, 1);
                }
            }
        });
        gui.addButton(12, spinSword);

        gui.update();
    }

    public void spinSword(ItemButton sword, Player p) {
        int maxRuns = 20;
        final int[] runs = {0};
        new BukkitRunnable() {
            @Override
            public void run() {
                runs[0]++;
                if (runs[0] >= maxRuns) {
                    Items unlockedItem = getRandomSword(null);
                    p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                    sword.setItem(new ItemBuilder(unlockedItem.getMaterial()).setName(unlockedItem.getName()).setLore(unlockedItem.getLore()));
                    gui.update();
                    p.sendTitle(ChatColor.AQUA + "" + ChatColor.BOLD + "UNLOCKED", unlockedItem.getName(), 10, 100, 10);
                    p.sendMessage("");
                    p.sendMessage(ChatColor.DARK_AQUA + " You unlocked: " + ChatColor.BOLD + unlockedItem.getName());
                    p.sendMessage("");
                    List<Items> swords = Tazpvp.playerWrapperMap.get(p.getUniqueId()).getSwords();
                    swords.add(unlockedItem);
                    Tazpvp.playerWrapperMap.get(p.getUniqueId()).setSwords(swords);
                    net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder.giveItem(p, unlockedItem, 1);
                    List<Items> swordUnlocked = Tazpvp.playerWrapperMap.get(p.getUniqueId()).getSwords();
                    if (swordUnlocked.size() >= Items.values().length) {
                        Achieve(p, "Hoarder", "hoarder", 5, 220);
                    }
                    p.closeInventory();
                    cancel();
                } else {
                    Items item = Items.values()[new Random().nextInt(Items.values().length)];
                    sword.setItem(new ItemBuilder(item.getMaterial()).setName(item.getName()).setLore(item.getLore()));
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 3, 1);
                    gui.update();
                }
            }
        }.runTaskTimer(Tazpvp.getInstance(), 3, 3);

    }
}
