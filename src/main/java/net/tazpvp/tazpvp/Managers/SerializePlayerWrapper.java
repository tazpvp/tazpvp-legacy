package net.tazpvp.tazpvp.Managers;

import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

public class SerializePlayerWrapper {
    public static String PlayerWrapperToString(PlayerWrapper pw) {
        try {
            ByteArrayOutputStream str = new ByteArrayOutputStream();
            BukkitObjectOutputStream data = new BukkitObjectOutputStream(str);
            data.writeObject(pw);
            data.close();
            return Base64.getEncoder().encodeToString(str.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static PlayerWrapper stringToPlayerWrapper(String pwData) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(pwData));
            BukkitObjectInputStream data = new BukkitObjectInputStream(stream);
            PlayerWrapper pw = (PlayerWrapper) data.readObject();
            data.close();
            return pw;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
