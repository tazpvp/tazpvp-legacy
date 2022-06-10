package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import redempt.redlib.commandmanager.CommandHook;

public class StaffchatCMD {
    @CommandHook("staffchat")
    public void onStaffChat(Player p, String[] args) {
        if (args.length == 0) {
            if (p.hasMetadata("staffchat")) {
                p.removeMetadata("staffchat", Tazpvp.getInstance());
                p.sendMessage(ChatColor.GREEN + "You are no longer staff chatting.");
            } else {
                p.setMetadata("staffchat", new FixedMetadataValue(Tazpvp.getInstance(), true));
                p.sendMessage(ChatColor.GREEN + "You are now staff chatting.");
            }
            return;
        }
        ChatUtils.sendStaffChatMessage(p, args);
    }

}
