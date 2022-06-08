package net.tazpvp.tazpvp.Managers.PlayerStats;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class AchievementManager {
    public final FileConfiguration statsFile;
    final File file;

    public AchievementManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/achievements.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initAch(OfflinePlayer player){
        statsFile.set(player.getUniqueId().toString()+".hasSentChat", false);
    }

    public void setAchievement(Player p, String s, boolean value) { statsFile.set(p.getUniqueId().toString()+"."+s, value); }
    public boolean getAchievement(Player p, String s) {
        return statsFile.getBoolean(p.getUniqueId().toString()+"."+s);
    }
}
