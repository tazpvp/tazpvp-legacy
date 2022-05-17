package net.tazpvp.tazpvp.Managers.SavingSwords;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SwordManager {
    public final FileConfiguration statsFile;
    final File file;
    List<Items> itemList = new ArrayList<>();

    public SwordManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/swords.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Items> getSwords(OfflinePlayer p) {
        return SerializeSwords.stringToList(statsFile.getString(p.getUniqueId().toString()+".swordList"));
    }
    public void removeSword(OfflinePlayer p, Items sword) {
        List<Items> swords = getSwords(p);
        swords.remove(sword);
    }
    public void addSword(OfflinePlayer p, Items sword) {
        List<Items> swords = getSwords(p);
        swords.add(sword);
    }
    public void setSwords(OfflinePlayer p, List<Items> swords) {
        statsFile.set(p.getUniqueId().toString()+".swordList", SerializeSwords.listToString(swords));
    }
    public void wipeSwords(OfflinePlayer p) {
        statsFile.set(p.getUniqueId().toString()+".swordList", null);
    }

    public static <T> T getOrDefault(FileConfiguration file, String path, T defaultReturn) {
        if(file.contains(path) && file.get(path) != null) {
            return (T) file.get(path);
        }
        return defaultReturn;
    }
}
