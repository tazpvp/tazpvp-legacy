package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Mobs.MobUtils;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class MobsCMD {
    @CommandHook("mobs_spawn")
    public void mobsSpawnCommand(Player p) {
        p.sendMessage("Spawning mobs");
        MobUtils.doShitFirstTImeYKYK();
    }

    @CommandHook("mobs_kill")
    public void mobsKillCommand(Player p) {
        p.sendMessage("Killing mobs");
        MobUtils.clearMobs();
    }

    @CommandHook("mobs_reload")
    public void mobsReloadCommand(Player p) {
        p.sendMessage("Reloading mobs");
        MobUtils.clearMobs();
        MobUtils.doShitFirstTImeYKYK();
    }

    @CommandHook("mobs_list")
    public void mobsListCommand(Player p) {
        p.sendMessage("Mobs:");
        p.sendMessage(MobUtils.mobList.toString());
    }

}
