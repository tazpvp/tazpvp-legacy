package net.tazpvp.tazpvp.Managers.PlayerStats;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
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
        teamReg(sb, "a", "owner");
        teamReg(sb, "b", "admin");
        teamReg(sb, "c", "developer");
        teamReg(sb, "d", "sr.mod");
        teamReg(sb, "e", "mod");
        teamReg(sb, "f", "helper");
        teamReg(sb, "g", "media");
        teamReg(sb, "h", "mvp+");
        teamReg(sb, "i", "mvp");
        teamReg(sb, "j", "vip");
        teamReg(sb, "k", "default");
        teamReg(sb, "l", "banned");
        sb.registerNewTeam(player.getUniqueId().toString());
        sb.registerNewObjective("sb", "dummy", ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TAZPVP.NET");
        scoreboards.put(player.getUniqueId(), sb);
        Tazpvp.getInstance().initScoreboard(player);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Tazpvp.getInstance().addPlayerToOnlinePlayersSB(onlinePlayer);
            Tazpvp.getInstance().addOnlinePlayersToSB(onlinePlayer);
        }
    }

    public void teamReg(Scoreboard sb, String id, String teamName) {
        Team team = sb.registerNewTeam(id);
        String prefix = getPrefix(teamName);
        prefix = prefix.length() > 0 ? ChatColor.translateAlternateColorCodes('&', prefix) : "";
        team.setPrefix(prefix);
        team.setColor(getColor(teamName));
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initPlayer(OfflinePlayer player){
        statsFile.set(player.getUniqueId() +".shards", 0);
        statsFile.set(player.getUniqueId() +".exp", 0);
        statsFile.set(player.getUniqueId() +".expLeft", 45);
        statsFile.set(player.getUniqueId() +".level", 0);
        statsFile.set(player.getUniqueId() +".coins", 0);
        statsFile.set(player.getUniqueId() +".deaths", 0);
        statsFile.set(player.getUniqueId() +".kills", 0);
        statsFile.set(player.getUniqueId() +".streak", 0);
        statsFile.set(player.getUniqueId() +".rank", Tazpvp.permissions.getPrimaryGroup((Player) player));
        statsFile.set(player.getUniqueId() +".bowType", 0);
        statsFile.set(player.getUniqueId() +".rebirth", 0);
        statsFile.set(player.getUniqueId() +".spins", 0);
        statsFile.set(player.getUniqueId() +".credits", 0);
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
    public void setShards(OfflinePlayer player, int shards) {
        statsFile.set(player.getUniqueId().toString()+".shards", shards);
        Tazpvp.getInstance().initScoreboard((Player) player);
    }
    public void addShards(OfflinePlayer player, int shards) {
        setShards(player, shards+getShards(player));
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
        Tazpvp.statsManager.addCoins(player, 45*number);
        Tazpvp.statsManager.addShards(player, 1);
        Tazpvp.statsManager.setExpLeft(player, (Tazpvp.statsManager.getExpLeft(player)*1.05)*number);
        Tazpvp.statsManager.setExp(player, 0);
        //Tazpvp.statsManager.addMulti(player, 1);
        if (player.isOnline()){
            Player p = (Player) player;
            p.sendMessage("");
            p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  LEVEL UP " + ChatColor.DARK_AQUA + "Combat Lvl. " + ChatColor.AQUA + Tazpvp.statsManager.getLevel(player));
            p.sendMessage("");
            p.sendMessage(ChatColor.DARK_GRAY + "  ▶ " + ChatColor.GOLD + "45 Coins");
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
            case "admin" -> sb.getTeam("b");
            case "developer" -> sb.getTeam("c");
            case "sr.mod" -> sb.getTeam("d");
            case "mod" -> sb.getTeam("e");
            case "helper" -> sb.getTeam("f");
            case "media" -> sb.getTeam("g");
            case "mvp+" -> sb.getTeam("h");
            case "mvp" -> sb.getTeam("i");
            case "vip" -> sb.getTeam("j");
            // yes yes switch aroubnd bc ykykykk
            case "banned" -> sb.getTeam("l");
            default -> sb.getTeam("k");
        };
    }

    public ChatColor getColor(Player p) {
        if (Tazpvp.hidden.contains(p.getUniqueId())) {
            return ChatColor.GRAY;
        }
        return switch (Tazpvp.permissions.getPrimaryGroup(p).toLowerCase()) {
            case "owner", "media", "vip" -> ChatColor.RED;
            case "admin", "mvp+" -> ChatColor.GOLD;
            case "developer" -> ChatColor.YELLOW;
            case "sr.mod" -> ChatColor.BLUE;
            case "mod" -> ChatColor.DARK_AQUA;
            case "helper" -> ChatColor.AQUA;
            case "mvp" -> ChatColor.GREEN;
            case "banned" -> ChatColor.DARK_GRAY;
            default -> ChatColor.GRAY;
        };
    }
    public ChatColor getColor(String teamName) {
        return switch (teamName.toLowerCase()) {
            case "owner", "media", "vip" -> ChatColor.RED;
            case "admin", "mvp+" -> ChatColor.GOLD;
            case "developer" -> ChatColor.YELLOW;
            case "sr.mod" -> ChatColor.BLUE;
            case "mod" -> ChatColor.DARK_AQUA;
            case "helper" -> ChatColor.AQUA;
            case "mvp" -> ChatColor.GREEN;
            case "banned" -> ChatColor.DARK_GRAY;
            default -> ChatColor.GRAY;
        };
    }

    public String getPrefix(String teamName) {
        return switch (teamName) {
            case "owner" -> "&c&lOWNER ";
            case "admin" -> "&6&lADMIN ";
            case "developer" -> "&e&lDEV ";
            case "sr.mod" -> "&9&lSR.MOD ";
            case "mod" -> "&3MOD ";
            case "helper" -> "&b&lHELPER ";
            case "media" -> "&c&lMEDIA ";
            case "mvp+" -> "&6&lMVP+ ";
            case "mvp" -> "&a&lMVP ";
            case "vip" -> "&c&lVIP ";
            // yes yes switch aroubnd bc ykykykk
            case "banned" -> "";
            default -> "";
        };
    }

    public void updateXPBar(final Player p) {
        p.setExp(getXpBar((float) getExp(p), (float) getExpLeft(p)));
    }

    public float getXpBar(final float currXp, final float xpLeft) {
        return currXp / xpLeft;
    }
    public static <T> T getOrDefault(FileConfiguration file, String path, T defaultReturn) {
        if(file.contains(path) && file.get(path) != null) {
            return (T) file.get(path);
        }
        return defaultReturn;
    }

    public Scoreboard scoreboards(UUID uniqueId) {
        return scoreboards.get(uniqueId);
    }
}
