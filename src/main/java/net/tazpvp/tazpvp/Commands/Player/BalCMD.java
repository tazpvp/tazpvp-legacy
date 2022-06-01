package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class BalCMD implements CommandListener {
    @CommandHook("bal")
    public void getBal(CommandSender sender, Player target) {
        int bal = Tazpvp.statsManager.getMoney(target);
        sender.sendMessage(ChatColor.YELLOW + target.getName() + "'s " + ChatColor.GOLD + "balance is " + ChatColor.YELLOW + bal + "$");
    }
}
