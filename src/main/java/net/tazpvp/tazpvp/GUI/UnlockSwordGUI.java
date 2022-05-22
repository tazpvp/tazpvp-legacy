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

public class UnlockSwordGUI {
    private InventoryGUI gui;

    public UnlockSwordGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 27, "Spins: " + Tazpvp.statsManager.getSpins(p)));
        addItems(p);
        gui.open(p);
    }

    public void addItems(Player p) {
        gui.fill(0, 28, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));

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
            if (Tazpvp.statsManager.getSpins(p) > 0) {
                Tazpvp.statsManager.setSpins(p, Tazpvp.statsManager.getSpins(p) - 1);
                spinSword(sword, p);
            } else {
                e.getWhoClicked().sendMessage("You don't have any spins!");
                e.getWhoClicked().closeInventory();
            }
        });
        gui.addButton(12, spinSword);

        gui.update();
    }

    public void spinSword(ItemButton sword, Player p) {
        int maxRuns = 10;
        final int[] runs = {0};
        new BukkitRunnable() {
            @Override
            public void run() {
                runs[0]++;
                if (runs[0] >= maxRuns) {
                    cancel();
                } else {
                    Items item = GetRandomSword.getRandomSword();
                    sword.setItem(new ItemBuilder(item.getMaterial()).setName(item.getName()).setLore(item.getLore()));
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                    gui.update();
                }
            }
        }.runTaskTimer(Tazpvp.getInstance(), 5, 5);
        Items unlockedItem = GetRandomSword.getRandomSword();
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
        p.getWorld().playEffect(p.getLocation(), Effect.FIREWORK_SHOOT, 1, 1);
        p.getWorld().playEffect(p.getLocation(), Effect.FIREWORK_SHOOT, 1, 1);
        p.getWorld().playEffect(p.getLocation(), Effect.FIREWORK_SHOOT, 1, 1);
        sword.setItem(new ItemBuilder(unlockedItem.getMaterial()).setName(unlockedItem.getName()).setLore(unlockedItem.getLore()));
        gui.update();
        Tazpvp.playerWrapperMap.get(p).swords().add(unlockedItem);
    }
}