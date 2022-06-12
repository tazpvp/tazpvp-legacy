package net.tazpvp.tazpvp.Guilds;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuildCMD {
    @CommandHook("sorttest")
    public void sortest(Player p) {
        HashMap<UUID, Double> map = new HashMap<>();
        for (Guild g : Tazpvp.guildManager.getAllGuilds()) {
            map.put(g.getID(), g.getKills());
        }
        Map<UUID, Double> sorted = GuildUtils.sortByValue(map);
        for (Map.Entry<UUID, Double> entry : sorted.entrySet()) {
            p.sendMessage(Tazpvp.guildManager.getGuild(entry.getKey()).name() + " has " + entry.getValue() + " kills");
        }
    }
}
