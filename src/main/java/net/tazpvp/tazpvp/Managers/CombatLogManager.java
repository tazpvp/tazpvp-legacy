package net.tazpvp.tazpvp.Managers;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class CombatLogManager {
    public static WeakHashMap<UUID, Long> combatLog = new WeakHashMap<>();

    public void putInCombat(Player p) {
        combatLog.put(p.getUniqueId(), 10L);
    }
    public void putInCombat(UUID p) {
        combatLog.put(p, 10L);
    }

    public static boolean isInCombat(Player p) {
        return combatLog.containsKey(p.getUniqueId());
    }
    public static boolean isInCombat(UUID p) {
        return combatLog.containsKey(p);
    }

    public static void tick() {
        List<UUID> queue = new ArrayList<>();

        if (!combatLog.isEmpty()) {
            for (UUID uuid : combatLog.keySet()) {
                if (combatLog.get(uuid) - 1 > 0) {
                    combatLog.replace(uuid, combatLog.get(uuid) - 1);
                } else {
                    queue.add(uuid);
                }
            }
            for (UUID uuid : queue) {
                combatLog.remove(uuid);
                Bukkit.getPlayer(uuid).sendMessage(ChatColor.RED + "You are no longer in combat.");
                Tazpvp.lastDamage.remove(uuid);
            }
        }
    }
}
