package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class VanishCMD {
    @CommandHook("vanish")
    public void vanish(Player target) {
        if (CombatTag.isInCombat(target)){ target.sendMessage(ChatColor.RED + "You cannot vanish while in combat."); return;}
        PlayerUtils.vanish(target);
    }
}
