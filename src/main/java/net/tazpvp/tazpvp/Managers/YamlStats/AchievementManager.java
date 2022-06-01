package net.tazpvp.tazpvp.Managers.YamlStats;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AchievementManager {
    public final FileConfiguration statsFile;
    final File file;

    public AchievementManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/ach.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPlayer(OfflinePlayer player){
        statsFile.set(player.getUniqueId().toString()+".sendchatmessage", false);
    }

    public Boolean getSendChatMessage(OfflinePlayer player){
        return statsFile.getBoolean(player.getUniqueId().toString()+".sendchatmessage");
    }
    public void setChatMessage(OfflinePlayer player, Boolean val){
        statsFile.set(player.getUniqueId().toString()+".sendchatmessage", val);
    }
}
