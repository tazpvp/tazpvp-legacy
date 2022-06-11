package net.tazpvp.tazpvp.Utils.NPCS;

import net.tazpvp.tazpvp.GUI.NPCGui.DepthsGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RigelNPC {
    private static List<Player> clickedOnce = new ArrayList<>();

    public void clickRigel(Player p){
        if (p.getWorld().equals(Bukkit.getWorld("arena"))){
            if (clickedOnce.contains(p)){
                p.sendMessage(ChatColor.DARK_PURPLE + "[NPC] Rigel " + ChatColor.LIGHT_PURPLE + "Alright, here you go.");
                p.teleport(new Location(Bukkit.getWorld("ban"), 0.5, 70, 0.5, 0, 0));
                p.sendTitle(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "THE DEPTHS", ChatColor.LIGHT_PURPLE + "Look alive.", 10, 100, 10);
                p.playSound(p.getLocation(), Sound.AMBIENT_CAVE, 1, 1);
                clickedOnce.remove(p);
            } else {
                p.sendMessage(ChatColor.DARK_PURPLE + "[NPC] Rigel " + ChatColor.LIGHT_PURPLE + "Although this land allows you to improve greatly, The Depths is a dangerous place full of elite enemies. Are you sure?");
                clickedOnce.add(p);
            }
        } else if (p.getWorld().equals(Bukkit.getWorld("ban"))) {
            new DepthsGUI(p);
        }
    }
}
