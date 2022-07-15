package net.tazpvp.tazpvp.Utils.Functionality;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class HideUtil {
    public static void hide(Player p) {
        Tazpvp.hidden.add(p.getUniqueId());
        String randomName = ChatColor.GRAY + randomName();
        p.setCustomName(randomName);
        p.setDisplayName(randomName);
        p.setPlayerListName(randomName);
        Tazpvp.getInstance().addPlayerToOnlinePlayersSB(p);
        Tazpvp.getInstance().addOnlinePlayersToSB(p);
    }

    public static void unhide(Player p) {
        Tazpvp.hidden.remove(p.getUniqueId());
        p.setCustomName(null);
        p.setDisplayName(p.getName());
        p.setPlayerListName(p.getName());
        Tazpvp.getInstance().addPlayerToOnlinePlayersSB(p);
        Tazpvp.getInstance().addOnlinePlayersToSB(p);
    }

    public static String randomName() {
        Random rand = new Random();
        List<String> names = List.of("AssaultIwant",
                "AuraLand",
                "BroodFly",
                "Cathysi",
                "Chevence",
                "Controv",
                "Deepext",
                "Fireca",
                "GuyNews",
                "HeartShadow",
                "Hugzho",
                "Interviewondp",
                "JollyChamp",
                "MaxiSand",
                "Mediati",
                "Parkvelg",
                "Pastele",
                "Rampace",
                "RatSlayer",
                "RelyIssue",
                "Rhoughts",
                "RobSumo",
                "RosaHip",
                "Shinyna",
                "Shortco",
                "Spooneg",
                "ThegaTopic",
                "Trackergi",
                "Unll");
        return names.get(rand.nextInt(names.size()));
    }
}
