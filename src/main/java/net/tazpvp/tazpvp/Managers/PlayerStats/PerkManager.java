package net.tazpvp.tazpvp.Managers.PlayerStats;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PerkManager {
    public final FileConfiguration statsFile;
    final File file;

    public PerkManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/perks.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void initPerks(OfflinePlayer p){
        statsFile.set(p.getUniqueId().toString()+".hasClickedMiner", false);
    }

    public boolean getFatPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".fatPerk"); }
    public void setFatPerk(Player p, boolean type){ statsFile.set(p.getUniqueId().toString()+".fatPerk", type);}

    public boolean getExcavatorPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".excavatorPerk"); }
    public void setExcavatorPerk(Player p, boolean type){ statsFile.set(p.getUniqueId().toString()+".excavatorPerk", type);}

    public boolean getArcherPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".archerPerk"); }
    public void setArcherPerk(Player p, boolean type){ statsFile.set(p.getUniqueId().toString()+".archerPerk", type);}

    public boolean getBuilderPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".builderPerk"); }
    public void setBuilderPerk(Player p, boolean type){ statsFile.set(p.getUniqueId().toString()+".builderPerk", type); }

    public boolean getGobblePerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".gobblePerk"); }
    public void setGobblePerk(Player p, boolean type){ statsFile.set(p.getUniqueId().toString()+".gobblePerk", type); }

    public boolean getAgilityPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".agilityPerk"); }
    public void setAgilityPerk(Player p, boolean type){ statsFile.set(p.getUniqueId().toString()+".agilityPerk", type); }

    public boolean getTankPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".tankPerk"); }
    public void setTankPerk(Player p, boolean type){ statsFile.set(p.getUniqueId().toString()+".tankPerk", type); }

    public void setStatsString(Player p, String s, boolean value) {
        statsFile.set(p.getUniqueId().toString()+"."+s, value);
    }

    public boolean getStatsString(Player p, String s) {
        return statsFile.getBoolean(p.getUniqueId().toString()+"."+s);
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
