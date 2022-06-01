package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class WarnCMD implements CommandListener {
    @CommandHook("warn")
    public void warn(Player p, Player target, String reason) {
        target.sendMessage(ChatColor.RED + "You have been warned for " + ChatColor.WHITE + reason);
        Tazpvp.punishmentManager.addWarn(target, reason);
        p.sendMessage(ChatColor.DARK_AQUA + "Success! " + target.getName() + " now has " + ChatColor.WHITE + Tazpvp.punishmentManager.getWarns(target).size() + ChatColor.DARK_AQUA + " warns.");
    }
}
