package net.tazpvp.tazpvp.Utils.Custom.Items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.WeakHashMap;

public class ClickableItem extends Item{
    final WeakHashMap<UUID, Long> cooldown = new WeakHashMap<>();
    final int cooldownTime;
    public ClickableItem(Items item, int cooldownTime) {
        super(item);
        this.cooldownTime = cooldownTime;
    }


    @Override
    public boolean execute(Player p, ItemStack itemStack, double cID) {
        if(cooldown.containsKey(p.getUniqueId())){
            long secondsLeft = cooldown.get(p.getUniqueId())-System.currentTimeMillis();
            if(secondsLeft>0) {
                return true;
            } else {
                cooldown.remove(p.getUniqueId());
            }
        }
        cooldown.put(p.getUniqueId(), System.currentTimeMillis() + (cooldownTime * 1000L));
        return false;
    }
}
