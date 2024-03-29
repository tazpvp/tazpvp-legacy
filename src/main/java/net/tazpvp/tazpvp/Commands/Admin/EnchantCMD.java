package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.enchants.CustomEnchant;
import redempt.redlib.enchants.EnchantRegistry;

public class EnchantCMD implements CommandListener {
    @CommandHook("enchant")
    public void enchant(Player p, String enchantName, int level) {
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType() != Material.AIR) {
            CustomEnchant ench = EnchantRegistry.get(Tazpvp.getInstance()).getByName(enchantName);
            if (ench != null) {
                ench.apply(item, level);
            }
        }
    }
}
