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

    @CommandHook("pay")
    public void payCMD(Player p, Player target, int amount) {
        if (Tazpvp.statsManager.getMoney(p) >= amount && amount > 0) {
            p.sendMessage(ChatColor.YELLOW + "You paid " + ChatColor.GOLD + amount + "$ " + ChatColor.YELLOW + "to " + ChatColor.GOLD + target.getName());
            Tazpvp.statsManager.addMoney(p, -amount);
            Tazpvp.statsManager.addMoney(target, amount);
            target.sendMessage(ChatColor.YELLOW + "You received " + ChatColor.GOLD + amount + "$ " + ChatColor.YELLOW + "from " + ChatColor.GOLD + p.getName());
        } else {
            p.sendMessage(ChatColor.RED + "You don't have enough money!");
        }
    }
}
