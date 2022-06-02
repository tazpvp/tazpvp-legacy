package net.tazpvp.tazpvp.Duels.WorldUtils;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;

public class WorldManageent {
    public World cloneWorld(String backup, String target) {
        File srcDir = new File(Bukkit.getServer().getWorldContainer(), backup);
        if (!srcDir.exists()) {
            Bukkit.getLogger().warning("World does not exist!");
            return null;
        }
        String dee = target;
        File destDir = new File(Bukkit.getServer().getWorldContainer(), dee);
        try {
            FileUtils.copyDirectory(srcDir, destDir);
            for (File file : destDir.listFiles())
                if (file.isFile())
                    if (file.getName().equalsIgnoreCase("uid.dat"))
                        file.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Bukkit.getServer().createWorld(new WorldCreator(dee));
        return Bukkit.getServer().getWorld(dee);
    }

    public void deleteWorld(String worldName) {
        World w = Bukkit.getWorld(worldName);
        File srcDir = new File(Bukkit.getServer().getWorldContainer(), worldName);

        Bukkit.unloadWorld(w, false);
        try {
            FileUtils.deleteDirectory(srcDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
