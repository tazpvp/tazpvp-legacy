package net.tazpvp.tazpvp.Mobs;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class CZombie {
    public void setZombie(Zombie z) {
        z.setCustomName(ChatColor.BLUE + "Zombie");
        z.setCustomNameVisible(true);
        z.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
        z.getPersistentDataContainer().set(new NamespacedKey(Tazpvp.getInstance(), "mob"), PersistentDataType.INTEGER, MobLocations.ZOMBIE.id());
    }

    public void createZombie() {
        Location loc = MobLocations.ZOMBIE.location();
        Zombie z = (Zombie) loc.getWorld().spawnEntity(loc, MobLocations.ZOMBIE.mob());
        setZombie(z);
        MobUtils.mobList.put(z, MobLocations.ZOMBIE);
    }
}
