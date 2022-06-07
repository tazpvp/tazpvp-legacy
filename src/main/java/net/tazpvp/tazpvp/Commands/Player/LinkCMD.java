package net.tazpvp.tazpvp.Commands.Player;

import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class LinkCMD {
    @CommandHook("link")
    public void onLink(Player p) {
        p.sendMessage("Linked!");
    }
}
