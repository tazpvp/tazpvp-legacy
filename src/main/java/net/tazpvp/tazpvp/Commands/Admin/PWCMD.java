package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Managers.PlayerWrapper;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class PWCMD {
    @CommandHook("pw_test")
    public void pwTest(Player p) {
        PlayerWrapper pw = new PlayerWrapper(p);

        pw.sendMother();
    }
}
