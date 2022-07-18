package net.tazpvp.tazpvp.Duels;

import net.tazpvp.tazpvp.Duels.WorldUtils.WorldManageent;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.IA.ArmorManager;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.*;

import static net.tazpvp.tazpvp.Utils.Functionality.AchieveUtils.Achieve;

public class DuelLogic implements Listener {

    public HashMap<List<UUID>, DW> duels = new HashMap<>();
    public Random random = new Random();

    /**
     * Create a new duel
     * @param p1 player one
     * @param p2 player two
     * @param type The duel type
     */
    public void duelStart(final Player p1, final Player p2, @Nullable DuelType type) {
        String duelName = "active_duel_" + p1.getName() + "_" + p2.getName();
        World w = new WorldManageent().cloneWorld(this.chooseRandomDuelMap("duel"), duelName);

        if (type == null) {
            type = DuelType.SWORD;
        }

        DW d = new DW(p1, p2, w, type.kit);

        universal(p1, p2, d);
    }

    private void universal(final Player p1, final Player p2, DW d) {
        this.duels.put(Arrays.asList(p1.getUniqueId(), p2.getUniqueId()), d);
        inventoryManagement(p1, p2, true);

        d.start();

        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "                                                             ");
        Bukkit.broadcastMessage(ChatColor.AQUA + p1.getName() + ChatColor.DARK_AQUA + " is now dueling " + ChatColor.AQUA + p2.getName());
        Bukkit.broadcastMessage(ChatColor.DARK_AQUA +"Type " + ChatColor.GRAY + "/spectate " + p1.getName());
        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "                                                             ");

        p1.sendTitle(ChatColor.AQUA + "Get Ready", ChatColor.DARK_AQUA + "The duel will start in 5 seconds.", 10, 60, 20);
        p2.sendTitle(ChatColor.AQUA + "Get Ready", ChatColor.DARK_AQUA + "The duel will start in 5 seconds.", 10, 60, 20);
        

        Tazpvp.particleUtil.save(p1);
        Tazpvp.particleUtil.save(p2);

        new BukkitRunnable() {
            @Override
            public void run() {
                p1.teleport(d.getSpawn1());
                p2.teleport(d.getSpawn2());
                p1.sendTitle(ChatColor.RED + "Begin!", ChatColor.BLUE + "", 10, 40, 20);
                p2.sendTitle(ChatColor.RED + "Begin!", ChatColor.BLUE + "", 10, 40, 20);
                p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
                p2.playSound(p2.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1, 1);
            }
        }.runTaskLater(Tazpvp.getInstance(), 20L * 5);
    }

    /**
     * Run the duel ending logic
     * @param p player that was killed
     */
    public void duelEnd(final Player p) {

        DW d = this.getDuel(p);

        if (d.isEnded()) return;
        d.setEnded(true);

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

        p.teleport(d.getSpawn1());

        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "                                                             ");
        Bukkit.broadcastMessage(ChatColor.AQUA + opponent.getName() + ChatColor.DARK_AQUA + " won the duel against " + ChatColor.AQUA + p.getName());
        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.STRIKETHROUGH + "                                                             ");

        if (!Tazpvp.achievementManager.statsFile.getBoolean(opponent.getUniqueId() + ".brave")) {
            Achieve(opponent, "Brave", "brave", 2, 32);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                d.end();

                List<UUID> spectators = d.spectators();
                for (UUID u : spectators) {
                    Player p = Bukkit.getPlayer(u);
                    if (p != null) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.teleport(configUtils.spawn);
                    }
                }

                if (p.isOnline()) {
                    p.getInventory().clear();
                    ArmorManager.setPlayerContents(p, false);
                    Tazpvp.returnItems.remove(p.getUniqueId());
                    Tazpvp.particleUtil.load(p);
                }
                if (opponent.isOnline()) {
                    opponent.getInventory().clear();
                    ArmorManager.setPlayerContents(opponent, false);
                    Tazpvp.returnItems.remove(opponent.getUniqueId());
                    Tazpvp.particleUtil.load(opponent);
                }
                new WorldManageent().deleteWorld(d.getDuelMap().getName());
                duels.remove(Arrays.asList(p.getUniqueId(), opponent.getUniqueId()));
                duels.remove(Arrays.asList(opponent.getUniqueId(), p.getUniqueId()));
            }
        }.runTaskLater(Tazpvp.getInstance(), 20 * 5L);
    }

    /**
     * makes player start spectating another player
     * @param p the player to set spectating
     * @param dueler the player to spectate
     */
    public void addSpectator(Player p, Player dueler) {
        if (isInDuel(dueler) && !isInDuel(p) && !CombatTag.isInCombat(p) && !isSpectating(p)) {
            DW d = this.getDuel(dueler);
            d.addSpectator(p);
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(d.getSpawn1());
            p.sendMessage(ChatColor.GREEN + "You are now spectating " + ChatColor.GOLD + dueler.getName());
            p.sendMessage(ChatColor.GREEN + "You can stop spectating by typing /spawn");
        } else {
            p.sendMessage(ChatColor.RED + "You can't spectate that player");
        }
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

    /**
     * Check if a player is spectating
     * @param p the player to check if they are spectating
     * @return true if the player is spectating a duel
     */
    public boolean isSpectating(final Player p) {
        for (DW dw : duels.values()) {
            if (dw.spectators().contains(p.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if an uuid is spectating
     * @param uuid the uuid to check if they are spectating
     * @return true if the uuid is spectating a duel
     */
    public boolean isSpectating(final UUID uuid) {
        for (DW dw : duels.values()) {
            if (dw.spectators().contains(uuid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a random duel map
     * @return the name of the map
     */
    public String chooseRandomDuelMap(String worldPrefix) {
        List<String> maps = new ArrayList<>();
        for (World w : Bukkit.getWorlds()) {
            if (w.getName().startsWith(worldPrefix)) {
                maps.add(w.getName());
            }
        }
        return maps.get(this.random.nextInt(maps.size()));
    }
}
