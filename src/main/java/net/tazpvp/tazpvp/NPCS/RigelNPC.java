package net.tazpvp.tazpvp.NPCS;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RigelNPC {
    private static List<Player> clickedOnce = new ArrayList<>();

    public static void clickRigel(Player p){
        if (p.getWorld().equals(Bukkit.getWorld("arena"))){
            if (clickedOnce.contains(p)){
                p.sendMessage("good luck buddy");
            } else {
                p.sendMessage("Are you sure you would like to travel the depths?");
            }
        }
    }
}
