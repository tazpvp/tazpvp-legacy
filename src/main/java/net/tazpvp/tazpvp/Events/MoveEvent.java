package net.tazpvp.tazpvp.Events;


import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MoveEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Block b = new Location(e.getPlayer().getWorld(), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY() - 1, e.getPlayer().getLocation().getZ()).getBlock();
        if (b.getType() == Material.WATER && p.getGameMode() == GameMode.SURVIVAL) {
            if (Tazpvp.lastDamage.containsKey(p.getUniqueId())) {
                new DeathEvent().DeathFunction(p, Bukkit.getPlayer(Tazpvp.lastDamage.get(p.getUniqueId())), false);
            } else {
                new DeathEvent().DeathFunction(p, null, false);
            }
            return;
        }

        Location raidus = new Location(Bukkit.getWorld("arena"), 0, 100, 24);
        if (p.getWorld().getName().equals("arena")) {
            if (p.getLocation().distance(raidus) < 5) {
                Launchpad(p); // Launchpad
            }
        }

        if (Tazpvp.afkPlayers.contains(p)) {
            Tazpvp.afkPlayers.remove(p);
            p.sendMessage(ChatColor.YELLOW + "You are no longer AFK.");
        }

        if (Tazpvp.duelLogic.isInDuel(p)) {
            if (p.getLocation().getY() < 50 && p.getGameMode() == GameMode.SURVIVAL) {
                Tazpvp.duelLogic.duelEnd(p);
            }
        }
        if(p.hasMetadata("goingToSpawn")){
            p.removeMetadata("goingToSpawn", Tazpvp.getInstance());
            e.getPlayer().sendMessage(ChatColor.RED + "Teleportation cancelled, you moved.");
        }

    }

    public void Launchpad(Player p) {
        if (p.getGameMode().equals(GameMode.SURVIVAL)){
            if (!Tazpvp.fallDamageImmune.contains(p)) {
                Tazpvp.fallDamageImmune.add(p);
            }
            p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);
            p.setVelocity(new Vector(0, 1.5, 3));
        }
    }
}
