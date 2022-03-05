package net.tazpvp.tazpvp.unused.NPC;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R1.CraftServer;
import org.bukkit.craftbukkit.v1_18_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;
import java.util.WeakHashMap;

public class NPC {
    private Location npcLocation;
    private EntityPlayer npcPlayer;
    private final GameProfile gameProfile;
    private final String identifier;
    public final WeakHashMap<UUID, Boolean> isLoaded = new WeakHashMap<>();

    public NPC(final String name, final Location location, final String identifier) {
        this.identifier = identifier;
        this.gameProfile = new GameProfile(UUID.randomUUID(), name);
        if(location.isWorldLoaded()) {
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
            WorldServer world = ((CraftWorld) location.getWorld()).getHandle();

            npcPlayer = new EntityPlayer(server, world, gameProfile);
        } else {
            Bukkit.getLogger().warning("NPC: " + identifier + " Failed to load because they were in a unloaded world.");
        }
        setNpcLocation(location);
    }

    public void createNpcForPlayer(Player player) {
        if(getNpcLocation().isWorldLoaded()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
            isLoaded.put(player.getUniqueId(), true);
            String[] skin = getSkinTexture(player.getName());
            if (skin != null) {
                setSkin(player, skin[0], skin[1]);
            }
            connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npcPlayer));

            connection.a(new PacketPlayOutNamedEntitySpawn(npcPlayer));

            //connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npcPlayer));
            new BukkitRunnable() {
                @Override
                public void run() {
                    //if(isLoaded.get(player.getUniqueId()))
                    connection.a(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.e, npcPlayer));
                }
            }.runTaskLater(Tazpvp.getInstance(), 50);
            connection.a(new PacketPlayOutEntityHeadRotation(npcPlayer, (byte) (npcLocation.getYaw() * 256 / 360)));
        } else {
            Bukkit.getLogger().warning("NPC: " + identifier + " Failed to load because they were in a unloaded world.");
        }

    }

    public void removeNpcForPlayer(Player player) {
        if(getNpcLocation().isWorldLoaded()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
            isLoaded.put(player.getUniqueId(), false);
            connection.a(new PacketPlayOutEntityDestroy(npcPlayer.ae()));
        } else {
            Bukkit.getLogger().warning("NPC: " + identifier + " Failed to load because they were in a unloaded world.");
        }
    }

    public void setSkin(Player player, String texture, String signature) {
        if(getNpcLocation().isWorldLoaded()) {
            gameProfile.getProperties().clear();
            gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
            DataWatcher watcher = npcPlayer.ai();
            watcher.b(new DataWatcherObject<>(17, DataWatcherRegistry.a), (byte) 0xFF);
            PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(npcPlayer.ae(), watcher, true);
            ((CraftPlayer) player).getHandle().b.a(packet);
        } else {
            Bukkit.getLogger().warning("NPC: " + identifier + " Failed to load because they were in a unloaded world.");
        }
    }


    public Location getNpcLocation() {
        return npcLocation;
    }

    public void setNpcLocation(Location npcLocation) {
        this.npcLocation = npcLocation;
        if(npcLocation.isWorldLoaded())
            npcPlayer.b(npcLocation.getX(), npcLocation.getY(), npcLocation.getZ(), npcLocation.getYaw(), npcLocation.getPitch());
    }

    public EntityPlayer getNpcPlayer() {
        return npcPlayer;
    }

    public static String[] getSkinTexture(String username) {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(String.format("https://api.ashcon.app/mojang/v2/user/%s", username)).openConnection();
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                ArrayList<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                reader.lines().forEach(lines::add);
                String reply = String.join(" ",lines);
                int indexOfValue = reply.indexOf("\"value\": \"");
                int indexOfSignature = reply.indexOf("\"signature\": \"");
                String texture = reply.substring(indexOfValue + 10, reply.indexOf("\"", indexOfValue + 10));
                String signature = reply.substring(indexOfSignature + 14, reply.indexOf("\"", indexOfSignature + 14));
                return new String[] {texture, signature};
            }
            else {
                Bukkit.getConsoleSender().sendMessage("Error fetching player skin (Response code " + connection.getResponseCode() + ", " + connection.getResponseMessage() + ")");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
