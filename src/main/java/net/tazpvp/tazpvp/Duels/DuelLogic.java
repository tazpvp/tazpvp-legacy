package net.tazpvp.tazpvp.Duels;

import net.tazpvp.tazpvp.Duels.WorldUtils.WorldManageent;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.IA.ArmorManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.*;

public class DuelLogic implements Listener {

    public HashMap<List<UUID>, DW> duels = new HashMap<>();
    public Random random = new Random();


    public void duelStart(final Player p1, final Player p2, @Nullable KitManager kit) {
        String duelName = "active_duel_" + p1.getName() + "_" + p2.getName();
        World w = new WorldManageent().cloneWorld(this.chooseRandomDuelMap(), duelName);

        if (kit == null) {
            kit = KitManager.SWORD;
        }

        DW d = new DW(p1, p2, w, kit);

        this.duels.put(Arrays.asList(p1.getUniqueId(), p2.getUniqueId()), d);
        inventoryManagement(p1, p2, true);

        d.start();

        p1.sendMessage(ChatColor.GREEN + "Duel starting in 5 Seconds");
        p2.sendMessage(ChatColor.GREEN + "Duel starting in 5 Seconds");


        new BukkitRunnable() {
            @Override
            public void run() {
                p1.teleport(d.getSpawn1());
                p2.teleport(d.getSpawn2());
                p1.sendMessage(ChatColor.GREEN + "GO!");
                p2.sendMessage(ChatColor.GREEN + "GO!");
                p1.playSound(p1.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
                p2.playSound(p2.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
            }
        }.runTaskLater(Tazpvp.getInstance(), 20L * 5);
    }

    public void duelEnd(final Player p) {

        DW d = this.getDuel(p);
        Player opponent;
        if (p.equals(d.getPlayer1())) {
            opponent = d.getPlayer2();
        } else {
            opponent = d.getPlayer1();
        }
        opponent.setMetadata("sentDuel", new FixedMetadataValue(Tazpvp.getInstance(), ""));
        p.setMetadata("sentDuel", new FixedMetadataValue(Tazpvp.getInstance(), ""));
        Tazpvp.returnItems.add(p.getUniqueId());
        Tazpvp.returnItems.add(opponent.getUniqueId());

        p.getWorld().playEffect(p.getLocation().add(0, 1, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                p.getWorld().dropItemNaturally(p.getLocation(), i);
            }
        }

        p.setGameMode(GameMode.SPECTATOR);

        Bukkit.broadcastMessage(ChatColor.GOLD + opponent.getName() + ChatColor.YELLOW + " has won the duel against " + ChatColor.GOLD + p.getName());

        new BukkitRunnable() {
            @Override
            public void run() {
                d.end();

                if (p.isOnline()) {
                    p.getInventory().clear();
                    ArmorManager.setPlayerContents(p, false);
                    Tazpvp.returnItems.remove(p.getUniqueId());
                }
                if (opponent.isOnline()) {
                    opponent.getInventory().clear();
                    ArmorManager.setPlayerContents(opponent, false);
                    Tazpvp.returnItems.remove(opponent.getUniqueId());
                }
                new WorldManageent().deleteWorld(d.getDuelMap().getName());
                duels.remove(Arrays.asList(p.getUniqueId(), opponent.getUniqueId()));
                duels.remove(Arrays.asList(opponent.getUniqueId(), p.getUniqueId()));
            }
        }.runTaskLater(Tazpvp.getInstance(), 20 * 5L);
    }

    public void inventoryManagement(final Player p1, final Player p2, final Boolean save) {
        if (save) {
            ArmorManager.storeAndClearInventory(p1);
            ArmorManager.storeAndClearInventory(p2);
        } else {
            ArmorManager.setPlayerContents(p1, false);
            ArmorManager.setPlayerContents(p2, false);
        }
    }

    public DW getDuel(final Player p) {
        for (List<UUID> l : this.duels.keySet()) {
            if (l.contains(p.getUniqueId())) {
                return this.duels.get(l);
            }
        }
        return null;
    }

    public boolean isInDuel(final Player p) {
        for (List<UUID> l : this.duels.keySet()) {
            if (l.contains(p.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    public String chooseRandomDuelMap() {
        List<String> maps = new ArrayList<>();
        for (World w : Bukkit.getWorlds()) {
            if (w.getName().startsWith("duel")) {
                maps.add(w.getName());
            }
        }
        return maps.get(this.random.nextInt(maps.size()));
    }
}
