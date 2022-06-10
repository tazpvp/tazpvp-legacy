package net.tazpvp.tazpvp.Utils.Functionality;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {
    public static void sendSurround(Player p, String msg){
        p.sendMessage("", msg, "");
    }

    public static void sendStaffChatMessage(Player p, String[] msg) {
        StringBuilder sb = new StringBuilder();
        for (String s : msg) {
            sb.append(s).append(" ");
        }
        Bukkit.broadcast(ChatColor.YELLOW + "STAFF - " + ChatColor.GOLD + p.getDisplayName() + ChatColor.WHITE + " " + sb, "tazpvp.staffchat");
    }

    public static void sendStaffChatMessage(Player p, String msg) {
        Bukkit.broadcast(ChatColor.YELLOW + "STAFF - " + ChatColor.GOLD + p.getDisplayName() + ChatColor.WHITE + " " + msg, "tazpvp.staffchat");
    }
}
