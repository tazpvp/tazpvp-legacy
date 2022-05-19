package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Managers.PlayerWrapper;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class PWCMD {
    @CommandHook("pw_test")
    public void pwTest(Player p) {
        PlayerWrapper pw = Tazpvp.playerWrapperStatsManager.getPlayerWrapper(p);

        pw.sendMother();

        Tazpvp.playerWrapperStatsManager.setPlayerWrapper(p, pw);
    }
}
