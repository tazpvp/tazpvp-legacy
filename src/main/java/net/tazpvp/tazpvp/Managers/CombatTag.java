package net.tazpvp.tazpvp.Managers;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

public class CombatTag {
    public static WeakHashMap<UUID, Long> combatLog = new WeakHashMap<>();

    public static void putInCombat(Player p, Player k) {
        p.sendMessage(ChatColor.RED + "You are now in combat with" + ChatColor.YELLOW + " " + k);
        Tazpvp.particleUtil.save(p);
        combatLog.put(p.getUniqueId(), 10L * 20L);
    }
    public static void putInCombat(UUID p) {
        combatLog.put(p, 10L * 20);
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
                Bukkit.getPlayer(uuid).sendMessage(ChatColor.DARK_AQUA + "You are no longer in combat.");
                Tazpvp.lastDamage.remove(uuid);
                Tazpvp.particleUtil.load(Bukkit.getPlayer(uuid));
            }
        }
    }
}
