package net.tazpvp.tazpvp.Commands.Player;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.tazpvp.tazpvp.Managers.CombatLogManager;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.checkerframework.checker.units.qual.C;
import redempt.redlib.commandmanager.CommandHook;

import java.util.List;

public class DuelCMD {
    @CommandHook("duel")
    public void duel(Player p, Player target) {
        if (Tazpvp.duelLogic.isInDuel(p) || Tazpvp.duelLogic.isInDuel(target)) { p.sendMessage(ChatColor.RED + "Both users must be out of a duel to begin."); return; }
        if (CombatLogManager.isInCombat(p) || CombatLogManager.isInCombat(target)) { p.sendMessage(ChatColor.RED + "Both users must be out of combat to begin."); return;}
        if (sentDuel(target).equals(p.getName())) {
            target.setMetadata("sentDuel", new FixedMetadataValue(Tazpvp.getInstance(), ""));
            p.setMetadata("sentDuel", new FixedMetadataValue(Tazpvp.getInstance(), ""));
            Tazpvp.duelLogic.duelStart(p, target, null);
        } else {
            target.setMetadata("sentDuel", new FixedMetadataValue(Tazpvp.getInstance(), p.getName()));
            TextComponent Accept = new TextComponent(ChatColor.GRAY + " " + ChatColor.BOLD + "CLICK HERE " + ChatColor.GRAY + "to accept.");
            Accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duel " + p.getName()));
            Accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "ACCEPT").create()));

            target.sendMessage(ChatColor.DARK_GRAY + "");
            target.sendMessage(" " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has challenged you to a duel.");
            target.spigot().sendMessage(Accept);
            target.sendMessage(ChatColor.DARK_GRAY + "");
            p.sendMessage(ChatColor.DARK_GRAY + "Sent Duel");
        }
    }

    public String sentDuel(Player p){
        List<MetadataValue> metaDataValues = p.getMetadata("sentDuel");
        for (MetadataValue metaDataValue : metaDataValues) {
            return metaDataValue.asString();
        }
        return "";
    }
}
