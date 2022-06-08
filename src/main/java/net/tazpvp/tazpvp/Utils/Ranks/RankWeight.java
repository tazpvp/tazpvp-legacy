package net.tazpvp.tazpvp.Utils.Ranks;

import org.bukkit.Color;

public enum RankWeight {
    VIP("VIP", 1, Color.RED),
    MVP("MVP", 2, Color.LIME),
    MVP_P("MVP+", 3, Color.fromRGB(255,215,0));

    private String name;
    private int weight;
    private Color color;
     RankWeight(String rankName, int rankWeight, Color color) {
        this.name = rankName;
        this.weight = rankWeight;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isHigher(String currRank, String newRank) {
        return RankWeight.valueOf(currRank).getWeight() < RankWeight.valueOf(newRank).getWeight();
    }

    public Color getColor() {
        return color;
    }
}
