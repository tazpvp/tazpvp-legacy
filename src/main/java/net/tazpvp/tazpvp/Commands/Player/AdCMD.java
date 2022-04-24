package net.tazpvp.tazpvp.Commands.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class AdCMD {
    final String ad = "/ad Tazpvp &f/ &c1.8 &f/ &cDuels &f/ &cGrind &f/ &cFFA";

    @CommandHook("ad")
    public void ad(Player p){
        TextComponent copy = new TextComponent("\n" + ChatColor.YELLOW + "  Click " + ChatColor.GOLD + "" + ChatColor.BOLD + "Here" + ChatColor.YELLOW + " to copy the advertisement for this server.\n");
        copy.setClickEvent(new net.md_5.bungee.api.chat.ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ad));
        p.spigot().sendMessage(copy);

    }
}
