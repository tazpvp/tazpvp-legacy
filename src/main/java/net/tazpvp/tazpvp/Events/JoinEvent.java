package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Managers.PlayerWrapperManagers.PlayerWrapper;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.UpdateSword;
import net.tazpvp.tazpvp.Utils.Functionality.ChatEnum;
import net.tazpvp.tazpvp.Utils.Functionality.IA.ArmorManager;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static net.tazpvp.tazpvp.Tazpvp.vanished;
import static net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils.checkArmor;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location spawn = configUtils.spawn;

        if (Tazpvp.tempBan.containsKey(p.getUniqueId())) {
            long elapsedTime = System.currentTimeMillis() - Tazpvp.tempBan.get(p.getUniqueId());
            if (elapsedTime < 5 * 60 * 1000) {
                p.kickPlayer(ChatColor.RED + "You are temporarily kicked from the server. Join back in 5 minutes.");
                return;
            } else {
                Tazpvp.tempBan.remove(p.getUniqueId());
            }
        }

        p.sendMessage("");
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "  ┃  " + ChatColor.RED + "" + ChatColor.BOLD + "TAZPVP");
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "  ┃  " + ChatColor.GRAY + "Type /discord");
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.BOLD + "  ┃  " + ChatColor.GRAY + "IP: tazpvp.net");
        p.sendMessage("");

        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);

        if (p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + p.getName());
            checkArmor(p);
        } else {
            Tazpvp.statsManager.initPlayer(p);
            e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "+" + ChatColor.GRAY + "] " + p.getName());
            p.sendTitle(ChatColor.AQUA + "" + ChatColor.BOLD + "TAZPVP", ChatColor.DARK_AQUA + "Type " + ChatColor.AQUA + "/help " + ChatColor.DARK_AQUA + "to get started", 10, 100, 10);
            PlayerUtils.kitPlayer(p);
            Tazpvp.playerWrapperStatsManager.setPlayerWrapper(p, new PlayerWrapper(p));
            Tazpvp.playerWrapperStatsManager.setPlayerWrapper(p, new PlayerWrapper(p));
        }

        p.setInvulnerable(false);

        if (CombatTag.isInCombat(p)) {
            CombatTag.combatLog.remove(p.getUniqueId());
        }

        for(Player vanished : vanished){
            if (!p.hasPermission("tazpvp.vanish")) p.hidePlayer(Tazpvp.getInstance(), vanished);
        }

        if (Tazpvp.playerWrapperStatsManager.getPlayerWrapper(p) == null) {
            Tazpvp.playerWrapperStatsManager.setPlayerWrapper(p, new PlayerWrapper(p));
        }
        Tazpvp.playerWrapperMap.put(p.getUniqueId(), Tazpvp.playerWrapperStatsManager.getPlayerWrapper(p));

        if (Tazpvp.punishmentManager.isBanned(p)) {
            p.teleport(new Location(Bukkit.getWorld("ban"), 0.5, 70, 0.5));
            p.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "BANNED", ChatColor.RED + "You are still currently banned. Try /appeal", 10, 100, 10);
        } else {
            p.teleport(spawn);
        }

        if (Tazpvp.returnItems.contains(p.getUniqueId())) {
            p.getInventory().clear();
            ArmorManager.setPlayerContents(p, true);
            Tazpvp.returnItems.remove(p.getUniqueId());
        }

        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                UpdateSword.updateSword(p, i);
            }
        }

        Tazpvp.chatEnum.put(p.getUniqueId(), ChatEnum.ALL);

        for(Scoreboard sb : Tazpvp.statsManager.scoreboards.values()) {
            Tazpvp.statsManager.getTeam(p, sb).addEntry(p.getName());
        }
        Tazpvp.statsManager.initScoreboard(p);

        Bukkit.getLogger().info(Tazpvp.permissions.getPrimaryGroup(p).toLowerCase());

        for (Player plr : Bukkit.getOnlinePlayers()) {
            Tazpvp.sendBaseTablist(plr);
        }

        Tazpvp.statsManager.scoreboards.get(p.getUniqueId()).getTeam(p.getUniqueId().toString()).setDisplayName(p.getDisplayName());

        Tazpvp.getInstance().addPlayerToOnlinePlayersSB(p);
        Tazpvp.getInstance().addOnlinePlayersToSB(p);

        Tazpvp.chatEnum.put(p.getUniqueId(), ChatEnum.ALL);
    }

    public void setNametag(Player player1, Player player2) {
        Scoreboard scoreboard = player2.getScoreboard();
        if (scoreboard.getTeam(player1.getUniqueId().toString()) != null) {
            scoreboard.getTeam(player1.getUniqueId().toString()).unregister();
        }
        Team team = scoreboard.registerNewTeam(player1.getUniqueId().toString());
        team.setPrefix(ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(player2)));
        scoreboard.getTeam(player1.getUniqueId().toString()).addPlayer(player2);
    }

}
