package net.tazpvp.tazpvp.Minigames.Events;

import net.tazpvp.tazpvp.Minigames.Event;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Listener;

public class KOTHEvent extends Event implements Listener {

    public KOTHEvent() {
        super(new Location(Bukkit.getWorld("KOTHEvent"), 0, 0, 0), "King Of The Hill");
        Bukkit.getPluginManager().registerEvents(this, Tazpvp.getInstance());

    }

    @Override
    public void initiate() {

    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }
}
