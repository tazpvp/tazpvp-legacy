package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Items;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class temp {
    public static HashMap<UUID, ArrayList<Items>> hashas = new HashMap<>();

    public static void run(Player p) {
        UUID uuid = p.getUniqueId();
        ArrayList<Items> items = hashas.get(uuid);
        for (Items item : items) {
            p.sendMessage(item.getName());
            p.sendMessage(item.getItem().toString());
        }
    }

    public static void unlock(Player p, Items item) {
        ArrayList<Items> items = hashas.get(p.getUniqueId());
        items.add(item);
        hashas.put(p.getUniqueId(), items);
    }
}
