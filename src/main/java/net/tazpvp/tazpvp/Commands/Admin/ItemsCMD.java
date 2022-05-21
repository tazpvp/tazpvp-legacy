package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.commandmanager.CommandHook;

public class ItemsCMD {
    @CommandHook("items_give")
    public void itemsGive(Player p){
        for (Items item : Items.values()){
            ItemBuilder.giveItem(p, item, 1);
        }
    }
    @CommandHook("items_pdc")
    public void itemsPdc(Player p){
        ItemStack item = p.getInventory().getItemInMainHand();
        NamespacedKey key = new NamespacedKey(Tazpvp.getInstance(), "custom-sword");
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if (container.has(key, PersistentDataType.DOUBLE)){
            double foundValue = container.get(key, PersistentDataType.DOUBLE);
            p.sendMessage("Found value: " + foundValue);
        }
    }
}
