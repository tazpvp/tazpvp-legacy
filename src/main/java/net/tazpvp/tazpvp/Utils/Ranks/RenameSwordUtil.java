package net.tazpvp.tazpvp.Utils.Ranks;

import net.tazpvp.tazpvp.Tazpvp;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import redempt.redlib.itemutils.ItemBuilder;

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
        if (Tazpvp.statsManager.getMoney(p) >= 200) {
            renameSword(p, sword);
        } else {
            p.sendMessage(ChatColor.RED + "You don't have enough money to rename this sword!");
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
