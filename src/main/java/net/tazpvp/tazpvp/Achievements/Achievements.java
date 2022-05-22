package net.tazpvp.tazpvp.Achievements;

import org.bukkit.Material;

public enum Achievements {
    SENDCHATMESSAGE("Send a chat message!", "send achat mesage");

    private final String name;
    private final String[] lore;

    Achievements(String name, String... lore) {
        this.name = name;
        this.lore = lore;
    }

    public String getName() {
        return this.name;
    }

    public String[] getLore() {
        return this.lore;
    }
}
