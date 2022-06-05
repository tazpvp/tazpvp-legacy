package net.tazpvp.tazpvp.Managers.PlayerStats;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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

    public boolean getFatPerk(OfflinePlayer p){ return statsFile.getBoolean(p.getUniqueId().toString()+".fatPerk"); }
    public void setFatPerk(OfflinePlayer p, boolean type){ statsFile.set(p.getUniqueId().toString()+".fatPerk", type); }

    public boolean getExcavatorPerk(OfflinePlayer p){ return statsFile.getBoolean(p.getUniqueId().toString()+".excavatorPerk"); }
    public void setExcavatorPerk(OfflinePlayer p, boolean type){ statsFile.set(p.getUniqueId().toString()+".excavatorPerk", type); }

    public boolean getArcherPerk(OfflinePlayer p){ return statsFile.getBoolean(p.getUniqueId().toString()+".archerPerk"); }
    public void setArcherPerk(OfflinePlayer p, boolean type){ statsFile.set(p.getUniqueId().toString()+".archerPerk", type); }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
