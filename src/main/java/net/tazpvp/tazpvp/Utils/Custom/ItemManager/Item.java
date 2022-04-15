package net.tazpvp.tazpvp.Utils.Custom.ItemManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Item implements Listener {
    private String name;
    private String lore;
    private Material material;
    private int cost;
    private int damage;
    private int exp;
    private char rarity;
    public final Items enumeration;
    public Item(Items item) {
        this.name = item.getName();
        this.lore = item.getLore();
        this.material = item.getMaterial();
        this.cost = item.getCost();
        this.damage = item.getDamage();
        this.exp = item.getExp();
        this.rarity = item.getRarity();
        this.enumeration = item;
    }

    public abstract boolean execute(Player p, String name, EntityDamageByEntityEvent e);

}
