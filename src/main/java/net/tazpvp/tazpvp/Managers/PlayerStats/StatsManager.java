package net.tazpvp.tazpvp.Managers.PlayerStats;

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
        sb.registerNewTeam(player.getUniqueId().toString());
        sb.registerNewObjective("sb", "dummy");
        scoreboards.put(player.getUniqueId(), sb);
        Tazpvp.getInstance().initScoreboard(player);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Tazpvp.getInstance().addPlayerToOnlinePlayersSB(onlinePlayer);
            Tazpvp.getInstance().addOnlinePlayersToSB(onlinePlayer);
        }
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
        statsFile.set(player.getUniqueId().toString()+".coins", 0);
        statsFile.set(player.getUniqueId().toString()+".deaths", 0);
        statsFile.set(player.getUniqueId().toString()+".kills", 0);
        statsFile.set(player.getUniqueId().toString()+".streak", 0);
        statsFile.set(player.getUniqueId().toString()+".rank", Tazpvp.permissions.getPrimaryGroup((Player) player));
        statsFile.set(player.getUniqueId().toString()+".bowType", 0);
        statsFile.set(player.getUniqueId().toString()+".rebirth", 0);
        statsFile.set(player.getUniqueId().toString()+".spins", 0);
        statsFile.set(player.getUniqueId().toString()+".credits", 0);
    }

    public int getCoins(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".coins");
    }
    public int getCoins(UUID player) {
        return statsFile.getInt(player.toString()+".coins");
    }
    public void setCoins(OfflinePlayer player, int coins) {
        statsFile.set(player.getUniqueId().toString()+".coins", coins);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addCoins(OfflinePlayer player, int coins) {
        setCoins(player, (coins+getCoins(player)));
    }
    public int getShards(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".shards");
    }
    public int getShards(UUID player) {
        return statsFile.getInt(player.toString()+".shards");
    }
    public void setShards(OfflinePlayer player, int points) {
        statsFile.set(player.getUniqueId().toString()+".shards", points);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addShards(OfflinePlayer player, int points) {
        setShards(player, points+getShards(player));
    }

    public int getRebirth(OfflinePlayer player){return statsFile.getInt(player.getUniqueId().toString()+".rebirth");}
    public void setRebirth(OfflinePlayer player, int rebirth){statsFile.set(player.getUniqueId().toString()+".rebirth", rebirth);}
    public void addRebirth(OfflinePlayer player, int rebirth){setRebirth(player, rebirth+getRebirth(player));}

    public int getLevel(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".level");
    }
    public int getLevel(UUID player) {
        return statsFile.getInt(player.toString()+".level");
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
    public int getDeaths(UUID player) {
        return statsFile.getInt(player.toString()+".deaths");
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
        Tazpvp.statsManager.addCoins(player, 60*number);
        Tazpvp.statsManager.addShards(player, 1);
        Tazpvp.statsManager.setExpLeft(player, (Tazpvp.statsManager.getExpLeft(player)*1.05)*number);
        Tazpvp.statsManager.setExp(player, 0);
        //Tazpvp.statsManager.addMulti(player, 1);
        if (player.isOnline()){
            Player p = (Player) player;
            p.sendMessage("");
            p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat Lvl. " + ChatColor.AQUA + Tazpvp.statsManager.getLevel(player));
            p.sendMessage("");
            p.sendMessage(ChatColor.DARK_GRAY + "  ▶ " + ChatColor.GOLD + "60 Coins");
            p.sendMessage(ChatColor.DARK_GRAY + "  ▶ " + ChatColor.DARK_AQUA + "1 Shard");
            p.sendMessage("");
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
    public int getKills(UUID player) {
        return statsFile.getInt(player.toString()+".kills");
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
    public double getExp(UUID player) {
        return statsFile.getDouble(player.toString()+".exp");
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
    public double getExpLeft(UUID player) {
        return statsFile.getDouble(player.toString()+".expLeft");
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
    public int getBowType(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".bowType");
    }
    public void setBowType(OfflinePlayer player, int type) {
        statsFile.set(player.getUniqueId().toString()+".bowType", type);
    }
    public int getSpins(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".spins");
    }
    public void setSpins(OfflinePlayer player, int spins) {
        statsFile.set(player.getUniqueId().toString()+".spins", spins);
    }
    public void addSpins(OfflinePlayer player, int spins) {
        setSpins(player, spins+getSpins(player));
    }
    public int getCredits(OfflinePlayer player) {
        return statsFile.getInt(player.getUniqueId().toString()+".credits");
    }
    public void setCredits(OfflinePlayer player, int credits) {
        statsFile.set(player.getUniqueId().toString()+".credits", credits);
    }
    public void addCredits(OfflinePlayer player, int credits) { setCredits(player, credits+getCredits(player)); }


    public Team getTeam(Player player, Scoreboard sb) {
        return switch (Tazpvp.permissions.getPrimaryGroup(player).toLowerCase()) {
            case "owner" -> sb.getTeam("a");
            case "manager" -> sb.getTeam("b");
            case "sr.admin" -> sb.getTeam("c");
            case "admin" -> sb.getTeam("d");
            case "developer" -> sb.getTeam("e");
            case "sr.mod" -> sb.getTeam("f");
            case "mod" -> sb.getTeam("g");
            case "helper" -> sb.getTeam("h");
            case "builder" -> sb.getTeam("i");
            case "media" -> sb.getTeam("j");
            case "vip" -> sb.getTeam("k");
            case "mvp" -> sb.getTeam("l");
            case "mvp+" -> sb.getTeam("m");
            case "mvp++" -> sb.getTeam("n");
            case "banned" -> sb.getTeam("z");
            default -> sb.getTeam("o");
        };
    }
    public static <T> T getOrDefault(FileConfiguration file, String path, T defaultReturn) {
        if(file.contains(path) && file.get(path) != null) {
            return (T) file.get(path);
        }
        return defaultReturn;
    }
}
