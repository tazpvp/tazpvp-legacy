package net.tazpvp.tazpvp.Guilds;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class SerializeGuild {
    public static String GuildToString(Guild guild) {
        try {
            ByteArrayOutputStream str = new ByteArrayOutputStream();
            BukkitObjectOutputStream data = new BukkitObjectOutputStream(str);
            data.writeObject(guild);
            data.close();
            return Base64.getEncoder().encodeToString(str.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Guild StringToGuild(String guildData) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(guildData));
            BukkitObjectInputStream data = new BukkitObjectInputStream(stream);
            Guild guild = (Guild) data.readObject();
            data.close();
            return guild;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
