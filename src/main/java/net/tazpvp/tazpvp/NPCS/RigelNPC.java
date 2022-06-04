package net.tazpvp.tazpvp.NPCS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RigelNPC {
    private static List<Player> clickedOnce = new ArrayList<>();

    public void clickRigel(Player p){
        if (p.getWorld().equals(Bukkit.getWorld("arena"))){
            if (clickedOnce.contains(p)){
                p.sendMessage("good luck buddy");
                clickedOnce.remove(p);
            } else {
                p.sendMessage(ChatColor.DARK_PURPLE + "[NPC] Rigel " + ChatColor.WHITE + "Although this land allows you to rebirth, The Depths is a dangerous place full of god-like enemies. Are you sure? (click again)");
                clickedOnce.add(p);
            }
        }
    }
}
