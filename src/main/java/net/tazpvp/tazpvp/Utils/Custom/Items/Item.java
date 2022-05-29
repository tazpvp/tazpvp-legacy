package net.tazpvp.tazpvp.Utils.Custom.Items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Item {
    public final String name;
    public final Material item;
    public final double cID;
    public final String[] lore;
    public final Items enumeration;
    public Item(Items item) {
        this.name = item.name;
        this.item = item.item;
        this.cID = item.cID;
        this.lore = item.lore;
        this.enumeration = item;
    }

    public abstract boolean execute(Player p, ItemStack itemstack, double cID);
}
