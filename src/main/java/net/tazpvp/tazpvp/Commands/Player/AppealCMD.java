package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Utils.DiscordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class AppealCMD {

    @CommandHook("appeal")
    public void appeal(Player p){
        p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        p.sendMessage(ChatColor.AQUA + "If you would like to be un-muted or un-banned, please follow the steps below:");
        p.sendMessage(ChatColor.DARK_AQUA + "Contact support by going to the " + ChatColor.WHITE + "#support " + ChatColor.DARK_AQUA + "channel of our discord server.");
        p.sendMessage(ChatColor.DARK_AQUA + "False punishments will be quickly revoked; cheaters, however, will stay punished but can appeal 24 hours later.");
        p.spigot().sendMessage(DiscordUtils.discordInvite("Click Here to appeal."));
        p.sendMessage(ChatColor.DARK_GRAY + "▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

    }
}
