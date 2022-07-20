package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Duels.DW;
import net.tazpvp.tazpvp.Guilds.Guild;
import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.DeathUtils;
import net.tazpvp.tazpvp.Utils.Functionality.IA.ArmorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static net.tazpvp.tazpvp.Tazpvp.vanished;

public class LeaveEvnet implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (vanished.contains(p)) {
            e.setQuitMessage("");
        } else {
            e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + e.getPlayer().getName());
        }
        Tazpvp.playerWrapperStatsManager.setPlayerWrapper(e.getPlayer(), Tazpvp.playerWrapperMap.get(e.getPlayer().getUniqueId()));
        Tazpvp.playerWrapperMap.remove(e.getPlayer().getUniqueId());

        if (Tazpvp.duelLogic.isInDuel(e.getPlayer())) {
            ArmorManager.setPlayerContents(e.getPlayer(), false);
            Tazpvp.duelLogic.duelEnd(e.getPlayer());
        }



        for (DW dw : Tazpvp.duelLogic.duels.values()) {
            dw.spectators().remove(p.getUniqueId());
        }

        if (CombatTag.isInCombat(e.getPlayer())) {
            UUID k = Tazpvp.lastDamage.get(p.getUniqueId());
            Player h = Bukkit.getPlayer(k);
            DeathUtils deathUtils = new DeathUtils(p, h);

            CombatTag.combatLog.remove(p.getUniqueId());
            if (Tazpvp.lastDamage.containsKey(p.getUniqueId())) {
                if (Bukkit.getOfflinePlayer(k).isOnline()) {
                    if (Bukkit.getOnlinePlayers().size() < 8) {
                        deathUtils.sendDeathMessageAll(false);
                    } else {
                        deathUtils.sendDeathMessage(h);
                    }
                }
                Tazpvp.statsManager.addKills(Bukkit.getOfflinePlayer(k), 1);
                Tazpvp.statsManager.addExp(Bukkit.getOfflinePlayer(k), 15);
                if (Tazpvp.statsManager.checkLevelUp(Bukkit.getOfflinePlayer(k))) {
                    Tazpvp.statsManager.levelUp(Bukkit.getOfflinePlayer(k), 1);
                }
                Tazpvp.statsManager.addCoins(Bukkit.getOfflinePlayer(k), 7);
            }
            Tazpvp.dueling.remove(e.getPlayer().getUniqueId());
        }

        for (Player plr : Bukkit.getOnlinePlayers()) {
            Tazpvp.sendBaseTablist(plr);
        }

        if(GuildUtils.isInGuild(e.getPlayer())) {
            Guild g = GuildUtils.getGuild(e.getPlayer());
            if (g.isOwner(e.getPlayer().getUniqueId())) {
                g.invites().clear();
                Tazpvp.guildManager.setGuild(g.getID(), g);
            }
        }

        vanished.remove(p);

        Tazpvp.particleUtil.inUse.remove(e.getPlayer());
    }
}
