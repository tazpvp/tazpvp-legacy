package net.tazpvp.tazpvp.Commands.Admin;

import net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

public class ItemsCMD {
    @CommandHook("items_give")
    public void itemsGive(Player p){
        for (Items item : Items.values()){
            ItemBuilder.giveItem(p, item, 1);
        }
    }
}
