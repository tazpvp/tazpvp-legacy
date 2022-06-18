package net.tazpvp.tazpvp.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CmdCooldown {
    private static Map<UUID, Long> cooldowns = new HashMap<>();
    private int cooldown;

    public cmdCooldown(int num){
        this.cooldown = num;

    }

    public int getCooldown(){
        return cooldown;
    }
}
