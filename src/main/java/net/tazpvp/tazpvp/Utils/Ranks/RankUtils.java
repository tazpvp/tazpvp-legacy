package net.tazpvp.tazpvp.Utils.Ranks;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import redempt.redlib.itemutils.ItemBuilder;

import javax.annotation.Nullable;

public class RankUtils implements Listener {

    public static void rankBuy(Player p, String Rank) {
        rankMSG(p, null, Rank);
    }

    public static void rankGift(Player p, String Rank, int Price) {
        new AnvilGUI.Builder()
                .onComplete((player, text) -> {
                    if (text.contains(">")) {
                        text = text.replaceFirst(">", "");
                    }
                    if (text.equalsIgnoreCase(p.getName())) {
                        p.sendMessage(ChatColor.RED + "You can't gift yourself a rank!");
                        return AnvilGUI.Response.close();
                    }
                    if (Bukkit.getPlayer(text) == null) {
                        p.sendMessage(ChatColor.RED + "That player is not online!");
                        return AnvilGUI.Response.close();
                    }
                    Player target = Bukkit.getPlayer(text);

                    Tazpvp.statsManager.addMoney(player, -Price);
                    rankMSG(p, target, Rank);
                    return AnvilGUI.Response.close();
                })
                .text(">")
                .itemLeft(new ItemBuilder(Material.NAME_TAG).setName(ChatColor.GREEN + "Gift").setLore(ChatColor.GRAY + Rank))
                .title(ChatColor.YELLOW + "Gift Recipient:")
                .plugin(Tazpvp.getInstance())
                .open(p);
    }

    private static void cancelRefundFunc(Player p, int Price) {
        p.sendMessage(ChatColor.RED + "Canceled, refunding money lost.");
        Tazpvp.statsManager.addMoney(p, Price);
        p.closeInventory();
    }

    private static void rankMSG(Player p, @Nullable Player target, String rank) {
        Player recipient = null;
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
        if (target == null) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " just purchased " + ChatColor.YELLOW + "[" + rank.toUpperCase() + "]" + ChatColor.GOLD + " in the store!");
            recipient = p;
        } else {
            Bukkit.broadcastMessage(ChatColor.YELLOW + p.getName() + ChatColor.GOLD + " has gifted " + ChatColor.YELLOW + "[" + rank.toUpperCase() + "]" + ChatColor.GOLD + " to " + ChatColor.YELLOW + target.getName());
            recipient = target;
        }
        Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");

        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.playSound(pl.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
        }
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, PlayerUtils.setLPRankCommand(recipient, rank));
    }
}
