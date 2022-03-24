package net.tazpvp.tazpvp.Commands.Player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class RulesCMD {
    @CommandHook("rules")
    public void rules(Player p) {
        p.sendMessage(ChatColor.WHITE + "\n" +
                ChatColor.WHITE + "1. Be respectful of others\n" +
                ChatColor.GRAY + "Do not disrespect, impersonate, nor harass other server members. Usage of excessive toxicity and hate speech is prohibited. Do not threaten to Dox, DDoS, Swat or IP grab players.\n" +
                ChatColor.GRAY + "\n" +
                ChatColor.WHITE + "2. No advertising or self-promotion.\n" +
                ChatColor.GRAY + "Do not post any external discord invites, servers, YouTube channels, or Twitch channels.\n" +
                ChatColor.GRAY + "\n" +
                ChatColor.WHITE + "3. No cheating\n" +
                ChatColor.GRAY + "Do not use mods, hacks, or clients that give you an advantage over others. Do not abuse glitches and bugs in the server.\n" +
                ChatColor.GRAY + "\n" +
                ChatColor.WHITE + "4. Do not disrupt the server\n" +
                ChatColor.GRAY + "Do not attempt to lag or crash the server. Do not trade in-game items for real money. No repeated or excessive messages.\n" +
                ChatColor.GRAY + "\n" +
                ChatColor.WHITE + "5. Do not attempt to bot/boost yourself\n" +
                ChatColor.GRAY + "Using bots to gain a higher ranking in the game is not allowed. You have to gain stats legitimately."
        );
    }
}
