package net.tazpvp.tazpvp.GUI.Bow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class FlameVilGUI {
    public FlameVilGUI(Player p) {
        open(p);
    }

    public void open(Player p) {
        Merchant m = Bukkit.createMerchant("test");

        List<MerchantRecipe> recipes = new ArrayList<>();
        recipes.add(flame1());
        m.setRecipes(recipes);

        p.openMerchant(m, true);
    }

    private MerchantRecipe flame1() {
        ItemStack result = new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_FIRE, 1);
        MerchantRecipe recipe = new MerchantRecipe(result, 99999);
        recipe.setUses(0);
        recipe.addIngredient(new ItemBuilder(Material.BOW));
        return recipe;
    }
}
