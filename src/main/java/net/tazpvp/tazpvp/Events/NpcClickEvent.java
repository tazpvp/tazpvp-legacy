package net.tazpvp.tazpvp.Events;


import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcClickEvent implements Listener {
    @EventHandler
    public void onNpcLClick(NPCLeftClickEvent e) {
        if (e.getNPC().getId() == 1) {
            //do stuff
        }
    }
    @EventHandler
    public void onNpcRClick(NPCRightClickEvent e) {
        if (e.getNPC().getId() == 1) {
            //do stuff
        }
    }
}
