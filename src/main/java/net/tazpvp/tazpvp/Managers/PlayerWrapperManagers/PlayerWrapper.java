package net.tazpvp.tazpvp.Managers.PlayerWrapperManagers;

import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerWrapper implements Serializable {
    private final Player p;
    private int mothers;
    private List<Items> swords = new ArrayList<>();

    public PlayerWrapper(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

    public void sendMother() {
        p.sendMessage("Mother" + mothers);
        mothers++;
    }

    public void setSwords(List<Items> swords) {
        this.swords = swords;
    }

    public List<Items> getSwords() {
        return swords;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Swords: ");
        for (Items i : swords) {
            sb.append(i.toString()).append(" ");
        }
        return sb.toString();
    }
}
