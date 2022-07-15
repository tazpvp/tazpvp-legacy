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
        String prefix = ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(p));
        JDA jda = Tazpvp.jda;
        TextChannel channel  = jda.getTextChannelById(997521265478864916L);
        channel.sendMessage("***" + prefix + "*** **" + p.getName() + "** " + message).queue();
    }
}
