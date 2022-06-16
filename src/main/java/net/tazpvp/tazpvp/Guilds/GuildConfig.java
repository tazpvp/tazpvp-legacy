package net.tazpvp.tazpvp.Guilds;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GuildConfig {
    public final FileConfiguration guildConfig;
    final File file;

    public GuildConfig() {
        file = new File(Tazpvp.getInstance().getDataFolder(), "/guilds-config.yml");
        guildConfig = YamlConfiguration.loadConfiguration(file);
    }

    public void setDefaults() {
        guildConfig.addDefault("bad-words.", "fuck");
    }
}
