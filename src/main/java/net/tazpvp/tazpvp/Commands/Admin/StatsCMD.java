package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class StatsCMD {

    //Points Command

    @CommandHook("point_add")
    public void pointAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addShards(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have added " + ChatColor.RED + amount + ChatColor.GOLD + " points to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " points to " + target.getName());
        }
    }
    @CommandHook("point_remove")
    public void pointRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addShards(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have removed " + ChatColor.RED + amount + ChatColor.GOLD + " points from " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " points from " + target.getName());
        }
    }
    @CommandHook("point_set")
    public void pointSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setShards(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have set " + ChatColor.RED + amount + ChatColor.GOLD + " points to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " points to " + target.getName());
        }
    }
    @CommandHook("point_reset")
    public void pointReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setShards(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have reset " + ChatColor.RED + target.getName() + ChatColor.GOLD + "'s points");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s points");
        }
    }
    @CommandHook("point_get")
    public void pointGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has got " + ChatColor.RED + Tazpvp.statsManager.getShards(target) + ChatColor.GOLD + "'s points");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getShards(target) + "'s points");
        }
    }

    // Exp stats commands

    @CommandHook("exp_add")
    public void expAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addExp(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have added " + ChatColor.RED + amount + ChatColor.GOLD + " exp to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " exp to " + target.getName());
        }
    }
    @CommandHook("exp_remove")
    public void expRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addExp(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have removed " + ChatColor.RED + amount + ChatColor.GOLD + " exp from " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " exp from " + target.getName());
        }
    }
    @CommandHook("exp_set")
    public void expSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setExp(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have set " + ChatColor.RED + amount + ChatColor.GOLD + " exp to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " exp to " + target.getName());
        }
    }
    @CommandHook("exp_reset")
    public void expReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setExp(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have reset " + ChatColor.RED + target.getName() + ChatColor.GOLD + "'s exp");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s exp");
        }
    }
    @CommandHook("exp_get")
    public void expGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has got " + ChatColor.RED + Tazpvp.statsManager.getExp(target) + ChatColor.GOLD + "'s exp");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getExp(target) + "'s exp");
        }
    }

    // Level commands

    @CommandHook("level_add")
    public void levelAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addLevels(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have added " + ChatColor.RED + amount + ChatColor.GOLD + " levels to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " levels to " + target.getName());
        }
    }
    @CommandHook("level_remove")
    public void levelRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addLevels(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have removed " + ChatColor.RED + amount + ChatColor.GOLD + " levels from " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " levels from " + target.getName());
        }
    }
    @CommandHook("level_set")
    public void levelSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setLevel(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have set " + ChatColor.RED + amount + ChatColor.GOLD + " levels to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " levels to " + target.getName());
        }
    }
    @CommandHook("level_reset")
    public void levelReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setLevel(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have reset " + ChatColor.RED + target.getName() + ChatColor.GOLD + "'s levels");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s levels");
        }
    }
    @CommandHook("level_get")
    public void levelGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has got " + ChatColor.RED + Tazpvp.statsManager.getLevel(target) + ChatColor.GOLD + "'s levels");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getLevel(target) + "'s levels");
        }
    }

    // Money commands

    @CommandHook("money_add")
    public void moneyAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addMoney(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have added " + ChatColor.RED + amount + ChatColor.GOLD + " money to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " money to " + target.getName());
        }
    }
    @CommandHook("money_remove")
    public void moneyRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addMoney(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have removed " + ChatColor.RED + amount + ChatColor.GOLD + " money from " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " money from " + target.getName());
        }
    }
    @CommandHook("money_set")
    public void moneySet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setMoney(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have set " + ChatColor.RED + amount + ChatColor.GOLD + " money to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " money to " + target.getName());
        }
    }
    @CommandHook("money_reset")
    public void moneyReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setMoney(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have reset " + ChatColor.RED + target.getName() + ChatColor.GOLD + "'s money");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s money");
        }
    }
    @CommandHook("money_get")
    public void moneyGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has got " + ChatColor.RED + Tazpvp.statsManager.getMoney(target) + ChatColor.GOLD + "'s money");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getMoney(target) + "'s money");
        }
    }

    // Death commands

    @CommandHook("deaths_add")
    public void deathAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addDeaths(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have added " + ChatColor.RED + amount + ChatColor.GOLD + " deaths to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " deaths to " + target.getName());
        }
    }
    @CommandHook("deaths_remove")
    public void deathRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addDeaths(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have removed " + ChatColor.RED + amount + ChatColor.GOLD + " deaths from " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " deaths from " + target.getName());
        }
    }
    @CommandHook("deaths_set")
    public void deathSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setDeaths(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have set " + ChatColor.RED + amount + ChatColor.GOLD + " deaths to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " deaths to " + target.getName());
        }
    }
    @CommandHook("deaths_reset")
    public void deathReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setDeaths(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have reset " + ChatColor.RED + target.getName() + ChatColor.GOLD + "'s deaths");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s deaths");
        }
    }
    @CommandHook("deaths_get")
    public void deathGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has got " + ChatColor.RED + Tazpvp.statsManager.getDeaths(target) + ChatColor.GOLD + "'s deaths");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getDeaths(target) + "'s deaths");
        }
    }

    // Kill commands

    @CommandHook("kills_add")
    public void killAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addKills(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have added " + ChatColor.RED + amount + ChatColor.GOLD + " kills to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " kills to " + target.getName());
        }
    }
    @CommandHook("kills_remove")
    public void killRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addKills(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have removed " + ChatColor.RED + amount + ChatColor.GOLD + " kills from " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " kills from " + target.getName());
        }
    }
    @CommandHook("kills_set")
    public void killSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setKills(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have set " + ChatColor.RED + amount + ChatColor.GOLD + " kills to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " kills to " + target.getName());
        }
    }
    @CommandHook("kills_reset")
    public void killReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setKills(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have reset " + ChatColor.RED + target.getName() + ChatColor.GOLD + "'s kills");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s kills");
        }
    }
    @CommandHook("kills_get")
    public void killGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has got " + ChatColor.RED + Tazpvp.statsManager.getKills(target) + ChatColor.GOLD + "'s kills");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getKills(target) + "'s kills");
        }
    }

    // Killstreak commands

    @CommandHook("streak_add")
    public void killstreakAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addStreak(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have added " + ChatColor.RED + amount + ChatColor.GOLD + " killstreaks to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " killstreaks to " + target.getName());
        }
    }
    @CommandHook("streak_remove")
    public void killstreakRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addStreak(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have removed " + ChatColor.RED + amount + ChatColor.GOLD + " killstreaks from " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " killstreaks from " + target.getName());
        }
    }
    @CommandHook("streak_set")
    public void killstreakSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setStreak(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have set " + ChatColor.RED + amount + ChatColor.GOLD + " killstreaks to " + ChatColor.RED + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " killstreaks to " + target.getName());
        }
    }
    @CommandHook("streak_reset")
    public void killstreakReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setStreak(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.GOLD + "You have reset " + ChatColor.RED + target.getName() + ChatColor.GOLD + "'s killstreaks");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s killstreaks");
        }
    }
    @CommandHook("streak_get")
    public void killstreakGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.RED + target.getName() + ChatColor.GOLD + " has got " + ChatColor.RED + Tazpvp.statsManager.getStreak(target) + ChatColor.GOLD + "'s killstreaks");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getStreak(target) + "'s killstreaks");
        }
    }

}
