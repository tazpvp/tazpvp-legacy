package net.tazpvp.tazpvp.Events;

import net.tazpvp.tazpvp.Guilds.Guild;
import net.tazpvp.tazpvp.Guilds.GuildManager;
import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.DeathUtils;
import net.tazpvp.tazpvp.Utils.Functionality.IA.ArmorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class LeaveEvnet implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + e.getPlayer().getName());
        Tazpvp.playerWrapperStatsManager.setPlayerWrapper(e.getPlayer(), Tazpvp.playerWrapperMap.get(e.getPlayer().getUniqueId()));
        Tazpvp.playerWrapperMap.remove(e.getPlayer().getUniqueId());

        if (Tazpvp.duelLogic.isInDuel(e.getPlayer())) {
            ArmorManager.setPlayerContents(e.getPlayer(), false);
            Tazpvp.duelLogic.duelEnd(e.getPlayer());
        }

        if (CombatTag.isInCombat(e.getPlayer())) {
            Player p = e.getPlayer();
            UUID k = Tazpvp.lastDamage.get(p.getUniqueId());
            Player h = Bukkit.getOfflinePlayer(k).getPlayer();
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
                Tazpvp.statsManager.addCoins(Bukkit.getOfflinePlayer(k), 7);
            }
        }

        for (UUID uuid : Tazpvp.lastDamage.keySet()) {
            if (Tazpvp.lastDamage.get(uuid).equals(e.getPlayer().getUniqueId())) {
                Tazpvp.lastDamage.remove(uuid);
            }
        }
        Tazpvp.lastDamage.remove(e.getPlayer().getUniqueId());

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
    }
}
