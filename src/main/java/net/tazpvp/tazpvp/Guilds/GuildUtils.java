package net.tazpvp.tazpvp.Guilds;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuildUtils {
    public final static String gNameTaken = "Guild name already taken.";
    public final static String alrdyInG = "You are already in a guild.";
    public final static String gCreated = "Guild created.";
    public final static String notInG = "You are not in a guild.";
    public final static String noPermission = "You do not have permission to do that.";
    public final static ChatColor primaryColor = ChatColor.WHITE;
    public final static ChatColor secondaryColor = ChatColor.GRAY;

    /**
     * Creates a guild
     * @param p the player who created the guld
     * @param name the name of the guild, will accept {@code String[]}
     * @param description the description of the guild
     */
    public static void createGuild(Player p, String name, String description) {
        if (isInGuild(p)) {
            p.sendMessage(alrdyInG);
            return;
        }
        if (Tazpvp.guildManager.getTakeNames().contains(name)) {
            p.sendMessage(gNameTaken);
            return;
        }

        Guild guild = new Guild(name, null, description, p.getUniqueId());
        Tazpvp.guildManager.addGuild(guild);
        Tazpvp.guildManager.setPlayerGuild(p, guild.getID());
        Tazpvp.guildManager.addTakeName(name);
        p.sendMessage(gCreated);
    }

    /**
     * Gets information about specified guild
     * @param guild The Guild object
     * @return A {@code String} containing the guild information
     */
    public static List<String> viewGuild(Guild guild) {
        return guildReturn(guild);
    }

    /**
     * Gets information about specified guild
     * @param gUUID The UUID of the guild
     * @return A {@code String} containing the guild information
     */
    public static List<String> viewGuild(UUID gUUID) {
        return guildReturn(Tazpvp.guildManager.getGuild(gUUID));
    }

    private static List<String> guildReturn(Guild guild) {
        List<String> guildInfo = new ArrayList<>();
        guildInfo.add(secondaryColor + guild.description());
        guildInfo.add(primaryColor + "-" + uuidToOfflinePlayer(guild.owner().get(0)).getName());
        guildInfo.add(secondaryColor + "KDR: " + primaryColor + guild.getKDR());
        guildInfo.add(secondaryColor + "Members: " + primaryColor + guild.allMembers().size());
        return guildInfo;
    }

    public static OfflinePlayer uuidToOfflinePlayer(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid);
    }

    /**
     * Checks if player is in a guild
     * @param p The player
     * @return {@code true} if player is in a guild, {@code false} if not
     */
    public static boolean isInGuild(Player p) {
        return Tazpvp.guildManager.getPlayerGuild(p) != null;
    }

    public static void leaveGuild(Player p, Guild guild) {
        if (!isInGuild(p)) {
            p.sendMessage(notInG);
            return;
        }
        guild.removeFromGuild(p.getUniqueId());
        Tazpvp.guildManager.removePlayerGuild(p);
        if (guild.getGuildCount() == 0) {
            Tazpvp.guildManager.removeGuild(guild.getID());
            Tazpvp.guildManager.removeTakeName(guild.name());
            return;
        }
        if (guild.owner().size() == 0) {
            guild.promote(p, (guild.staff().size()  > 0) ? guild.staff().get(0) : guild.allMembers().get(0));
        }
        Tazpvp.guildManager.setGuild(guild.getID(), guild);
    }

    public static void kickFromGuild(Player p, Player target, Guild guild) {
        if (!guild.staff().contains(p.getUniqueId()) || !guild.owner().contains(p.getUniqueId())) {
            p.sendMessage(noPermission);
            return;
        }


    }
}
