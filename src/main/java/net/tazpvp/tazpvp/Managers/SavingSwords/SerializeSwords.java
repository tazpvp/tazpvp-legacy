package net.tazpvp.tazpvp.Managers.SavingSwords;

import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

public class SerializeSwords {
    public static String listToString(List<Items> swords) {
        try {
            ByteArrayOutputStream str = new ByteArrayOutputStream();
            BukkitObjectOutputStream data = new BukkitObjectOutputStream(str);
            data.writeObject(swords);
            data.close();
            return Base64.getEncoder().encodeToString(str.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static List<Items> stringToList(String listData) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(Base64.getDecoder().decode(listData));
            BukkitObjectInputStream data = new BukkitObjectInputStream(stream);
            List<Items> itemList = (List<Items>) data.readObject();
            data.close();
            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
