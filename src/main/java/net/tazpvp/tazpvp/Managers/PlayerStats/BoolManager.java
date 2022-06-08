package net.tazpvp.tazpvp.Managers.PlayerStats;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class BoolManager {
    public final FileConfiguration statsFile;
    final File file;

    public BoolManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/bool.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initBool(OfflinePlayer p){
        statsFile.set(p.getUniqueId().toString()+".hasRebirthed", false);
    }

    public boolean getHasRebirthed(OfflinePlayer p){ return statsFile.getBoolean(p.getUniqueId().toString()+".hasRebirthed"); }
    public void setHasRebirthed(OfflinePlayer p, boolean type){ statsFile.set(p.getUniqueId().toString()+".hasRebirthed", type); }





}
