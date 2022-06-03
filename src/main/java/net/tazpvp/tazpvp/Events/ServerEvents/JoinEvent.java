package net.tazpvp.tazpvp.Events.ServerEvents;

import net.tazpvp.tazpvp.Managers.PlayerWrapperManagers.PlayerWrapper;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.NameTag;
import net.tazpvp.tazpvp.Utils.PlayerUtils;
import net.tazpvp.tazpvp.Utils.configUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location spawn = configUtils.spawn;

        p.sendMessage("");
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "  ┃  " + ChatColor.RED + "" + ChatColor.BOLD + "TAZPVP");
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "  ┃  " + ChatColor.GRAY + "Type /discord");
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "  ┃  " + ChatColor.GRAY + "IP: tazpvp.net");
        p.sendMessage("");

        if (p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + p.getName());
        } else {
            Tazpvp.statsManager.initPlayer(p);
            Tazpvp.achievementManager.initPlayer(p);
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + p.getName());
            p.sendTitle(ChatColor.GOLD + "Welcome to " + ChatColor.YELLOW + "TazPvP", ChatColor.GOLD + "Type /help to get started", 10, 100, 10);
            PlayerUtils.kitPlayer(p);
            Tazpvp.playerWrapperStatsManager.setPlayerWrapper(p, new PlayerWrapper(p));
            Tazpvp.playerWrapperStatsManager.setPlayerWrapper(p, new PlayerWrapper(p));
        }

        if (Tazpvp.playerWrapperStatsManager.getPlayerWrapper(p) == null) {
            Tazpvp.playerWrapperStatsManager.setPlayerWrapper(p, new PlayerWrapper(p));
        }
        Tazpvp.playerWrapperMap.put(p.getUniqueId(), Tazpvp.playerWrapperStatsManager.getPlayerWrapper(p));

        if (Tazpvp.punishmentManager.isBanned(p)) {
            p.teleport(new Location(Bukkit.getWorld("ban"), 0, 77, 0));
        } else {
            p.teleport(spawn);
        }


        for(Scoreboard sb : Tazpvp.statsManager.scoreboards.values()) {
            Tazpvp.statsManager.getTeam(p, sb).addEntry(p.getName());
        }
        Tazpvp.statsManager.initScoreboard(p);

        for (Player plr : Bukkit.getOnlinePlayers()) {
            Tazpvp.sendBaseTablist(plr);
        }

        Tazpvp.statsManager.scoreboards.get(p.getUniqueId()).getTeam(p.getUniqueId().toString()).setDisplayName(p.getDisplayName());

        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            setNametag(onlinePlayers, p);
        }
    }

    public void setNametag(Player player1, Player player2) {
        Scoreboard scoreboard = player1.getScoreboard();
        if (scoreboard.getTeam(player1.getUniqueId().toString()) != null) {
            scoreboard.getTeam(player1.getUniqueId().toString()).unregister();
        }
        Team team = scoreboard.registerNewTeam(player1.getUniqueId().toString());
        team.setPrefix(ChatColor.translateAlternateColorCodes('&', "eee"));
        scoreboard.getTeam(player1.getUniqueId().toString()).addPlayer(player2);
    }
}
