package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class SwordsColat {
    @CommandHook("swordscolat_unlock")
    public void unlockSword(Player p) {
        Tazpvp.swordManager.addSword(p, Items.WOOGSWORD);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSword wurde erfolgreich freigeschaltet!"));
        getSword(p);
    }

    @CommandHook("swordscolat_lock")
    public void lockSword(Player p) {
        Tazpvp.swordManager.removeSword(p, Items.WOOGSWORD);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSword wurde erfolgreich gesperrt!"));
    }

    @CommandHook("swordscolat_wipe")
    public void wipeSword(Player p) {
        Tazpvp.swordManager.wipeSwords(p);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAlle Swords wurden gelöscht!"));
    }

    @CommandHook("swordscolat_get")
    public void getSword(Player p) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aDu hast folgende Swords:"));
        for (Items i : Tazpvp.swordManager.getSwords(p)) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a" + i.getName()));
        }
        p.sendMessage(Tazpvp.swordManager.getSwords(p).toString());
    }
}
