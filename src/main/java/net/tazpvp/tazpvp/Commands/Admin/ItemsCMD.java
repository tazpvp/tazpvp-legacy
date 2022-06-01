package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import net.tazpvp.tazpvp.Utils.GetRandomSword;
import net.tazpvp.tazpvp.Utils.PdcUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.commandmanager.CommandHook;

import java.util.List;

public class ItemsCMD implements CommandListener {
    @CommandHook("items_give")
    public void itemsGive(Player p){
        for (Items item : Items.values()){
            ItemBuilder.giveItem(p, item, 1);
        }
    }
    @CommandHook("items_pdc")
    public void itemsPdc(Player p){
        ItemStack item = p.getInventory().getItemInMainHand();
        NamespacedKey key = PdcUtils.key;
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();

        if (container.has(key, PersistentDataType.DOUBLE)){
            double foundValue = container.get(key, PersistentDataType.DOUBLE);
            p.sendMessage("Found value: " + foundValue);
        }
    }
    @CommandHook("items_getRandom")
    public void itemsGetRandom(Player p) {
        p.sendMessage(new GetRandomSword().getRandomSword().getName());
    }
    @CommandHook("items_unlocked")
    public void itemsUnlocked(Player p) {
        p.sendMessage(Tazpvp.playerWrapperMap.get(p.getUniqueId()).toString());
    }
    @CommandHook("items_wipe")
    public void itemsWipe(Player p) {
        List<Items> items = Tazpvp.playerWrapperMap.get(p.getUniqueId()).getSwords();
        items.clear();
        Tazpvp.playerWrapperMap.get(p.getUniqueId()).setSwords(items);
        p.sendMessage("Swords wiped!");
    }
}
