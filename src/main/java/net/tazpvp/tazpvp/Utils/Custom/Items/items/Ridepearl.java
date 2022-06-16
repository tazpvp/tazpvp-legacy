package net.tazpvp.tazpvp.Utils.Custom.Items.items;

import net.tazpvp.tazpvp.Utils.Custom.Items.ConsumableItem;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ridepearl extends ConsumableItem {
    public Ridepearl() {
        super(Items.RIDEPEARL, 10);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack, double cID) {
        if(super.execute(p, itemStack, cID)) {
            return true;
        }

        EnderPearl pearl = p.launchProjectile(EnderPearl.class);

        pearl.setCustomName(Items.RIDEPEARL.name);
        pearl.setCustomNameVisible(true);
        pearl.setBounce(true);
        pearl.addPassenger(p);

        return false;
    }
}
