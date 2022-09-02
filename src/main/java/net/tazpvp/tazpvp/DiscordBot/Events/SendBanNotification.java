package net.tazpvp.tazpvp.DiscordBot.Events;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Fun.DiscordWebhook;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class SendBanNotification {
    public static void sendBanNotification(UUID uuid, CommandSender plr, String reason) throws IOException {
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        DiscordWebhook webhook = new DiscordWebhook(Tazpvp.getInstance().config.getString("webhook-ban"));

        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("**" + p.getName() + "** has been banned!")
                .setDescription("**Time:** " + dtf.format(now))
                .setColor(Color.RED)
                .setThumbnail("https://mc-heads.net/avatar/" + uuid + "/64")
                .setFooter("they better have been hacking", null)
                .addField("**Reason**", reason + "", true)
                .addField("**Banned By**", plr.getName(), true));

        webhook.execute();
    }

    public static void sendUnBanNotification(UUID uuid, CommandSender plr) throws IOException {
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        DiscordWebhook webhook = new DiscordWebhook(Tazpvp.getInstance().config.getString("webhook-ban"));

        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("**" + p.getName() + "** has been unbanned!")
                .setDescription("**Time:** " + dtf.format(now))
                .setColor(Color.YELLOW)
                .setThumbnail("https://mc-heads.net/avatar/" + uuid + "/64")
                .setFooter("here we go again", null)
                .addField("**Unbanned By**", plr.getName(), true));

        webhook.execute();
    }
}
