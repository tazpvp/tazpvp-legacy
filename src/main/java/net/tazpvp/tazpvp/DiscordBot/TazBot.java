package net.tazpvp.tazpvp.DiscordBot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.tazpvp.tazpvp.DiscordBot.Commands.Slash.LeaderBoardCMD;
import net.tazpvp.tazpvp.DiscordBot.Commands.Slash.StatsSCMD;
import net.tazpvp.tazpvp.DiscordBot.Commands.Slash.ping;
import net.tazpvp.tazpvp.DiscordBot.Events.MessageListenerAdapter;
import net.tazpvp.tazpvp.Tazpvp;

import javax.security.auth.login.LoginException;

public class TazBot {
    public static TextChannel channel;
    public static void main(String[] args) throws LoginException, InterruptedException {
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.addSlashCommands(new ping(), new StatsSCMD(), new LeaderBoardCMD());
        builder.forceGuildOnly("535281648980459550");
        builder.setServerInvite("https://discord.gg/56rdkbSqa8");
        builder.setOwnerId("811580599068262421");
        builder.setCoOwnerIds(348587937144897537L);
        builder.setPrefix("/");
        builder.useHelpBuilder(false);

        builder.setActivity(Activity.watching("idiots play block games"));
        CommandClient commandClient = builder.build();

        JDA jda = JDABuilder.createDefault(Tazpvp.getInstance().config.getString("bot-token"))
                .disableCache(CacheFlag.VOICE_STATE)
                .setBulkDeleteSplittingEnabled(false)
                .setChunkingFilter(ChunkingFilter.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .addEventListeners(
                        commandClient,
                        new MessageListenerAdapter())
                .build();
        Tazpvp.jda = jda;
        jda.awaitReady();
        CommandListUpdateAction commandListUpdateAction = jda.updateCommands();
        commandListUpdateAction.queue();
    }
}
