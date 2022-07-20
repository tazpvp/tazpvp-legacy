package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import static net.tazpvp.tazpvp.Tazpvp.vanished;

public class VanishCMD {
    @CommandHook("vanish")
    public static void vanish(Player p) {
        if (CombatTag.isInCombat(p)){ p.sendMessage(ChatColor.RED + "You cannot vanish while in combat."); return;}
        if (!vanished.contains(p)) {
            for(Player ol : Bukkit.getOnlinePlayers()){
                ol.hidePlayer(Tazpvp.getInstance(), p);
                p.setInvulnerable(true);
                vanished.add(p);
                if (ol.hasPermission("tazpvp.staff")) {
                    ol.sendMessage(ChatColor.YELLOW + "" + p.getName() + " is now vanished.");
                }
                Tazpvp.sendBaseTablist(ol);
            }
        } else {
            for(Player ol : Bukkit.getOnlinePlayers()){
                ol.showPlayer(Tazpvp.getInstance(), p);
                p.setInvulnerable(false);
                vanished.remove(p);
                p.teleport(configUtils.spawn);
                if (ol.hasPermission("tazpvp.staff")) {
                    ol.sendMessage(ChatColor.YELLOW + "" + p.getName() + " is no longer vanished.");
                }
                Tazpvp.sendBaseTablist(ol);
            }
        }
    }
}
