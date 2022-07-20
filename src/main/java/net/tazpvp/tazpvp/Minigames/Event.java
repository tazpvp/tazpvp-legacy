package net.tazpvp.tazpvp.Minigames;

import net.tazpvp.tazpvp.Utils.Functionality.IA.ArmorManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Event {

    protected List<UUID> playercount;
    protected Location spawn;
    protected String name;

    public Event(Location spawn, String name) {
        this.spawn = spawn;
        this.name = name;
        this.playercount = new ArrayList<>();
    }

    public abstract void initiate();
    public abstract void start();
    public abstract void end();

    public void addPlayer(Player p) {
        this.playercount.add(p.getUniqueId());
    }

    public void removePlayer(Player p) {
        this.playercount.remove(p.getUniqueId());
    }

    public void saveInv(Player p) {
        ArmorManager.storeAndClearInventory(p);
    }

    public void loadInv(Player p) {
        ArmorManager.setPlayerContents(p, true);
    }
}
