package net.tazpvp.tazpvp.DiscordBot.Events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Channel;
import net.tazpvp.tazpvp.DiscordBot.TazBot;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class SendBanNotification {
    public static void sendBanNotification(UUID uuid, Player plr) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        EmbedBuilder banEmbed = new EmbedBuilder();
        banEmbed.setTitle("**" + p.getName() + "** has been banned!");
        banEmbed.setDescription("**Time:** " + dtf.format(now));
        banEmbed.setColor(Color.RED);
        banEmbed.setThumbnail("https://mc-heads.net/avatar/" + uuid.toString() + "/64");
        banEmbed.setFooter("they better have been hacking");

        banEmbed.addField("**Reason**", Tazpvp.punishmentManager.getBanReason(p), true);
        banEmbed.addField("**Banned By**", plr.getName(), true);


        TazBot.jda.getTextChannelById(981276321705496626L).sendMessageEmbeds(banEmbed.build()).queue();
    }

    public static void sendUnBanNotification(UUID uuid, Player plr) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        EmbedBuilder banEmbed = new EmbedBuilder();
        banEmbed.setTitle("**" + p.getName() + "** has been unbanned!");
        banEmbed.setDescription("**Time:** " + dtf.format(now));
        banEmbed.setColor(Color.GREEN);
        banEmbed.setThumbnail("https://mc-heads.net/avatar/" + uuid.toString() + "/64");
        banEmbed.setFooter("here we go again");

        banEmbed.addField("**Unbanned By**", plr.getName(), true);


        TazBot.jda.getTextChannelById(981276321705496626L).sendMessageEmbeds(banEmbed.build()).queue();
    }
}
