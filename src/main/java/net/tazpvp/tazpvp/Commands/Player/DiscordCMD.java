package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Utils.DiscordUtils;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class DiscordCMD implements CommandListener {
    @CommandHook("discord")
    public void discord(Player p){
        p.sendMessage("");
        p.spigot().sendMessage(DiscordUtils.discordInvite("Click to join the discord!"));
        p.sendMessage("");
    }
}
