package net.tazpvp.tazpvp.Guilds;

import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.util.HashMap;
import java.util.Map;

public class GuildCMD {
    @CommandHook("guild_sorttest")
    public void sorttest(Player p) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Fred", 3);
        map.put("Your", 4);
        map.put("Mother", 2);
        Map<String, Integer> sorted = GuildUtils.sortByValue(map);
        for (String s : sorted.keySet()) {
            p.sendMessage(s + ": " + sorted.get(s));
        }
    }

}
