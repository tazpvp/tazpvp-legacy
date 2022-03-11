package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Item implements Listener {
    private String name;
    private String lore;
    private ItemStack itemstack;
    private int cost;
    private int damage;
    private char rarity;
    public final Items enumeration;
    public Item(Items item) {
        this.name = item.getName();
        this.lore = item.getLore();
        this.itemstack = item.getItem();
        this.cost = item.getCost();
        this.damage = item.getDamage();
        this.rarity = item.getRarity();
        this.enumeration = item;
    }

    public abstract boolean execute(Player p, ItemStack itemStack, EntityDamageByEntityEvent e);

}
