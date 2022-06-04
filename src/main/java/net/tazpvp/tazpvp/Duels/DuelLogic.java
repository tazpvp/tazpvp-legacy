package net.tazpvp.tazpvp.Duels;

import net.tazpvp.tazpvp.Duels.WorldUtils.WorldManageent;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.IA.ArmorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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

        p1.sendTitle(null, ChatColor.GREEN + "Duel starting in 3 Seconds", 10, 20, 10);
        p2.sendTitle(null, ChatColor.GREEN + "Duel starting in 3 Seconds", 10, 20, 10);


        new BukkitRunnable() {
            @Override
            public void run() {
                p1.teleport(d.getSpawn1());
                p2.teleport(d.getSpawn2());
                p1.sendTitle(null, ChatColor.GREEN + "GO!", 5, 10, 5);
                p2.sendTitle(null, ChatColor.GREEN + "GO!", 5, 10, 5);
            }
        }.runTaskLater(Tazpvp.getInstance(), 20L * 3);
    }

    public void duelEnd(final Player p) {
        DW d = this.getDuel(p);
        Player opponent;
        if (p.equals(d.getPlayer1())) {
            opponent = d.getPlayer2();
        } else {
            opponent = d.getPlayer1();
        }
        p.spigot().respawn();
        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(opponent.getLocation());

        Bukkit.broadcastMessage(ChatColor.GOLD + opponent.getName() + ChatColor.YELLOW + " has won the duel against " + ChatColor.GOLD + p.getName());

        new BukkitRunnable() {
            @Override
            public void run() {
                d.end();

                p.getInventory().clear();
                opponent.getInventory().clear();

                inventoryManagement(p, opponent, false);
                new WorldManageent().deleteWorld(d.getDuelMap().getName());
                duels.remove(Arrays.asList(p.getUniqueId(), opponent.getUniqueId()));
            }
        }.runTaskLater(Tazpvp.getInstance(), 20 * 5L);
    }

    public void inventoryManagement(final Player p1, final Player p2, final Boolean save) {
        if (save) {
            ArmorManager.storeAndClearInventory(p1);
            ArmorManager.storeAndClearInventory(p2);
        } else {
            ArmorManager.setPlayerContents(p1, true);
            ArmorManager.setPlayerContents(p2, true);
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
