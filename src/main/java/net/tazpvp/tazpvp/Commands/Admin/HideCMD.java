package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.HideUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class HideCMD {
    @CommandHook("hide")
    public void onHide(Player p) {
        if (Tazpvp.hidden.contains(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You are already hidden");
            return;
        }
        HideUtil.hide(p);
        p.sendMessage(ChatColor.AQUA + "You are now hidden");
    }

    @CommandHook("unhide")
    public void onUnhide(Player p) {
        if (!Tazpvp.hidden.contains(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "You are already un-hidden");
            return;
        }
        HideUtil.unhide(p);
        p.sendMessage(ChatColor.AQUA + "You are now unhidden");
    }
}
