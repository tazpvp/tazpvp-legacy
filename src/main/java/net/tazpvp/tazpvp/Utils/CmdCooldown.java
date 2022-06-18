package net.tazpvp.tazpvp.Utils;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CmdCooldown implements CommandExecutor {
    private static Map<UUID, Integer> cooldowns = new HashMap<>();
    private int cooldown;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player p)) return true;
        UUID uuid = p.getUniqueId();
        if (cooldowns.containsKey(uuid)){
            p.sendMessage(ChatColor.RED +"Please wait before using this command again.");
            return true;
        }
        cooldowns.put(uuid, cooldown);
        new BukkitRunnable(){
            @Override
            public void run() {
                if (cooldowns.containsKey(uuid)){
                    cooldowns.remove(uuid);
                }
            }
        }.runTaskLater(Tazpvp.getInstance(), cooldown);
        execute(p, strings);
        return false;
    }

    public CmdCooldown(int num){
        this.cooldown = num;
    }

    abstract void execute(Player p, String[] strings);
}
