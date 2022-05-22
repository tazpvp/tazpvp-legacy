package net.tazpvp.tazpvp.Commands.Admin;

import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ECCMD {
    @CommandHook("enderchest")
    public void openEC(Player p) {
        p.openInventory(p.getEnderChest());
    }
}
