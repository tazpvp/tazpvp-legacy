package net.tazpvp.tazpvp.DiscordBot.Commands.Slash;

import com.jagrosh.jdautilities.command.CooldownScope;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class StatsSCMD extends SlashCommand {
    public StatsSCMD() {
        this.name = "stats";
        this.help = "Gets stats about the target player!";

        List<OptionData> options = new ArrayList<>(Arrays.asList(
                new OptionData(OptionType.STRING, "ign", "The name of the player you want to get stats for").setRequired(true)
        ));
        this.options = options;
        this.cooldown = 5;
        this.cooldownScope = CooldownScope.USER;
    }

    @Override
    protected void execute(SlashCommandEvent e) {
        if (e.getOptions().isEmpty()) {
            e.reply("You need to specify a player!").queue();
            return;
        }
        String playerName = e.getOption("ign").getAsString();
        if (PlayerUtils.ignToUUID(playerName) == null || PlayerUtils.ignToUUID(playerName).equals("error")) {
            e.reply("That player doesn't exist!").queue();
            return;
        }
        UUID uuid = UUID.fromString(PlayerUtils.ignToUUID(playerName));
        OfflinePlayer p = Bukkit.getPlayer(uuid);
        if (!p.hasPlayedBefore()) {
            e.reply("That player hasn't played before!").queue();
            return;
        }


        EmbedBuilder stats = new EmbedBuilder();
        stats.setTitle("**" + playerName + "'s stats**");
        stats.setColor(Color.YELLOW);
        stats.setAuthor("TazPVP", null, "https://cdn.discordapp.com/attachments/811666816278724639/980269861806686288/Taznet_Logo_16.png");
        stats.setFooter("By Ntdi and toenox");
        stats.setThumbnail("https://mc-heads.net/avatar/" + uuid.toString() + "/128");

        stats.addField("**Level**", "" + Tazpvp.statsManager.getLevel(p) + "", true);
        stats.addField("**Exp**", "" + Tazpvp.statsManager.getExp(p) + "/" + Tazpvp.statsManager.getExpLeft(p), true);
        stats.addField("**Kills**", "" + Tazpvp.statsManager.getKills(p) + "", true);
        stats.addField("**Deaths**", "" + Tazpvp.statsManager.getDeaths(p) + "", true);
        stats.addField("**Money**", "" + Tazpvp.statsManager.getMoney(p) + "", true);
        stats.addField("**Shards**", "" + Tazpvp.statsManager.getShards(p) + "", true);

        e.replyEmbeds(stats.build()).queue();
    }
}
