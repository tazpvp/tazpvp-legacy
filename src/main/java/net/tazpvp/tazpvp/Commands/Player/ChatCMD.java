package net.tazpvp.tazpvp.Commands.Player;

import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.ChatEnum;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ChatCMD {
    @CommandHook("chat_staff") public void onChatStaff(Player p) {
        p.sendMessage(ChatColor.AQUA + "Switched to staff chat!");
        Tazpvp.chatEnum.remove(p.getUniqueId());
        Tazpvp.chatEnum.put(p.getUniqueId(), ChatEnum.STAFF);
    }

    @CommandHook("chat_guild") public void onChatGuild(Player p) {
        if (GuildUtils.isInGuild(p)) {
            p.sendMessage(ChatColor.GREEN + "Switched to guild chat!");
            Tazpvp.chatEnum.remove(p.getUniqueId());
            Tazpvp.chatEnum.put(p.getUniqueId(), ChatEnum.GUILD);
        } else {
            p.sendMessage(ChatColor.RED + "You are not in a guild!");
        }
    }

    @CommandHook("chat_all") public void onChatAll(Player p) {
        p.sendMessage(ChatColor.YELLOW + "Switched to all chat!");
        Tazpvp.chatEnum.remove(p.getUniqueId());
        Tazpvp.chatEnum.put(p.getUniqueId(), ChatEnum.ALL);
    }
}
