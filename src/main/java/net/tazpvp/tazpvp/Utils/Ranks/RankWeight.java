package net.tazpvp.tazpvp.Utils.Ranks;

public enum RankWeight {
    VIP("VIP", 1),
    MVP("MVP", 2),
    MVP_P("MVP+", 3);

    private String name;
    private int weight;
     RankWeight(String rankName, int rankWeight) {
        this.name = rankName;
        this.weight = rankWeight;
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
}
