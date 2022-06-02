package net.tazpvp.tazpvp.Utils.Scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

public class ScoreboardMaker {
    private String title;
    private Scoreboard scoreboard;
    private Objective objective;
    private Score[] scores;

    public ScoreboardMaker(Scoreboard sb) {
        Scoreboard scobo = sb;
        Objective objective = scobo.getObjective("sb");
        assert objective != null;
        objective.unregister();
        objective = scobo.registerNewObjective("sb", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        if (scobo.getObjective("showhealth") == null) {
            Objective h = scobo.registerNewObjective("showhealth", Criterias.HEALTH);
            h.setDisplaySlot(DisplaySlot.BELOW_NAME);
            h.setDisplayName(ChatColor.RED + "❤");
        }
        this.scoreboard = scobo;
        this.objective = objective;
    }
}
