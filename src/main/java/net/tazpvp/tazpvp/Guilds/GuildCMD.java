package net.tazpvp.tazpvp.Guilds;

import net.minecraft.world.level.World;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuildCMD {
    @CommandHook("guild_sorttest")
    public void sortest(Player p) {
        HashMap<UUID, Double> map = new HashMap<>();
        for (Guild g : Tazpvp.guildManager.getAllGuilds()) {
            map.put(g.getID(), g.getKills());
        }
        Map<UUID, Double> sorted = GuildUtils.sortByValue(map);
        for (Map.Entry<UUID, Double> entry : sorted.entrySet()) {
            p.sendMessage(Tazpvp.guildManager.getGuild(entry.getKey()).name() + " has " + entry.getValue() + " kills");
        }
    }

    @CommandHook("guild_create") public void onGuildCreate(Player p, String name, String description) {
        GuildUtils.createGuild(p, name, description);
    }

    @CommandHook("guild_display") public void onGuildDisplay(Player p) {
        if (GuildUtils.isInGuild(p)) {
            Guild guild = GuildUtils.getGuild(p);
            p.sendMessage(GuildUtils.viewGuild(guild).toString());
            p.sendMessage(guild.owner().toString());
            p.sendMessage(guild.staff().toString());
            p.sendMessage(guild.members().toString());
            p.sendMessage(guild.invites().toString());
        } else {
            p.sendMessage(GuildUtils.notInG);
        }
    }

    @CommandHook("guild_leave") public void onGuildLeave(Player p) {
        if (GuildUtils.isInGuild(p)) {
            GuildUtils.leaveGuild(p, GuildUtils.getGuild(p));
        } else {
            p.sendMessage(GuildUtils.notInG);
        }
    }

    @CommandHook("guild_invite") public void onGuildInvite(Player p, Player target) {
        if (GuildUtils.isInGuild(p)) {
            GuildUtils.invite(p, target, GuildUtils.getGuild(p));
        } else {
            p.sendMessage(GuildUtils.notInG);
        }
    }

    @CommandHook("guild_accept") public void onGuildAccept(Player p) {
        GuildUtils.acceptInvite(p);

    }

    @CommandHook("guild_promote") public void onGuildPromote(Player p, Player target) {
        if (GuildUtils.isInGuild(p)) {
            GuildUtils.promote(p, target, GuildUtils.getGuild(p));
        } else {
            p.sendMessage(GuildUtils.notInG);
        }
    }

    @CommandHook("guild_demote") public void onGuildDemote(Player p, Player target) {
        if (GuildUtils.isInGuild(p)) {
            GuildUtils.demote(p, target, GuildUtils.getGuild(p));
        } else {
            p.sendMessage(GuildUtils.notInG);
        }
    }

    @CommandHook("guild_kick") public void onGuildKick(Player p, Player target) {
        if (GuildUtils.isInGuild(p)) {
            GuildUtils.kickFromGuild(p, target, GuildUtils.getGuild(p));
        } else {
            p.sendMessage(GuildUtils.notInG);
        }
    }

}
