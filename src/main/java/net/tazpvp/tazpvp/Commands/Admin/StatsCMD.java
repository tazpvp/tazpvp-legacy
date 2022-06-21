package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class StatsCMD implements CommandListener {

    //Points Command

    @CommandHook("shard_add")
    public void shardAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addShards(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " shards to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " shards to " + target.getName());
        }
    }
    @CommandHook("shard_remove")
    public void shardRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addShards(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " shards from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " shards from " + target.getName());
        }
    }
    @CommandHook("shard_set")
    public void shardSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setShards(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " shards to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " shards to " + target.getName());
        }
    }
    @CommandHook("shard_reset")
    public void shardReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setShards(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s shards");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s shards");
        }
    }
    @CommandHook("shard_get")
    public void shardGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getShards(target) + ChatColor.DARK_AQUA + "'s shards");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getShards(target) + "'s shards");
        }
    }

    // Exp stats commands

    @CommandHook("exp_add")
    public void expAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addExp(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " exp to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " exp to " + target.getName());
        }
    }
    @CommandHook("exp_remove")
    public void expRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addExp(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " exp from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " exp from " + target.getName());
        }
    }
    @CommandHook("exp_set")
    public void expSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setExp(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " exp to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " exp to " + target.getName());
        }
    }
    @CommandHook("exp_reset")
    public void expReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setExp(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s exp");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s exp");
        }
    }
    @CommandHook("exp_get")
    public void expGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getExp(target) + ChatColor.DARK_AQUA + "'s exp");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getExp(target) + "'s exp");
        }
    }

    // Level commands

    @CommandHook("level_add")
    public void levelAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addLevels(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " levels to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " levels to " + target.getName());
        }
    }
    @CommandHook("level_remove")
    public void levelRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addLevels(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " levels from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " levels from " + target.getName());
        }
    }
    @CommandHook("level_set")
    public void levelSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setLevel(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " levels to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " levels to " + target.getName());
        }
    }
    @CommandHook("level_reset")
    public void levelReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setLevel(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s levels");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s levels");
        }
    }
    @CommandHook("level_get")
    public void levelGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getLevel(target) + ChatColor.DARK_AQUA + "'s levels");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getLevel(target) + "'s levels");
        }
    }

    // Money commands

    @CommandHook("coins_add")
    public void moneyAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addCoins(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " money to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " money to " + target.getName());
        }
    }
    @CommandHook("coins_remove")
    public void moneyRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addCoins(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " money from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " money from " + target.getName());
        }
    }
    @CommandHook("coins_set")
    public void moneySet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setCoins(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " money to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " money to " + target.getName());
        }
    }
    @CommandHook("coins_reset")
    public void moneyReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setCoins(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s money");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s money");
        }
    }
    @CommandHook("coins_get")
    public void moneyGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getCoins(target) + ChatColor.DARK_AQUA + "'s money");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getCoins(target) + "'s money");
        }
    }

    // Death commands

    @CommandHook("deaths_add")
    public void deathAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addDeaths(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " deaths to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " deaths to " + target.getName());
        }
    }
    @CommandHook("deaths_remove")
    public void deathRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addDeaths(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " deaths from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " deaths from " + target.getName());
        }
    }
    @CommandHook("deaths_set")
    public void deathSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setDeaths(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " deaths to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " deaths to " + target.getName());
        }
    }
    @CommandHook("deaths_reset")
    public void deathReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setDeaths(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s deaths");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s deaths");
        }
    }
    @CommandHook("deaths_get")
    public void deathGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getDeaths(target) + ChatColor.DARK_AQUA + "'s deaths");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getDeaths(target) + "'s deaths");
        }
    }

    // Kill commands

    @CommandHook("kills_add")
    public void killAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addKills(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " kills to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " kills to " + target.getName());
        }
    }
    @CommandHook("kills_remove")
    public void killRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addKills(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " kills from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " kills from " + target.getName());
        }
    }
    @CommandHook("kills_set")
    public void killSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setKills(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " kills to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " kills to " + target.getName());
        }
    }
    @CommandHook("kills_reset")
    public void killReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setKills(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s kills");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s kills");
        }
    }
    @CommandHook("kills_get")
    public void killGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getKills(target) + ChatColor.DARK_AQUA + "'s kills");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getKills(target) + "'s kills");
        }
    }

    // Killstreak commands

    @CommandHook("streak_add")
    public void killstreakAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addStreak(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " killstreaks to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " killstreaks to " + target.getName());
        }
    }
    @CommandHook("streak_remove")
    public void killstreakRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addStreak(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " killstreaks from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " killstreaks from " + target.getName());
        }
    }
    @CommandHook("streak_set")
    public void killstreakSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setStreak(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " killstreaks to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " killstreaks to " + target.getName());
        }
    }
    @CommandHook("streak_reset")
    public void killstreakReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setStreak(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s killstreaks");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s killstreaks");
        }
    }
    @CommandHook("streak_get")
    public void killstreakGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getStreak(target) + ChatColor.DARK_AQUA + "'s killstreaks");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getStreak(target) + "'s killstreaks");
        }
    }

    // Spins commands

    @CommandHook("spins_add")
    public void spinsAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addSpins(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " spins to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " spins to " + target.getName());
        }
    }
    @CommandHook("spins_remove")
    public void spinsRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addSpins(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " spins from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " spins from " + target.getName());
        }
    }
    @CommandHook("spins_set")
    public void spinsSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setSpins(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " spins to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " spins to " + target.getName());
        }
    }
    @CommandHook("spins_reset")
    public void spinsReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setSpins(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s spins");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s spins");
        }
    }
    @CommandHook("spins_get")
    public void spinsGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getSpins(target) + ChatColor.DARK_AQUA + "'s spins");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getSpins(target) + "'s spins");
        }
    }

    //credits commands
    @CommandHook("credits_add")
    public void creditsAdd(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addCredits(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have added " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " credits to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has added " + amount + " credits to " + target.getName());
        }
    }
    @CommandHook("credits_remove")
    public void creditsRemove(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.addCredits(target, -amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have removed " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " credits from " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has removed " + amount + " credits from " + target.getName());
        }
    }
    @CommandHook("credits_set")
    public void creditsSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setCredits(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " credits to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " credits to " + target.getName());
        }
    }
    @CommandHook("credits_reset")
    public void creditsReset(CommandSender sender, Player target) {
        Tazpvp.statsManager.setCredits(target, 0);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s credits");
        } else {
            sender.sendMessage("Console has reset " + target.getName() + "'s credits");
        }
    }
    @CommandHook("credits_get")
    public void creditsGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getCredits(target) + ChatColor.DARK_AQUA + "'s credits");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getCredits(target) + "'s credits");
        }
    }

    // expleft commands

    @CommandHook("expleft_set")
    public void expleftSet(CommandSender sender, Player target, int amount) {
        Tazpvp.statsManager.setExpLeft(target, amount);
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have set " + ChatColor.AQUA + amount + ChatColor.DARK_AQUA + " exp left to " + ChatColor.AQUA + target.getName());
        } else {
            sender.sendMessage("Console has set " + amount + " exp left to " + target.getName());
        }
    }

    @CommandHook("expleft_get")
    public void expleftGet(CommandSender sender, Player target) {
        if (sender instanceof Player p) {
            p.sendMessage(ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + " has got " + ChatColor.AQUA + Tazpvp.statsManager.getExpLeft(target) + ChatColor.DARK_AQUA + "'s exp left");
        } else {
            sender.sendMessage(target.getName()+ " has got " + Tazpvp.statsManager.getExpLeft(target) + "'s exp left");
        }
    }

    @CommandHook("stats_reset")
    public void resetStats(Player p, Player target) {
        Tazpvp.statsManager.initPlayer(target);
        Tazpvp.playerWrapperStatsManager.wipeSwords(target);
        Tazpvp.boolManager.setHasRebirthed(target, false);
        p.sendMessage(ChatColor.DARK_AQUA + "You have reset " + ChatColor.AQUA + target.getName() + ChatColor.DARK_AQUA + "'s stats and swords");
    }

    @CommandHook("stats_scoreboard")
    public void showScore(Player p) {
        Tazpvp.getInstance().initScoreboard(p);
    }

}
