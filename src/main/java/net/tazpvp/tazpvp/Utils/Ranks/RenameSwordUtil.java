package net.tazpvp.tazpvp.Utils.Ranks;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.tazpvp.tazpvp.Tazpvp;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class RenameSwordUtil {

    public static ArrayList<String> badWords = new ArrayList<String>(Arrays.asList( "anal", "anus","arse","ass","ass","fuck","ass","assfucker","asshole","assshole","bastard","bitch","cock","boong","cock","cockfucker","cocksuck","cocksucker","coon","coonnass","cunt","cyberfuck","dick","dirty","douche","dummy","erect","erection","erotic","escort","fag","faggot","fuck","fuckass","fuckhole","gook","homoerotic","hore","lesbian","lesbians","motherfuck","motherfucker","negro","nigger","orgasim","orgasm","penis","penisfucker","piss","porn","porno","pornography","pussy","retard","sadist","sex","sexy","shit","slut","bitch","tits","viagra","whore","xxx" ));
    public static void renameSword(Player p, ItemStack sword) {
        new AnvilGUI.Builder()
                .onComplete((player, text) -> {
                    if (badWords.contains(text.toLowerCase(Locale.ROOT))) {
                        p.sendMessage(ChatColor.RED + "That word is not allowed!");
                        return AnvilGUI.Response.close();
                    } else {
                        ItemMeta swordMeta = sword.getItemMeta();
                        swordMeta.setDisplayName(text);
                        sword.setItemMeta(swordMeta);
                        return AnvilGUI.Response.close();
                    }
                })
                .itemLeft(sword)
                .title(ChatColor.YELLOW + "Rename Sword to:")
                .plugin(Tazpvp.getInstance())
                .open(p);
    }

    public static void renameChecks(Player p, ItemStack sword) {
        if (Tazpvp.statsManager.getCoins(p) >= 200) {
            renameSword(p, sword);
        } else {
            TextComponent nocred = new TextComponent(ChatColor.RED + "Insufficient Credits! " + ChatColor.WHITE + "[CLICK HERE]");
            nocred.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://tazpvp.tebex.io/"));
            p.spigot().sendMessage(nocred);
        }
    }

    public static ItemStack getSwordToRename(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null) {
                if (item.getType().toString().toLowerCase(Locale.ROOT).contains("sword")) {
                    return item;
                }
            }
        }
        return null;
    }
}
