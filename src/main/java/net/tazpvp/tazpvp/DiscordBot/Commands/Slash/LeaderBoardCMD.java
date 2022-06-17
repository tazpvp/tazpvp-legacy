package net.tazpvp.tazpvp.DiscordBot.Commands.Slash;

import com.jagrosh.jdautilities.command.CooldownScope;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.Sortation;
import org.bukkit.Bukkit;

import java.awt.*;
import java.util.List;
import java.util.*;

public class LeaderBoardCMD extends SlashCommand {
    public LeaderBoardCMD() {
        this.name = "leaderboard";
        this.help = "Gets the top 10 players!";
        this.aliases = new String[]{"lb"};
        this.cooldown = 10;
        this.cooldownScope = CooldownScope.USER;
        this.children = new SlashCommand[] {new KillLB(), new DeathLB(), new LevelLB(), new CoinsLB(), new ShardsLB()};
    }

    @Override
    public void execute(SlashCommandEvent event) { event.reply("You can use the sub commands to calculate the power.").queue(); }

    private static class KillLB extends SlashCommand {
        public KillLB() {
            this.name = "kills";
            this.help = "Gets the top 10 players sorted by kills!";
            this.cooldown = 10;
            this.cooldownScope = CooldownScope.USER;
            this.children = new SlashCommand[] {};
        }

        @Override
        public void execute(SlashCommandEvent event) {
            HashMap<UUID, Double> playerKills = new HashMap<>();
            for (UUID player : playerUUIDS()) {
                playerKills.put(player, (double) Tazpvp.statsManager.getKills(player));
            }
            LinkedHashMap<UUID, Double> sorted = Sortation.sortByValue(playerKills);
            makeEmbed(event, "Kills Leaderboard", sorted);
        }
    }

    private static class DeathLB extends SlashCommand {
        public DeathLB() {
            this.name = "deaths";
            this.help = "Gets the top 10 players sorted by Deaths!";
            this.cooldown = 10;
            this.cooldownScope = CooldownScope.USER;
            this.children = new SlashCommand[] {};
        }

        @Override
        public void execute(SlashCommandEvent event) {
            HashMap<UUID, Double> playerDeaths = new HashMap<>();
            for (UUID player : playerUUIDS()) {
                playerDeaths.put(player, (double) Tazpvp.statsManager.getDeaths(player));
            }
            LinkedHashMap<UUID, Double> sorted = Sortation.sortByValue(playerDeaths);
            makeEmbed(event, "Deaths Leaderboard", sorted);
        }
    }

    private static class CoinsLB extends SlashCommand {
        public CoinsLB() {
            this.name = "coins";
            this.help = "Gets the top 10 players sorted by Coins!";
            this.cooldown = 10;
            this.cooldownScope = CooldownScope.USER;
            this.children = new SlashCommand[] {};
        }

        @Override
        public void execute(SlashCommandEvent event) {
            HashMap<UUID, Double> coinAmount = new HashMap<>();
            for (UUID player : playerUUIDS()) {
                coinAmount.put(player, (double) Tazpvp.statsManager.getCoins(player));
            }
            LinkedHashMap<UUID, Double> sorted = Sortation.sortByValue(coinAmount);
            makeEmbed(event, "Coins Leaderboard", sorted);
        }
    }

    private static class ShardsLB extends SlashCommand {
        public ShardsLB() {
            this.name = "shards";
            this.help = "Gets the top 10 players sorted by Shards!";
            this.cooldown = 10;
            this.cooldownScope = CooldownScope.USER;
            this.children = new SlashCommand[] {};
        }

        @Override
        public void execute(SlashCommandEvent event) {
            HashMap<UUID, Double> playerShards = new HashMap<>();
            for (UUID player : playerUUIDS()) {
                playerShards.put(player, (double) Tazpvp.statsManager.getShards(player));
            }
            LinkedHashMap<UUID, Double> sorted = Sortation.sortByValue(playerShards);
            makeEmbed(event, "Shards Leaderboard", sorted);
        }
    }

    private static class LevelLB extends SlashCommand {
        public LevelLB() {
            this.name = "levels";
            this.help = "Gets the top 10 players sorted by Levels!";
            this.cooldown = 10;
            this.cooldownScope = CooldownScope.USER;
            this.children = new SlashCommand[] {};
        }

        @Override
        public void execute(SlashCommandEvent event) {
            HashMap<UUID, Double> playerLevel = new HashMap<>();
            for (UUID player : playerUUIDS()) {
                playerLevel.put(player, (double) Tazpvp.statsManager.getLevel(player));
            }
            LinkedHashMap<UUID, Double> sorted = Sortation.sortByValue(playerLevel);
            makeEmbed(event, "Level Leaderboard", sorted);
        }
    }

    public static void makeEmbed(SlashCommandEvent e, String title, LinkedHashMap<UUID, Double> data) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(title, null);
        embed.setColor(Color.RED);

        StringBuilder list = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            if (i >= data.size()) {
                break;
            }
            int placement = i + 1;
            UUID uuid = data.keySet().stream().toList().get(i);
            Double value = data.get(uuid);
            list.append("**" + placement + "〉**" + Bukkit.getOfflinePlayer(uuid).getName() + " - " + value + "\n");
        }

        embed.setDescription("━━━━━━━━━━━━━━\n" + list.toString() + "━━━━━━━━━━━━━━");

        e.replyEmbeds(embed.build()).queue();
    }

    public static List<UUID> playerUUIDS() {
        Set<String> players = Tazpvp.statsManager.statsFile.getConfigurationSection("").getKeys(false);
        List<UUID> playerUUIDS = new LinkedList<>();
        for (String player : players) {
            playerUUIDS.add(UUID.fromString(player));
        }
        return playerUUIDS;
    }
}
