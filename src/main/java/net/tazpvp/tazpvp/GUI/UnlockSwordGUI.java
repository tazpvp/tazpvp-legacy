package net.tazpvp.tazpvp.GUI;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import net.tazpvp.tazpvp.Utils.GetRandomSword;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.List;
import java.util.Random;

public class UnlockSwordGUI {
    private InventoryGUI gui;
    private boolean isSpinning = false;

    public UnlockSwordGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "Spins: " + Tazpvp.statsManager.getSpins(p)));
        addItems(p);
        gui.open(p);
    }

    public void addItems(Player p) {
        gui.fill(0, 27, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        ItemButton buySpins = ItemButton.create(new ItemBuilder(Material.PAPER)
                .setName("Buy spins")
                .setCount(1)
                .setLore("cost would go here but lazy :)"), e -> {
            e.getWhoClicked().closeInventory();
        });
        gui.addButton(10, buySpins);

        ItemButton sword = ItemButton.create(new ItemBuilder(Material.IRON_SWORD)
                .setName("Spin to unlock a random sword!")
                .setLore("Spin me!"), e -> {
        });
        gui.addButton(15, sword);

        ItemButton spinSword = ItemButton.create(new ItemBuilder(Material.SUNFLOWER)
                .setName("Spin for Sword")
                .setLore("Spins Available: " + Tazpvp.statsManager.getSpins(p)), e -> {
            if (isSpinning) {
                p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
            } else {
                if (Tazpvp.statsManager.getSpins(p) > 0) {
                    Tazpvp.statsManager.setSpins(p, Tazpvp.statsManager.getSpins(p) - 1);
                    spinSword(sword, p);
                    isSpinning = true;
                } else {
                    e.getWhoClicked().sendMessage("You don't have any spins!");
                    e.getWhoClicked().closeInventory();
                }
            }
        });
        gui.addButton(12, spinSword);

        gui.update();
    }

    public void spinSword(ItemButton sword, Player p) {
        int maxRuns = 50;
        final int[] runs = {0};
        new BukkitRunnable() {
            @Override
            public void run() {
                runs[0]++;
                if (runs[0] >= maxRuns) {
                    Items unlockedItem = GetRandomSword.getRandomSword();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 5, 5);
                    sword.setItem(new ItemBuilder(unlockedItem.getMaterial()).setName(unlockedItem.getName()).setLore(unlockedItem.getLore()));
                    gui.update();
                    if (Tazpvp.playerWrapperMap.get(p.getUniqueId()).getSwords().contains(unlockedItem)) {
                        p.sendMessage("You have already unlocked " + unlockedItem.getName() + "!");
                    } else {
                        List<Items> swords = Tazpvp.playerWrapperMap.get(p.getUniqueId()).getSwords();
                        swords.add(unlockedItem);
                        Tazpvp.playerWrapperMap.get(p.getUniqueId()).setSwords(swords);
                        p.sendMessage("You have unlocked a " + unlockedItem.getName() + "!");
                        net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder.giveItem(p, unlockedItem, 1);
                    }
                    p.closeInventory();
                    cancel();
                } else {
                    Items item = Items.values()[new Random().nextInt(Items.values().length)];
                    sword.setItem(new ItemBuilder(item.getMaterial()).setName(item.getName()).setLore(item.getLore()));
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                    gui.update();
                }
            }
        }.runTaskTimer(Tazpvp.getInstance(), 3, 3);

    }
}
