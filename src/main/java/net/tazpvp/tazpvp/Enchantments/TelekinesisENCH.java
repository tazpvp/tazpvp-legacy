package net.tazpvp.tazpvp.Enchantments;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.enchants.CustomEnchant;
import redempt.redlib.enchants.trigger.EnchantTrigger;

public class TelekinesisENCH extends CustomEnchant {
    public TelekinesisENCH() {
        super("telekinesis", 1);
        addTrigger(EnchantTrigger.MINE_BLOCK, (event, level) -> {
            Player p = event.getPlayer();
            Block b = event.getBlock();
            Material m = b.getType();
            Material type;
            ItemStack i = new ItemStack(m);
            p.getInventory().addItem(i);
            p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 1, 1);
        });
    }
}
