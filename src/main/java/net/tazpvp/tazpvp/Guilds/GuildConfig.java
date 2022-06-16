package net.tazpvp.tazpvp.Guilds;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GuildConfig {
    public static void reload() {
        Tazpvp.getInstance().reloadConfig();
        Tazpvp.config = Tazpvp.getInstance().getConfig();
    }

    public static List<String> badWords() {
        return Tazpvp.config.getStringList("bad-words.");
    }

    public static boolean isOffending(String text) {
        String[] words = text.split(" ");
        List<String> noNoWords = badWords();
        for (String s : words) {
            if (noNoWords.contains(s.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }
}
