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
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        if (PlayerUtils.ignToUUID(playerName).equals("error")) {
            e.reply("That player doesn't exist!").queue();
            return;
        }
        UUID uuid = UUID.fromString(PlayerUtils.ignToUUID(playerName));
        OfflinePlayer p = Bukkit.getOfflinePlayer(uuid);

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        Double d = (Tazpvp.statsManager.getExpLeft(uuid));

        EmbedBuilder stats = new EmbedBuilder();
        stats.setTitle("**" + playerName + "'s stats**");
        stats.setColor(Color.pink);
        stats.setAuthor("TazPVP", null, "https://cdn.discordapp.com/attachments/811666816278724639/980269861806686288/Taznet_Logo_16.png");
        stats.setFooter("tazpvp.net");
        stats.setThumbnail("https://mc-heads.net/avatar/" + uuid.toString() + "/64");

        stats.addField("**Level**", "" + Tazpvp.statsManager.getLevel(uuid) + "", true);
        stats.addField("**Exp**", "" + Tazpvp.statsManager.getExp(uuid) + "/" + df.format(d), true);
        stats.addField("**Kills**", "" + Tazpvp.statsManager.getKills(uuid) + "", true);
        stats.addField("**Deaths**", "" + Tazpvp.statsManager.getDeaths(uuid) + "", true);
        stats.addField("**Money**", "" + Tazpvp.statsManager.getMoney(uuid) + "", true);
        stats.addField("**Shards**", "" + Tazpvp.statsManager.getShards(uuid) + "", true);

        e.replyEmbeds(stats.build()).queue();
    }
}
