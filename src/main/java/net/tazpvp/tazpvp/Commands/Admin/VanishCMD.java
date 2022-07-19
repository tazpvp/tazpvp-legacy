package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import static net.tazpvp.tazpvp.Tazpvp.vanished;

public class VanishCMD {
    @CommandHook("vanish")
    public void vanish(Player target) {
        PlayerUtils.vanish(target);
    }
}
