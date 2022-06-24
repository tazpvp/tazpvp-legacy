package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Duels.DuelLogic;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.commandmanager.CommandHook;

import java.util.List;

public class SpawnCMD implements Listener, CommandListener {
    @CommandHook("spawn")
    public void spawn(Player p, Player target){
        if (Tazpvp.punishmentManager.isBanned(p)) return;
        if (Tazpvp.duelLogic.isInDuel(target)) return;
        if (Tazpvp.duelLogic.isInDuel(p)) return;
        if (!target.equals(p)) {
            if (!p.hasPermission("tazpvp.spawn")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return;
            } else {
                target.teleport(configUtils.spawn);
            }
        }
        if (CombatTag.isInCombat(p)) {
            p.sendMessage(ChatColor.RED + "You cannot go to spawn while in combat.");
            return;
        }
        if (target.hasPermission("tazpvp.spawn")){
            target.teleport(configUtils.spawn);
        } else {
            target.sendMessage(ChatColor.DARK_AQUA + "You'll be teleported to spawn in " + ChatColor.AQUA + "5 Seconds" + ChatColor.DARK_AQUA + " Do not move.");
            target.setMetadata("goingToSpawn", new FixedMetadataValue(Tazpvp.getInstance(), true));

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

    @EventHandler
    public void NoMoveNo(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.hasMetadata("goingToSpawn")){
            p.removeMetadata("goingToSpawn", Tazpvp.getInstance());
            e.getPlayer().sendMessage(ChatColor.RED + "Teleportation cancelled, you moved.");
        }
    }

    @EventHandler
    public void Damage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player p) {
            if (p.hasMetadata("goingToSpawn")) {
                p.removeMetadata("goingToSpawn", Tazpvp.getInstance());
                p.sendMessage(ChatColor.GREEN + "Teleportation cancelled, damage taken.");
            }
        }
    }
//
//    public boolean isGoingToSpawn(Player p){
//        List<MetadataValue> metaDataValues = p.getMetadata("goingToSpawn");
//        for (MetadataValue metaDataValue : metaDataValues) {
//            return metaDataValue.asBoolean();
//        }
//        return false;
//    }
}
