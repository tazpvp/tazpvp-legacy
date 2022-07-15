package net.tazpvp.tazpvp.DiscordBot.Events;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.tazpvp.tazpvp.Utils.Functionality.DiscordMCUtils;

public class MessageListenerAdapter extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();
        String msg = message.getContentDisplay();

        if (author.isBot()) return;

        if (event.isFromType(ChannelType.TEXT)) {
            Guild guild = event.getGuild();

            if (channel.getId().equals("997521265478864916")) {
                Member member = event.getMember();
                DiscordMCUtils.sendMessageToMC(msg, member);
            }
        }
    }
}
