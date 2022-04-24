package net.tazpvp.tazpvp.Managers;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.WeakHashMap;

public class StatsManager {
    public final FileConfiguration statsFile;
    final File file;
    public final WeakHashMap<UUID, Scoreboard> scoreboards = new WeakHashMap<>();



    public StatsManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/stats.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void initScoreboard(Player player) {
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        sb.registerNewTeam("a");
        sb.registerNewTeam("b");
        sb.registerNewTeam("c");
        sb.registerNewTeam("d");
        sb.registerNewTeam("e");
        sb.registerNewTeam("f");
        sb.registerNewTeam("g");
        sb.registerNewTeam("h");
        sb.registerNewTeam("i");
        sb.registerNewTeam("j");
        sb.registerNewTeam("k");
        sb.registerNewTeam("l");
        sb.registerNewTeam("m");
        sb.registerNewTeam("n");
        sb.registerNewTeam("o");
        sb.registerNewTeam("z");
        sb.registerNewObjective("sb", "dummy");
        scoreboards.put(player.getUniqueId(), sb);
        Tazpvp.getInstance().initScoreboard(player);
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initPlayer(OfflinePlayer player){
        statsFile.set(player.getUniqueId().toString()+".shards", 0);
        statsFile.set(player.getUniqueId().toString()+".exp", 0);
        statsFile.set(player.getUniqueId().toString()+".expLeft", 45);
        statsFile.set(player.getUniqueId().toString()+".level", 0);
        statsFile.set(player.getUniqueId().toString()+".money", 0);
        statsFile.set(player.getUniqueId().toString()+".deaths", 0);
        statsFile.set(player.getUniqueId().toString()+".kills", 0);
        statsFile.set(player.getUniqueId().toString()+".streak", 0);
        statsFile.set(player.getUniqueId().toString()+".rank", Tazpvp.permissions.getPrimaryGroup((Player) player));
    }

    public int getMoney(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".money");
    }
    public void setMoney(OfflinePlayer player, int money) {
        statsFile.set(player.getUniqueId().toString()+".money", money);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addMoney(OfflinePlayer player, int money) {
        setMoney(player, (money+getMoney(player)));
    }
    public int getShards(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".shards");
    }
    public void setShards(OfflinePlayer player, int points) {
        statsFile.set(player.getUniqueId().toString()+".shards", points);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addShards(OfflinePlayer player, int points) {
        setShards(player, points+getShards(player));
    }
    public int getLevel(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".level");
    }
    public void setLevel(OfflinePlayer player, int level) {
        statsFile.set(player.getUniqueId().toString()+".level", level);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addLevel(OfflinePlayer player, int level) {
        setLevel(player, level+getLevel(player));
    }
    public void addLevels(OfflinePlayer player, int level) {
        setLevel(player, level+getLevel(player));
    }
    public int getDeaths(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".deaths");
    }
    public void setDeaths(OfflinePlayer player, int deaths) {
        statsFile.set(player.getUniqueId().toString()+".deaths", deaths);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public boolean checkLevelUp(OfflinePlayer player){
        if (Tazpvp.statsManager.getExp(player) >= Tazpvp.statsManager.getExpLeft(player)){
            return true;
        } else {
            return false;
        }
    }
    public void levelUp(OfflinePlayer player, int number){
        Tazpvp.statsManager.addLevel(player, 1*number);
        Tazpvp.statsManager.addMoney(player, 60*number);
        Tazpvp.statsManager.setExpLeft(player, (Tazpvp.statsManager.getExpLeft(player)*1.05)*number);
        Tazpvp.statsManager.setExp(player, 0);
        //Tazpvp.statsManager.addMulti(player, 1);
        if (player.isOnline()){
            Player p = (Player) player;
            p.sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat Lvl. " + ChatColor.AQUA + Tazpvp.statsManager.getLevel(player));
            p.sendMessage("");
            p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "  REWARDS");
            p.sendMessage(ChatColor.DARK_GRAY + "  +" + ChatColor.GOLD + "60 Coins");
            p.sendMessage(ChatColor.DARK_AQUA + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            p.setLevel(Tazpvp.statsManager.getLevel(player));
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1 );
        }
    }
    public void addDeaths(OfflinePlayer player, int deaths) {
        setDeaths(player, deaths+getDeaths(player));
    }
    public int getKills(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".kills");
    }
    public void setKills(OfflinePlayer player, int kills) {
        statsFile.set(player.getUniqueId().toString()+".kills", kills);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addKills(OfflinePlayer player, int kills) {
        setKills(player, kills+getKills(player));
    }
    public double getExp(OfflinePlayer player) {
        return statsFile.getDouble(player.getUniqueId().toString()+".exp");
    }
    public void setExp(OfflinePlayer player, double exp) {
        statsFile.set(player.getUniqueId().toString()+".exp", exp);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addExp(OfflinePlayer player, double exp) {
        setExp(player, exp+getExp(player));
    }
    public double getExpLeft(OfflinePlayer player) {
        return statsFile.getDouble(player.getUniqueId().toString()+".expLeft");
    }
    public void setExpLeft(OfflinePlayer player, double exp) {
        statsFile.set(player.getUniqueId().toString()+".expLeft", exp);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public String getGroup(OfflinePlayer player) {
        return statsFile.getString(player.getUniqueId().toString()+".rank");
    }
    public void setGroup(OfflinePlayer player, String group) {
        statsFile.set(player.getUniqueId().toString() + ".rank", group);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public int getStreak(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".streak");
    }
    public void setStreak(OfflinePlayer player, int kills) {
        statsFile.set(player.getUniqueId().toString()+".streak", kills);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addStreak(OfflinePlayer player, int kills) {
        setStreak(player, kills+getStreak(player));
    }


    public Team getTeam(Player player, Scoreboard sb) {
        switch (Tazpvp.permissions.getPrimaryGroup(player).toLowerCase()) {
            case"owner":
                return sb.getTeam("a");
            case"manager":
                return sb.getTeam("b");
            case"sr.admin":
                return sb.getTeam("c");
            case"admin":
                return sb.getTeam("d");
            case"developer":
                return sb.getTeam("e");
            case"sr.mod":
                return sb.getTeam("f");
            case"mod":
                return sb.getTeam("g");
            case"helper":
                return sb.getTeam("h");
            case"builder":
                return sb.getTeam("i");
            case"media":
                return sb.getTeam("j");
            case"vip":
                return sb.getTeam("k");
            case"mvp":
                return sb.getTeam("l");
            case"mvp+":
                return sb.getTeam("m");
            case"mvp++":
                return sb.getTeam("n");
            case"default":
                return sb.getTeam("o");
            case"banned":
                return sb.getTeam("z");
        }
        return null;
    }
    public static <T> T getOrDefault(FileConfiguration file, String path, T defaultReturn) {
        if(file.contains(path) && file.get(path) != null) {
            return (T) file.get(path);
        }
        return defaultReturn;
    }
}
