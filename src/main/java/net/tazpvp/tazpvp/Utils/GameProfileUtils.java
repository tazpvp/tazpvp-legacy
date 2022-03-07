package net.tazpvp.tazpvp.Utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.minecraft.server.level.EntityPlayer;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class GameProfileUtils {
    public void gp(Player p){
        EntityPlayer ep = ((CraftPlayer)p).getHandle();
        GameProfile gp = ep.fp();

        PropertyMap pm = gp.getProperties();

        Property property = pm.get("textures").iterator().next();

        String base64 = property.getValue();
        String decoded = new String(Base64.decodeBase64(base64));

    }
}
