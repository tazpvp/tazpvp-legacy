package net.tazpvp.tazpvp.Commands.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import static net.tazpvp.tazpvp.Tazpvp.vanished;
import static net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils.sendDuel;

public class DuelCMD {
    @CommandHook("duel")
    public void duel(Player p, Player target) {
        if (Tazpvp.duelLogic.isInDuel(p) || Tazpvp.duelLogic.isInDuel(target)) {
            p.sendMessage(ChatColor.RED + "Both users must be out of a duel to begin.");
            return;
        }
        if (CombatTag.isInCombat(p)) {
            p.sendMessage(ChatColor.RED + "You must be out of combat to begin.");
            return;
        }
        if (Tazpvp.punishmentManager.isBanned(p)) {
            p.sendMessage(ChatColor.RED + "You're banned, lol");
            return;
        }
        if (vanished.contains(p)) {
            p.sendMessage(ChatColor.RED + "You cannot duel while in vanish.");
            return;
        }
        if (Tazpvp.isRestarting) {
            p.sendMessage(ChatColor.RED + "You cannot duel while the server is restarting.");
            return;
        }
        if (p.equals(target)) {
            p.sendMessage(ChatColor.RED + "You cannot duel yourself.");
            return;
        }
        if (p.getGameMode().equals(GameMode.SPECTATOR)) {
            p.sendMessage(ChatColor.RED + "You cannot duel as a spectator.");
            return;
        }
        if (Tazpvp.dueling.containsKey(p.getUniqueId())) {
            if (Tazpvp.dueling.get(p.getUniqueId()).equals(target.getUniqueId())) {
                p.sendMessage(ChatColor.RED + "You have already sent a duel request to " + target.getName() + ".");
                return;
            }
        }
        if (Tazpvp.dueling.containsValue(p.getUniqueId())) { //if you were sent a duel
            if (Tazpvp.dueling.containsKey(target.getUniqueId())) { //if the person you want to duel already sent a request to someone
                if (Tazpvp.dueling.get(target.getUniqueId()).equals(p.getUniqueId())) { //if that request was sent to you
                    Tazpvp.dueling.remove(target.getUniqueId());
                    Tazpvp.dueling.remove(p.getUniqueId());
                    Tazpvp.duelLogic.duelStart(p, target, null);
                } else {
                    sendDuel(target, p);
                }
            } else {
                sendDuel(target, p);
            }
        } else {
            sendDuel(target, p);
        }
    }


    @CommandHook("spectate")
    public void onSpectate(Player p, Player target) {
        Tazpvp.duelLogic.addSpectator(p, target);
    }
}
