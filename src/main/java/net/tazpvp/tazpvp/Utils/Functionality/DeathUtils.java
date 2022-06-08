package net.tazpvp.tazpvp.Utils.Functionality;

import jline.internal.Nullable;
import net.tazpvp.tazpvp.Managers.CombatLogManager;
import net.tazpvp.tazpvp.Tazpvp;
import net.tazpvp.tazpvp.Utils.Functionality.IA.addGlowUtil;
import net.tazpvp.tazpvp.Utils.Variables.configUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import java.util.Random;

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
        final String who = (receiver == killer) ? "You" : ChatColor.GRAY + p.getName();
        final String between = ChatColor.DARK_GRAY + " killed " + ChatColor.GRAY + p.getName();
        String end = "";
        if (receiver == killer) {
            end = ChatColor.GOLD + " + 7 Coins " + ChatColor.DARK_AQUA + "+ 15 Exp";
        } else {
            if (killer.getInventory().getItemInMainHand().hasItemMeta() && killer.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
                end = ChatColor.DARK_GRAY + " using " + ChatColor.AQUA + killer.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
            }
        }

        receiver.sendMessage(prefix + who + between + end);
    }

    /**
     * Send a death message to the player who died
     */
    public void sendDeadDeathMessage() {
        p.sendMessage(prefix + ChatColor.DARK_GRAY + "You were killed by " + ChatColor.GRAY + killer.getName());
    }

    /**
     * Sends a death message to all online players using {@code sendDeadDeathMessage()}
     */
    public void sendDeathMessageAll() {
        for (Player plr : Bukkit.getOnlinePlayers()) {
            sendDeathMessage(plr);
        }
    }

    /**
     * Checks if the {@code dead} player has a bounty
     */
    public void bountyCheck() {
        if (Tazpvp.bounty.containsKey(p.getUniqueId())) {
            Tazpvp.statsManager.addCoins(killer, Tazpvp.bounty.get(p.getUniqueId()));
            Bukkit.broadcastMessage(ChatColor.AQUA + killer.getName() + ChatColor.DARK_AQUA + " collected the " + ChatColor.AQUA + Tazpvp.bounty.get(p.getUniqueId()) + ChatColor.DARK_AQUA + " bounty on " + ChatColor.AQUA + p.getName());
            Tazpvp.bounty.remove(p.getUniqueId());
        }
    }

    /**
     * Updates all the stats of {@code p killer}
     */
    public void statsUpdate() {
        Tazpvp.statsManager.addKills(killer, 1);
        Tazpvp.statsManager.addExp(killer, 15);
        if (Tazpvp.boolManager.getHasRebirthed(p)) Tazpvp.statsManager.addExp(killer, 5);
        Tazpvp.statsManager.addCoins(killer, 7);
        Tazpvp.statsManager.addDeaths(p, 1);

        if (Tazpvp.statsManager.checkLevelUp(killer)) {
            Tazpvp.statsManager.levelUp(killer, 1);
        }

        Tazpvp.statsManager.initScoreboard(p);
        Tazpvp.statsManager.initScoreboard(killer);
    }

    /**
     * Add health bc why not lol
     */
    public void applyHealth() {
        if (Tazpvp.boolManager.getHasRebirthed(killer)) {
            setHealth(killer, 24, 2);
        }

        if (Tazpvp.perkManager.getFatPerk(killer)) {
            setHealth(killer, (Tazpvp.boolManager.getHasRebirthed(killer)) ? 24 : 22, 2);
            return;
        }

        setHealth(killer, 20, 4);
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
                    p.teleport(new Location(Bukkit.getWorld("ban"), 0, 77, 0));
                } else {
                    p.teleport(configUtils.spawn);
                    CombatLogManager.combatLog.remove(p.getUniqueId());
                }
                p.setMetadata("spectating", new FixedMetadataValue(Tazpvp.getInstance(), false));
                p.setGameMode(GameMode.SURVIVAL);
                p.getAttribute(org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            }
        }.runTaskLater(Tazpvp.getInstance(), 60L);
    }

}