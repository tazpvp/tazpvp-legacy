package net.tazpvp.tazpvp.Utils.Functionality;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatUtils {
    public static void sendSurround(Player p, String msg){
        p.sendMessage("", msg, "");
    }
}
