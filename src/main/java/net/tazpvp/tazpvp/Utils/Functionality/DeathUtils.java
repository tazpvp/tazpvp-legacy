package net.tazpvp.tazpvp.Utils.Functionality;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Custom.Sword.Items;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import javax.annotation.Nullable;
import java.util.Random;

import static net.tazpvp.tazpvp.Utils.Custom.Sword.GetRandomSword.getRandomSword;
import static net.tazpvp.tazpvp.Utils.Custom.Sword.ItemBuilder.maekItem;
import static net.tazpvp.tazpvp.Utils.Functionality.AchieveUtils.Achieve;
import static net.tazpvp.tazpvp.Utils.Functionality.ChatUtils.sendSurround;
import static net.tazpvp.tazpvp.Utils.Functionality.PlayerUtils.setHealth;

public class DeathUtils {

    final String prefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "☠" + ChatColor.GRAY + "] " + ChatColor.DARK_GRAY;

    private final Player p;
    private final Player killer;
    public DeathUtils(final Player p, @Nullable final Player killer) {
        this.p = p;
        this.killer = killer;
    }

    /**
     * Drops the player's head on death
     * @param bound number to generate to (exclusive)
     * @param chance number less than or equal to bound
     */
    public void dropHead(final int bound, final int chance) {
        if (new Random().nextInt(bound) <= chance){
            ItemStack head = new ItemBuilder(ItemUtils.skull(p)).setName(ChatColor.YELLOW + p.getName() + "'s head");
            Bukkit.getWorld("arena").dropItemNaturally(p.getLocation(), head);
        }
    }

    /**
     * Sends a death message to the player
     *
     * @param receiver the player to send the message to
     */
    public void sendDeathMessage(final Player receiver) {
        TextComponent hover = new TextComponent();
        final String who = (receiver == killer) ? "You" : ChatColor.GRAY + killer.getName();
        final String between = ChatColor.DARK_GRAY + " killed " + ChatColor.GRAY + p.getName();
        String end = "";
        if (receiver == killer) {
            end = ChatColor.GOLD + " + 5 Coins " + ChatColor.DARK_AQUA + "+ 25 Exp";
        } else {
            if (killer.getInventory().getItemInMainHand().hasItemMeta() && killer.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
                hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName())));
            }
        }
        String msg = prefix + who + between + end;
        hover.setText(msg);

        receiver.spigot().sendMessage(hover);
    }

    public void sendFunnyDeathMessage(final Player receiver) {
        final String who = p.getName();
        final String between = ChatColor.DARK_GRAY + " was killed in their own confusion!";
        receiver.sendMessage(prefix + who + between);
    }

    /**
     * Send a death message to the player who died
     */
    public void sendDeadDeathMessage() {
        String msg = prefix + ChatColor.DARK_GRAY + "You were killed by " + ChatColor.GRAY + killer.getName();
        TextComponent hover = new TextComponent();
        if (killer.getInventory().getItemInMainHand().hasItemMeta() && killer.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
            hover.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName())));
        }
        hover.setText(msg);

        p.spigot().sendMessage(hover);
    }

    /**
     * Sends a death message to all online players using {@code sendDeadDeathMessage()}
     */
    public void sendDeathMessageAll(boolean funny) {
        for (Player plr : Bukkit.getOnlinePlayers()) {
            if (funny) {
                sendFunnyDeathMessage(plr);
            } else {
                sendDeathMessage(plr);
            }
        }
    }

    /**
     * Checks if the {@code dead} player has a bounty
     */
    public void bountyCheck() {
        if (Tazpvp.bounty.containsKey(p.getUniqueId())) {
            Tazpvp.statsManager.addCoins(killer, Tazpvp.bounty.get(p.getUniqueId()));
            Bukkit.broadcastMessage(ChatColor.AQUA + killer.getName() + ChatColor.DARK_AQUA + " collected " + ChatColor.AQUA + p.getName() + "'s " + ChatColor.DARK_AQUA + "bounty: " + ChatColor.AQUA + "$" + Tazpvp.bounty.get(p.getUniqueId()));
            Tazpvp.bounty.remove(p.getUniqueId());
        }
    }

    /**
     * Updates all the stats of {@code p killer}
     */
    public void statsUpdate() {
        if (Tazpvp.statsManager.getExpLeft(p) <= 0) Tazpvp.statsManager.setExpLeft(p, 45);
        if (Tazpvp.statsManager.getExpLeft(killer) <= 0) Tazpvp.statsManager.setExpLeft(killer, 45);
        Tazpvp.statsManager.addKills(killer, 1);
        Tazpvp.statsManager.addExp(killer, 25);
        Tazpvp.statsManager.addStreak(killer, 1);
        if (Tazpvp.boolManager.getHasRebirthed(p)) Tazpvp.statsManager.addExp(killer, 5);
        Tazpvp.statsManager.addCoins(killer, 5);
        Tazpvp.statsManager.addDeaths(p, 1);
        Tazpvp.statsManager.setStreak(p, 0);


        if (Tazpvp.statsManager.checkLevelUp(killer)) {
            Tazpvp.statsManager.levelUp(killer, 1);
            if ((Tazpvp.statsManager.getLevel(killer) % 10) == 0) {
                Items unlockedItem;
                if (Tazpvp.statsManager.getLevel(killer) <= 30) {
                    unlockedItem = getRandomSword(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
                } else if (Tazpvp.statsManager.getLevel(killer) <= 50) {
                    unlockedItem = getRandomSword(ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON");
                } else if (Tazpvp.statsManager.getLevel(killer) <= 80) {
                    unlockedItem = getRandomSword(ChatColor.AQUA + "" + ChatColor.BOLD + "RARE");
                } else if (Tazpvp.statsManager.getLevel(killer) <= 100) {
                    unlockedItem = getRandomSword(ChatColor.RED + "" + ChatColor.BOLD + "EPIC");
                } else {
                    unlockedItem = getRandomSword(ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY");
                }
                killer.playSound(killer.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);
                killer.sendTitle(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "UNLOCKED", unlockedItem.getName(), 10, 80, 10);
                sendSurround(killer, ChatColor.DARK_AQUA + " You unlocked: " + ChatColor.BOLD + unlockedItem.getName());
                killer.getInventory().addItem(maekItem(unlockedItem));
                if (Tazpvp.statsManager.getLevel(killer) == 100) {
                    Achieve(killer, "Legend", "legend", 8, 235);
                }
            }
        }

        Tazpvp.statsManager.initScoreboard(p);
        Tazpvp.statsManager.initScoreboard(killer);

        if (Tazpvp.statsManager.getStreak(killer) == 100) {
            Achieve(killer, "Bowling", "bowling", 5, 380);
        }
        if ((Tazpvp.statsManager.getStreak(killer) % 5) == 0) {
            if (Tazpvp.bounty.containsKey(killer.getUniqueId())) {
                Tazpvp.bounty.put(killer.getUniqueId(), Tazpvp.bounty.get(killer.getUniqueId()) + 32);
            } else {
                Tazpvp.bounty.put(killer.getUniqueId(), 25);
            }
            Bukkit.broadcastMessage(ChatColor.GOLD + killer.getName() + ChatColor.YELLOW + " has a kill streak of " + ChatColor.GOLD + Tazpvp.statsManager.getStreak(killer));
            if (Tazpvp.bounty.containsKey(killer.getUniqueId())) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Bounty: " + ChatColor.YELLOW + "$" + Tazpvp.bounty.get(killer.getUniqueId()));
            }
            Tazpvp.statsManager.addCoins(killer, 10);
            killer.sendMessage(ChatColor.GOLD + "+ $" + 10);
        }
    }

    /**
     * Add health bc why not lol
     */
    public void applyHealth() {
        if (Tazpvp.boolManager.getHasRebirthed(killer)) {
            setHealth(killer, 24, 2);
        } else if (Tazpvp.perkManager.getFatPerk(killer)) {
            setHealth(killer, 22, 2);
        } else {
            setHealth(killer, 20, 4);
        }
    }

    public void kys() {
        if (p.hasMetadata("LastDamager")) {
            p.removeMetadata("LastDamager", Tazpvp.getInstance());
        }

        p.getWorld().playEffect(p.getLocation().add(0, 1, 0), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
        p.setGameMode(GameMode.SPECTATOR);
        p.setMetadata("spectating", new FixedMetadataValue(Tazpvp.getInstance(), true));
        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerUtils.healPlayer(p);
                if (Tazpvp.punishmentManager.isBanned(p)) {
                    p.teleport(new Location(Bukkit.getWorld("ban"), 0.5, 70, 0.5));
                } else {
                    p.teleport(configUtils.spawn);
                    CombatTag.combatLog.remove(p.getUniqueId());
                    Tazpvp.lastDamage.remove(p.getUniqueId());
                    Tazpvp.particleUtil.load(p);
                }
                p.setMetadata("spectating", new FixedMetadataValue(Tazpvp.getInstance(), false));
                p.setGameMode(GameMode.SURVIVAL);
                p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            }
        }.runTaskLater(Tazpvp.getInstance(), 60L);
    }
}
