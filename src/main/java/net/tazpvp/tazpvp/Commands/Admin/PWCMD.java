package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Managers.PlayerWrapperManagers.PlayerWrapper;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class PWCMD {
    @CommandHook("pw_test")
    public void pwTest(Player p) {
        PlayerWrapper pw = Tazpvp.playerWrapperMap.get(p.getUniqueId());
        pw.sendMother();
    }
    @CommandHook("pw_wipe")
    public void pwWipe(Player p) {
        PlayerWrapper newPw = new PlayerWrapper(p);
        Tazpvp.playerWrapperMap.remove(p.getUniqueId());
        Tazpvp.playerWrapperStatsManager.setPlayerWrapper(p.getUniqueId(), newPw);
        Tazpvp.playerWrapperMap.put(p.getUniqueId(), Tazpvp.playerWrapperStatsManager.getPlayerWrapper(p.getUniqueId()));
    }
}
