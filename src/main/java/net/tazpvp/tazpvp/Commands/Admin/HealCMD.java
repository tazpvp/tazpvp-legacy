package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class HealCMD {
    @CommandHook("heal")
    public void heal(Player p, Player target) {
        if (target == null) {
            PlayerUtils.healPlayer(p);
            p.sendMessage(ChatColor.GOLD + "You have healed " + ChatColor.RED + p.getName());
        } else {
            PlayerUtils.healPlayer(target);
            p.sendMessage(ChatColor.GOLD + "You have healed " + ChatColor.RED + target.getName());
        }
    }
}
