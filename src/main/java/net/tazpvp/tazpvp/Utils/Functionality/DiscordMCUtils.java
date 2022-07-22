package net.tazpvp.tazpvp.Utils.Functionality;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DiscordMCUtils {
    public static void sendMessageToMC(String Message, Member member) {
        Role highestRole = member.getRoles().get(0);
        String rank = highestRole.getName();
        String name = member.getEffectiveName();
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "DISCORD " + ChatColor.GRAY + "" + ChatColor.ITALIC + rank + " " + ChatColor.GRAY + name + " " + ChatColor.WHITE + Message);
    }

    public static void sendMessageToDiscord(String message, Player p) {
        String prefix = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(p)));
        prefix = prefix.length() > 0 ? "***" + prefix + "***" : "";
        JDA jda = Tazpvp.jda;
        TextChannel channel  = jda.getTextChannelById(997521265478864916L);
        message = discordFilter(message);
        String name = discordNameFilter(p.getName());
        channel.sendMessage(prefix + " **" + name + "** " + message).queue();
    }

    private static String discordFilter(final String message) {
        return message
                .replace("@","")
                .replace("<","")
                .replace(">","")
                .replace("https://", "")
                .replace("http://", "")
                .replace(".", "");
    }
    private static String discordNameFilter(final String message) {
        return message
                .replace("*","\\*")
                .replace("_","\\_")
                .replace("|","\\|");
    }
}
