package net.tazpvp.tazpvp.Guilds;

import net.tazpvp.tazpvp.Tazpvp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class GuildConfig {
    public static void reload() {
        Tazpvp.getInstance().reloadConfig();
        Tazpvp.getInstance().config = Tazpvp.getInstance().getConfig();
    }

    public static List<String> badWords() {
        return badWords;
    }

    public static boolean isOffending(String text) {
        String[] words = text.split(" ");
        List<String> noNoWords = badWords;
        for (String s : words) {
            if (noNoWords.contains(s.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    public static List<String> badWords = new ArrayList<String>(Arrays.asList(
            "anal", "anus","arse","ass","ass","fuck","ass","assfucker","asshole","assshole","bastard","bitch","cock","boong","cock", "cum","cockfucker","cocksuck","cocksucker","coon","coonnass","cunt","cyberfuck","dick","douche","erect","erection","erotic","fag","faggot","fuck","fuckass","fuckhole","gook","homoerotic","hore","lesbian","lesbians","motherfuck","motherfucker","negro","nigger","orgasim","orgasm","penis","penisfucker","piss","porn","porno","pornography","pussy","retard","sadist","sex","sexy","shit","slut","bitch","tits","viagra","whore","xxx"
    ));
}
