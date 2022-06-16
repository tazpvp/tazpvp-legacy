package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Utils.Custom.Items.ItemManager;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.itemutils.ItemBuilder;

public class TrollCMD implements CommandListener {
    @CommandHook("demo")
    public void demoCommand(Player p, Player target) {
        target.showDemoScreen();
    }

    @CommandHook("stick")
    public void stickCommand(Player p) {
        ItemManager.givePlayerItem(p, Items.RIDEPEARL, 1);
    }
}
