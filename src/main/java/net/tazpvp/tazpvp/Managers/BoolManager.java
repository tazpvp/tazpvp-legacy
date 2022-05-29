package net.tazpvp.tazpvp.Managers;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BoolManager {
    public final FileConfiguration statsFile;
    final File file;

    public BoolManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/stats.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void initBool(OfflinePlayer p){
        statsFile.set(p.getUniqueId().toString()+".hasClickedMiner", false);
    }

    public boolean getHasClickedMiner(OfflinePlayer p){ return statsFile.getBoolean(p.getUniqueId().toString()+".hasClickedMiner"); }
    public void setHasClickedMiner(OfflinePlayer p, boolean type){ statsFile.set(p.getUniqueId().toString()+".hasClickedMiner", type); }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
