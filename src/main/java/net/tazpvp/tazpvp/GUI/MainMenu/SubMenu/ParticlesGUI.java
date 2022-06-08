package net.tazpvp.tazpvp.GUI.MainMenu.SubMenu;

import net.tazpvp.tazpvp.Cosmetics.Particle.ParticleEffect;
import net.tazpvp.tazpvp.Cosmetics.Particle.Particles.*;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Ranks.RankUtils;
import net.tazpvp.tazpvp.Utils.Ranks.RankWeight;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import redempt.redlib.inventorygui.InventoryGUI;
import redempt.redlib.inventorygui.ItemButton;
import redempt.redlib.itemutils.ItemBuilder;

public class ParticlesGUI {
    private InventoryGUI gui;

    public ParticlesGUI(Player p) {
        gui = new InventoryGUI(Bukkit.createInventory(null, 3 * 9, "Particles"));
        addItems();
        gui.open(p);
    }

    public void addItems() {

        ItemButton wideCircle = ItemButton.create(new ItemBuilder(Material.BOWL).setName(ChatColor.RED + "Wide Circle").setLore(ChatColor.RED + "Requires: VIP or higher!"), e -> {
           if (canAccessHmm((Player) e.getWhoClicked(), 1)) {
               e.getWhoClicked().sendMessage("Applying wide circle particle...");
               e.getWhoClicked().closeInventory();
               doParticle((Player) e.getWhoClicked(), new WideCircleParticle((Player) e.getWhoClicked(), getColor((Player) e.getWhoClicked()), Color.WHITE));
           }
        });

        ItemButton haloCircle = ItemButton.create(new ItemBuilder(Material.SUNFLOWER).setName(ChatColor.RED + "Halo").setLore(ChatColor.RED + "Requires: MVP or higher!"), e -> {
            if (canAccessHmm((Player) e.getWhoClicked(), 2)) {
                e.getWhoClicked().sendMessage("Applying halo halo particle...");
                e.getWhoClicked().closeInventory();
                doParticle((Player) e.getWhoClicked(), new HaloParticle((Player) e.getWhoClicked(), getColor((Player) e.getWhoClicked()), Color.WHITE));
            }
        });

        ItemButton TwoHorzCircle = ItemButton.create(new ItemBuilder(Material.HEART_OF_THE_SEA).setName(ChatColor.RED + "Horizontal Aura").setLore(ChatColor.RED + "Requires: MVP+ or higher!"), e -> {
            if (canAccessHmm((Player) e.getWhoClicked(), 3)) {
                e.getWhoClicked().sendMessage("Applying your aura particle...");
                e.getWhoClicked().closeInventory();
                doParticle((Player) e.getWhoClicked(), new TwoHorizontalParticle((Player) e.getWhoClicked(), getColor((Player) e.getWhoClicked()), Color.WHITE));
            }
        });

        ItemButton CrushedCandyCane = ItemButton.create(new ItemBuilder(Material.PINK_DYE).setName(ChatColor.RED + "Crushed Candy Cane").setLore(ChatColor.RED + "Requires: MVP+ or higher!"), e -> {
            if (canAccessHmm((Player) e.getWhoClicked(), 3)) {
                e.getWhoClicked().sendMessage("Applying your crushed candy cane particle...");
                e.getWhoClicked().closeInventory();
                doParticle((Player) e.getWhoClicked(), new CrushedCandyCaneParticle((Player) e.getWhoClicked()));
            }
        });

        ItemButton Love = ItemButton.create(new ItemBuilder(Material.RED_CANDLE).setName(ChatColor.RED + "Love Hearts").setLore(ChatColor.RED + "Requires: MVP+ or higher!"), e -> {
            if (canAccessHmm((Player) e.getWhoClicked(), 3)) {
                e.getWhoClicked().sendMessage("Applying your love <3 particle...");
                e.getWhoClicked().closeInventory();
                doParticle((Player) e.getWhoClicked(), new LoveParticle((Player) e.getWhoClicked()));
            }
        });

        ItemButton disable = ItemButton.create(new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "Disable Particles").setLore(ChatColor.RED + "DISABLE all active particles!"), e -> {
            Tazpvp.particleUtil.removeAll((Player) e.getWhoClicked());
            e.getWhoClicked().sendMessage(ChatColor.RED + "Particles disabled!");
            e.getWhoClicked().closeInventory();
        });

        gui.addButton(11, wideCircle);
        gui.addButton(12, haloCircle);
        gui.addButton(13, TwoHorzCircle);
        gui.addButton(14, CrushedCandyCane);
        gui.addButton(15, Love);
        gui.addButton(22, disable);
        gui.update();
    }

    public void doParticle(Player p, ParticleEffect effect) {
        Tazpvp.particleUtil.init(p, effect);
    }

    public Color getColor(Player p) {
        if (RankUtils.getRankFromString(Tazpvp.permissions.getPrimaryGroup(p)) == null) {
            return (p.hasPermission("tazpvp.staff.particles") ? Color.PURPLE : Color.WHITE);
        } else {
            return RankUtils.getRankFromString(Tazpvp.permissions.getPrimaryGroup(p)).getColor();
        }
    }

    public boolean canAccessHmm(Player p, int weight) {
        if (p.hasPermission("tazpvp.staff.particles")) {
            return true;
        }

        return RankUtils.getRankFromString(Tazpvp.permissions.getPrimaryGroup(p)).getWeight() >= weight;
    }
}
