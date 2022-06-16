package net.tazpvp.tazpvp.Guilds;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GuildManager {
    public final FileConfiguration guildFile;
    final File file;

    public GuildManager() {
        file = new File(Tazpvp.getInstance().getDataFolder(), "/guilds.yml");
        guildFile = YamlConfiguration.loadConfiguration(file);
    }

    public void saveStats(){
        try {
            guildFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addGuild(Guild guild) {
        guildFile.set("guilds.guild-list." + guild.getID(), SerializeGuild.GuildToString(guild));
    }

    public Guild getGuild(UUID id) {
        return SerializeGuild.StringToGuild(guildFile.getString("guilds.guild-list." + id));
    }

    public void setGuild(UUID id, Guild guild) {
        guildFile.set("guilds.guild-list." + id, SerializeGuild.GuildToString(guild));
    }

    public void removeGuild(UUID id) {
        guildFile.set("guilds.guild-list." + id, null);
    }

    public List<UUID> getAllGuilds() {
        Set<String> guilds = guildFile.getConfigurationSection("guilds.guild-list").getKeys(false);
        List<UUID> guildIDs = new ArrayList<>();
        for (String guild : guilds) {
            guildIDs.add(UUID.fromString(guild));
        }
        return guildIDs;
    }

    public List<String> getTakeNames() {
        return guildFile.getStringList("guilds.take-names");
    }

    public void addTakeName(String name) {
        List<String> takeNames = guildFile.getStringList("guilds.take-names");
        takeNames.add(name);
        guildFile.set("guilds.take-names", takeNames);
    }

    public void removeTakeName(String name) {
        List<String> takeNames = guildFile.getStringList("guilds.take-names");
        takeNames.remove(name);
        guildFile.set("guilds.take-names", takeNames);
    }

    public void setPlayerGuild(OfflinePlayer p, UUID guild) {
        guildFile.set("players." + p.getUniqueId().toString() + ".guild", guild.toString());
    }

    public UUID getPlayerGuild(OfflinePlayer p) {
        if (guildFile.contains("players." + p.getUniqueId().toString() + ".guild")) {
            return UUID.fromString(guildFile.getString("players." + p.getUniqueId().toString() + ".guild"));
        } else {
            return null;
        }
    }

    public void removePlayerGuild(OfflinePlayer p) {
        guildFile.set("players." + p.getUniqueId().toString() + ".guild", null);
    }

    public void addTakenTag(String tag) {
        List<String> takemTag = guildFile.getStringList("guilds.take-tags");
        takemTag.add(tag);
        guildFile.set("guilds.take-tags", takemTag);
    }

    public void removeTakenTag(String tag) {
        List<String> takemTag = guildFile.getStringList("guilds.take-tags");
        takemTag.remove(tag);
        guildFile.set("guilds.take-tags", takemTag);
    }
    public List<String> getTakenTags() {
        return guildFile.getStringList("guilds.take-tags");
    }
}
