package net.tazpvp.tazpvp.Utils.Custom.Items.items;

import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Items.ConsumableItem;
import net.tazpvp.tazpvp.Utils.Custom.Items.Items;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CreditPaper extends ConsumableItem {

    public CreditPaper() {
        super(Items.CREDITPAPER, 0);
    }

    @Override
    public boolean execute(Player p, ItemStack itemStack) {
        if(super.execute(p, itemStack)) {
            return true;
        }

        p.sendMessage(ChatColor.RED + "YOOOOOOOOOOOOOOOOOO CLAIMNIN 50 CWEDITS!!!!!!!!! SO COOOOOOOOOOOLLLLLLLLLLLLLLLLLL");
        Tazpvp.statsManager.addShards(p, 50);

        return false;
    }
}
