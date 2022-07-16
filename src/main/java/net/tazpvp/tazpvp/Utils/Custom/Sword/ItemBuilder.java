package net.tazpvp.tazpvp.Utils.Custom.Sword;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ItemBuilder {
    public static void giveItem(Player p, Items item, int amount) {
        for (int i = 0; i < amount; i++) {
            p.getInventory().addItem(maekItem(item));
        }
    }

    public static ItemStack maekItem(Items item) {
        String name = item.getName();
        List<String> lore = new ArrayList<>(List.of(item.getLore()));
        Material material = item.getMaterial();

        lore.add("");
        String[] split = lore.toString().split(", ");
        split[0] = split[0].replace("[", "");
        split[split.length - 1] = split[split.length - 1].replace("]", "");


        ItemStack itemz = new redempt.redlib.itemutils.ItemBuilder(material).setName(name).setLore(split);
        ItemMeta meta = itemz.getItemMeta();
        meta.getPersistentDataContainer().set(item.getKey(), item.getType(), item.getStoredID());
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);


//        AttributeModifier modDamage = new AttributeModifier(UUID.fromString("f42c1399-5e51-49b0-9e2f-7c187e3d1dfe"), "damage.cool", item.getDamage(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
//        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modDamage);
//
//        AttributeModifier modifier = new AttributeModifier(UUID.fromString("3fa75ab4-ff99-11ec-b939-0242ac120002"), "generic.attackSpeed", -2.3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
//        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);

        itemz.setItemMeta(meta);

        Map<Enchantment, Integer> enchantments = item.getEnchantments();
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            itemz.addUnsafeEnchantment(entry.getKey(), entry.getValue());
        }

        return itemz;
    }
}
