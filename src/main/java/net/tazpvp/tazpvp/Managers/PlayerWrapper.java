package net.tazpvp.tazpvp.Managers;

import org.bukkit.entity.Player;

public class PlayerWrapper {
    private Player p;
    private int mothers;

    public PlayerWrapper(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

    public void sendMother() {
        p.sendMessage("Mother");
        mothers++;
    }
}
