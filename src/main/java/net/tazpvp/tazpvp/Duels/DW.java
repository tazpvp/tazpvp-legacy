package net.tazpvp.tazpvp.Duels;

import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils.setHealth;

public class DW {
    private Player p1;
    private Player p2;
    private List<UUID> spectators;
    private World duelMap;
    private Location spawn1;
    private Location spawn2;
    private KitManager kit;

    public DW(final Player p1, final Player p2, final World duelMap, final KitManager kit) {
        this.p1 = p1;
        this.p2 = p2;
        this.spectators = new ArrayList<>();
        this.duelMap = duelMap;
        this.spawn1 = new Location(duelMap, 0.5, 65, 0.5, 90, 0);
        this.spawn2 = new Location(duelMap, -48.5, 65, 0.5, -90, 0);
        this.kit = kit;
    }

    public Player getPlayer1() {
        return this.p1;
    }

    public Player getPlayer2() {
        return this.p2;
    }

    /**
     * Get a list of the dueling players
     * @return List<UUID> dueling players
     */
    public List<UUID> getPlayers() {
        return Arrays.asList(this.p1.getUniqueId(), this.p2.getUniqueId());
    }

    /**
     * Get Spectators
     * @return List<UUID> of spectators
     */
    public List<UUID> spectators() {
        return spectators;
    }

    /**
     * Add a player to the spectators list.
     * @param p the Player to add to the spectators list
     */
    public void addSpectator(Player p) {
        spectators.add(p.getUniqueId());
    }

    public World getDuelMap() {
        return this.duelMap;
    }

    public Location getSpawn1() {
        return this.spawn1;
    }

    public Location getSpawn2() {
        return this.spawn2;
    }

    public KitManager getKit() {
        return this.kit;
    }

    public void start() {
        this.p1.teleport(this.spawn1);
        this.p2.teleport(this.spawn2);
        this.kit.giveKit(this.p1);
        this.kit.giveKit(this.p2);
        setHealth(this.p1, 20, 20);
        setHealth(this.p2, 20, 20);
        this.p1.setFoodLevel(20);
        this.p2.setFoodLevel(20);
        this.p1.setSaturation(20);
        this.p2.setSaturation(20);
        this.p1.setFireTicks(0);
        this.p2.setFireTicks(0);
        this.p1.setGameMode(GameMode.SURVIVAL);
        this.p2.setGameMode(GameMode.SURVIVAL);
    }

    public void end() {
        this.p1.teleport(configUtils.spawn);
        this.p2.teleport(configUtils.spawn);
        setHealth(this.p1, 20, 20);
        setHealth(this.p2, 20, 20);
        this.p1.setFoodLevel(20);
        this.p2.setFoodLevel(20);
        this.p1.setSaturation(20);
        this.p2.setSaturation(20);
        this.p1.setFireTicks(0);
        this.p2.setFireTicks(0);
        this.p1.setGameMode(GameMode.SURVIVAL);
        this.p2.setGameMode(GameMode.SURVIVAL);
    }
}
