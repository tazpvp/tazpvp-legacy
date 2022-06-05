package net.tazpvp.tazpvp.Mobs.Listeners;

import java.util.ArrayList;
import java.util.List;

public class Initiater {
    private List<TickListener> listeners = new ArrayList<TickListener>();

    public void addListener(TickListener toAdd) {
        listeners.add(toAdd);
    }

    public void onTick() {
        System.out.println("Hello!!");

        // Notify everybody that may be interested.
        for (TickListener hl : listeners)
            hl.onTick();
    }
}
