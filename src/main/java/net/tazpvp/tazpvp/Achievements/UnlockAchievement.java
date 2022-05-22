package net.tazpvp.tazpvp.Achievements;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;

public class UnlockAchievement {
    public static void unlockMSG(Player p, Achievements a, String Reward) {
        p.sendMessage("Unlocked Achievement: " + a.getName());
        p.sendMessage("Reward: " + Reward);
    }

    public static void unlockSendChatMessage(Player p) {
        if (Tazpvp.achievementManager.getSendChatMessage(p)) {
            unlockMSG(p, Achievements.SENDCHATMESSAGE, "+3 moneys");
            Tazpvp.statsManager.addMoney(p, 3);
        }
    }
}
