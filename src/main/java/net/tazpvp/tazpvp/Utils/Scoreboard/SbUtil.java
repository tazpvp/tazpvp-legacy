package net.tazpvp.tazpvp.Utils.Scoreboard;

import dev.jcsoftware.jscoreboards.JPerPlayerScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Arrays;

public class SbUtil {
//    private JPerPlayerScoreboard scoreboard = Tazpvp.scoreboard;
//
//    public void init(Player p) {
//        scoreboard = new JPerPlayerScoreboard(
//                (player) -> {
//                    return ChatColor.translateAlternateColorCodes('&', "&3&lTAZPVP.NET");
//                },
//                (player) -> {
//                    return Arrays.asList(
//                            ChatColor.DARK_AQUA + "                         ",
//                            ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "WEALTH",
//                            ChatColor.AQUA + "〡 Level  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getLevel(player),
//                            ChatColor.AQUA + "〡 Money  " + ChatColor.DARK_AQUA + "$" + Tazpvp.statsManager.getMoney(player),
//                            ChatColor.AQUA + "〡 Shards  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getShards(player),
//                            ChatColor.AQUA + "〡 EXP  " + ChatColor.DARK_AQUA + "" + Tazpvp.statsManager.getExp(player) + ChatColor.GRAY + "/" + new DecimalFormat("#.##").format(Tazpvp.statsManager.getExpLeft(player)),
//                            ChatColor.DARK_AQUA + "                         ",
//                            ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "STATS",
//                            ChatColor.AQUA + "〡 Streak  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getStreak(player),
//                            ChatColor.AQUA + "〡 Kills  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getKills(player),
//                            ChatColor.AQUA + "〡 Deaths  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getDeaths(player),
//                            ChatColor.AQUA + "〡 KDR  " + ChatColor.DARK_AQUA + ((Tazpvp.statsManager.getDeaths(player) > 0) ? MathUtils.round((float) Tazpvp.statsManager.getKills(player) / Tazpvp.statsManager.getDeaths(player), 2) : Tazpvp.statsManager.getKills(player)),
//                            ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "",
//                            ChatColor.GRAY + "〡 tazpvp.net"
//                    );
//                }
//        );
//        for (Player player1 : Bukkit.getOnlinePlayers()) {
//            Tazpvp.statsManager.getTeam(player1, scoreboard.toBukkitScoreboard(player1)).addEntry(player1.getName());
//        }
//        if (Tazpvp.permissions.getPrimaryGroup(p).equals("default")) {
//            p.setPlayerListName(ChatColor.GRAY + p.getDisplayName());
//        } else {
//            p.setPlayerListName(ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getGroupPrefix((String) null, Tazpvp.permissions.getPrimaryGroup(p)) + p.getDisplayName()));
//        }
//        JScoreboardTeam team = scoreboard.createTeam(p.getUniqueId().toString(), ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(p)));
//        team.addPlayer(p);
//        Tazpvp.statsManager.scoreboards.put(p.getUniqueId(), scoreboard.toBukkitScoreboard(p));
//    }
//
//
//    public void addScoreboard(Player player) {
//        Tazpvp.statsManager.scoreboards.get(player.getUniqueId()).addPlayer(player);
//        scoreboard.addPlayer(player);
//        scoreboard.updateScoreboard();
//    }
}
