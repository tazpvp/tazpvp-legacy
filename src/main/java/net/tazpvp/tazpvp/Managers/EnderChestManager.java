package net.tazpvp.tazpvp.Managers;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.IA.ItemStackUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class EnderChestManager {
    public final FileConfiguration statsFile;
    final File file;

    public EnderChestManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/ec.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ItemStack[] getItemContents(OfflinePlayer p){
        return ItemStackUtils.deserialize(statsFile.getString(p.getUniqueId().toString()+".ec"));
    }
    public void setItemContents(OfflinePlayer p, ItemStack[] contents) {
        statsFile.set(p.getUniqueId().toString()+".ec", ItemStackUtils.serialize(contents));
    }
}
