package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Utils.Custom.ItemManager.ItemBuilder;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.Items;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.temp;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ItemsCMD {
    @CommandHook("items_list")
    public void itemsList(Player p){
        temp.run(p);
    }
    @CommandHook("items_add")
    public void itemsAdd(Player p){
        temp.unlock(p, Items.TEMPLATE);
    }
    @CommandHook("items_remove")
    public void itemsRemove(Player p){
        temp.lock(p, Items.TEMPLATE);
    }
    @CommandHook("items_give")
    public void itemsGive(Player p){
        for (Items item : Items.values()){
            ItemBuilder.giveItem(p, item, 1);
        }
    }
}
