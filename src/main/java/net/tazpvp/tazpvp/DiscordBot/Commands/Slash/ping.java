package net.tazpvp.tazpvp.DiscordBot.Commands.Slash;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

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
