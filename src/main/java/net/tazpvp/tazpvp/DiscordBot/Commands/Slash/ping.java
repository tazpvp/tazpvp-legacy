package net.tazpvp.tazpvp.DiscordBot.Commands.Slash;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class ping extends SlashCommand {
    public ping() {
        this.name = "ping";
        this.help = "Pongs you!";
    }

    @Override
    protected void execute(SlashCommandEvent e) {
        e.reply("Pong!").queue();
    }

}
