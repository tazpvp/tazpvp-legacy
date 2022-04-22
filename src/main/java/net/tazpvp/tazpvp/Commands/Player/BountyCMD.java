package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.util.UUID;

public class BountyCMD {
    @CommandHook("bounty_set")
    public void bounty_set(Player p, Player target, int amount) {
        if (Tazpvp.statsManager.getMoney(p) > amount) {
            Tazpvp.statsManager.setMoney(p, Tazpvp.statsManager.getMoney(p) - amount);
            //actually putting the bounty
            if (Tazpvp.bounty.containsKey(target.getUniqueId())) {
                Tazpvp.bounty.put(target.getUniqueId(), Tazpvp.bounty.get(target.getUniqueId()) + amount);
            } else {
                Tazpvp.bounty.put(target.getUniqueId(), amount);
            }
            Bukkit.broadcastMessage(ChatColor.AQUA + p.getName() + ChatColor.DARK_AQUA + "set a bounty of " + ChatColor.AQUA + amount + "$" + ChatColor.DARK_AQUA + " on " + ChatColor.AQUA + target.getName());
        } else {
            p.sendMessage(ChatColor.RED + "You don't have enough money");
        }
    }

    @CommandHook("bounty_get")
    public void bounty_get(Player p, Player target) {
        if (Tazpvp.bounty.containsKey(target.getUniqueId())) {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has a bounty of " + ChatColor.RED + Tazpvp.bounty.get(target.getUniqueId()) + "$");
        } else {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has no bounty");
        }
    }

    @CommandHook("bounty_remove")
    public void bounty_remove(Player p, Player target) {
        if (Tazpvp.bounty.containsKey(target.getUniqueId())) {
            if (Tazpvp.statsManager.getMoney(p) > Tazpvp.bounty.get(target.getUniqueId())) {
                Tazpvp.statsManager.setMoney(p, Tazpvp.statsManager.getMoney(p) - Tazpvp.bounty.get(target.getUniqueId()));
                Tazpvp.bounty.remove(target.getUniqueId());
                p.sendMessage(ChatColor.GOLD + "Removed the bounty of " + ChatColor.RED + target.getName());
            } else {
                p.sendMessage(ChatColor.RED + "You don't have enough money");
            }
        } else {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " does not have a bounty");
        }
    }

    @CommandHook("bounty_forceremove")
    public void bounty_forceremove(Player p, Player target) {
        if (Tazpvp.bounty.containsKey(target.getUniqueId())) {
            Tazpvp.bounty.remove(target.getUniqueId());
            p.sendMessage(ChatColor.GOLD + "Removed the bounty of " + ChatColor.RED + target.getName());
        } else {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " does not have a bounty");
        }
    }

    @CommandHook("bounty_list")
    public void bounty_list(Player p) {
        if (Tazpvp.bounty.size() > 0) {
            p.sendMessage(ChatColor.GOLD + "Bounties:");
            for (UUID uuid : Tazpvp.bounty.keySet()) {
                Player bounti = Bukkit.getPlayer(uuid);
                p.sendMessage("  " + ChatColor.RED + bounti.getName() + ChatColor.GOLD + ": " + ChatColor.RED + Tazpvp.bounty.get(uuid) + "$");
            }
        } else {
            p.sendMessage(ChatColor.RED + "There are no active bounties");
        }
    }
}
