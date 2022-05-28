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

        List<OptionData> options = new ArrayList<OptionData>();
        options.add(new OptionData(OptionType.USER, "user", "ping someone in the process!").setRequired(false));

        this.options = options;
    }

    @Override
    protected void execute(SlashCommandEvent e) {
        String user = e.getOption("user").getAsString();

        e.reply("Pong! " + (user.equalsIgnoreCase("") ? "" : "to <@" + user + ">")).queue();
    }

}
