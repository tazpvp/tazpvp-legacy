package net.tazpvp.tazpvp.Guilds;

import net.minecraft.world.level.World;
import net.tazpvp.tazpvp.GUI.Guild.GuildBrowserGUI;
import net.tazpvp.tazpvp.GUI.Guild.GuildInfoGUI;
import net.tazpvp.tazpvp.GUI.Guild.MemberListGUI;
import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import redempt.redlib.commandmanager.CommandHook;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuildCMD {
    @CommandHook("guild_sorttest")
    public void sortest(Player p) {
        new GuildBrowserGUI(p);
    }

    @CommandHook("guild_create") public void onGuildCreate(Player p) {
        GuildUtils.createGuildAnvilGui(p);
    }

    @CommandHook("guild_display") public void onGuildDisplay(Player p) {
        if (GuildUtils.isInGuild(p)) {
            Guild guild = GuildUtils.getGuild(p);
            new GuildInfoGUI(p, guild);
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

    @CommandHook("guild_disband") public void onGuildDisband(Player p) {
        if (GuildUtils.isInGuild(p)) {
            GuildUtils.guildDisband(p, GuildUtils.getGuild(p));
        } else {
            p.sendMessage(GuildUtils.notInG);
        }
    }

}
