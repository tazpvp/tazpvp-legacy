package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Duels.DW;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.commandmanager.CommandHook;

public class SpawnCMD implements CommandListener {
    @CommandHook("spawn")
    public void spawn(Player p, Player target){
        if (!target.equals(p)) {
            if (!p.hasPermission("tazpvp.spawn")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            } else {
                spawnPlayer(target);
            }
        } else {
            spawnPlayer(p);
        }
    }

    public void spawnPlayer(Player p){
        if (Tazpvp.punishmentManager.isBanned(p)) return;
        if (Tazpvp.duelLogic.isInDuel(p)) return;
        if (CombatTag.isInCombat(p)) { p.sendMessage(ChatColor.RED + "You cannot go to spawn while in combat."); return; }
        if (p.getGameMode() == GameMode.SPECTATOR) {
            for (DW dw : Tazpvp.duelLogic.duels.values()) {
                if (dw.spectators().contains(p.getUniqueId())) {
                    dw.spectators().remove(p.getUniqueId());
                    p.sendMessage(ChatColor.RED + "You have stopped spectating the duel.");
                    p.teleport(configUtils.spawn);
                    return;
                }
            }
        }
        if (p.hasPermission("tazpvp.spawn")){
            p.teleport(configUtils.spawn);
        } else {
            p.sendMessage(ChatColor.DARK_AQUA + "You'll be teleported to spawn in " + ChatColor.AQUA + "5 Seconds" + ChatColor.DARK_AQUA + " Do not move.");
            p.setMetadata("goingToSpawn", new FixedMetadataValue(Tazpvp.getInstance(), true));
            new BukkitRunnable(){
                @Override
                public void run() {
                    if (p.hasMetadata("goingToSpawn")){
                        p.teleport(configUtils.spawn);
                        p.removeMetadata("goingToSpawn", Tazpvp.getInstance());
                        p.sendMessage(ChatColor.DARK_AQUA + "Teleportation complete.");
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    }
                }
            }.runTaskLater(Tazpvp.getInstance(), 5 * 20);
        }
    }
}
