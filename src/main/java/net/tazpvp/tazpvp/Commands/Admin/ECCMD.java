package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ECCMD implements CommandListener {
    @CommandHook("enderchest")
    public void openEC(Player p) {
        p.openInventory(p.getEnderChest());
    }
}
