package net.tazpvp.tazpvp.Achievements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Achievements {
    SENDCHATMESSAGE("Send a chat message!", ".sendchatmessage", new ArrayList<>(Arrays.asList("Become Cool and send a chat messG!"))),
    COLLECTEMALL("Gotta Collect Em' All!", ".collectemall", new ArrayList<>(Arrays.asList("Collect all the swords in the game!")));


    private final String name;
    private final List<String> lore;
    private final String statsFileName;

    Achievements(String name, String statsFileName, List<String> lore) {
        this.name = name;
        this.statsFileName = statsFileName;
        this.lore = lore;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public String getStatsFileName() {
        return this.statsFileName;
    }
}
