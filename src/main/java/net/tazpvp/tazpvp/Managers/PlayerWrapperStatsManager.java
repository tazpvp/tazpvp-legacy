package net.tazpvp.tazpvp.Managers;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerWrapperStatsManager {
    public final FileConfiguration statsFile;
    final File file;

    public PlayerWrapperStatsManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/pw.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerWrapper getPlayerWrapper(OfflinePlayer p) {
        return SerializePlayerWrapper.stringToPlayerWrapper(statsFile.getString(p.getUniqueId().toString()+".pw"));
    }
    public void setPlayerWrapper(OfflinePlayer p, PlayerWrapper pw) {
        statsFile.set(p.getUniqueId().toString()+".pw", SerializePlayerWrapper.PlayerWrapperToString(pw));
    }
    public void wipeSwords(OfflinePlayer p) {
        statsFile.set(p.getUniqueId().toString()+".pw", null);
    }

    public static <T> T getOrDefault(FileConfiguration file, String path, T defaultReturn) {
        if(file.contains(path) && file.get(path) != null) {
            return (T) file.get(path);
        }
        return defaultReturn;
    }
}
