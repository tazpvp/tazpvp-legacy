package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.configUtils;
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

public class SpawnCMD implements Listener {
    @CommandHook("spawn")
    public void spawn(Player p){
        if (p.hasPermission("tazpvp.spawn")){
            p.teleport(configUtils.spawn);
        } else {
            p.sendMessage(ChatColor.GOLD + "You will be teleported to spawn in " + ChatColor.RED + "5s");
            p.setMetadata("goingToSpawn", new FixedMetadataValue(Tazpvp.getInstance(), true));

            new BukkitRunnable(){
                @Override
                public void run() {
                    if (isGoingToSpawn(p)){
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
        if(isGoingToSpawn(e.getPlayer())){
            e.getPlayer().setMetadata("goingToSpawn", new FixedMetadataValue(Tazpvp.getInstance(), false));
            e.getPlayer().sendMessage(ChatColor.RED + "Teleportation cancelled, you moved.");
        }
    }

    @EventHandler
    public void Damage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (isGoingToSpawn(p)) {
                p.setMetadata("goingToSpawn", new FixedMetadataValue(Tazpvp.getInstance(), false));
                p.sendMessage(ChatColor.GREEN + "Detected Damage! Canceling teleportation!");
            }
        }
    }

    public boolean isGoingToSpawn(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("goingToSpawn");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asBoolean();
        }
        return false;
    }
}
