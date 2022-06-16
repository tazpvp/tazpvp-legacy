package net.tazpvp.tazpvp.Guilds;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.tazpvp.tazpvp.Tazpvp;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class GuildUtils {
    public final static String gNameTaken = "Guild name already taken.";
    public final static String alrdyInG = "You are already in a guild.";
    public final static String gCreated = "Guild created.";
    public final static String notInG = "You are not in a guild.";
    public final static String noPermission = "You do not have permission to do that.";
    public final static String targetNotInG = "That player is not in a guild.";
    public final static String targetNotInUrG = "That player is not in your guild.";
    public final static String alrdyInUrG = "That player is already in your guild.";
    public final static String targetAlrdyInG = "That player is already in a guild.";
    public final static String noGInv = "You have no pending guild invites.";
    public final static String wasPromo = " was promoted to ";
    public final static String wasDemo = " was demoted to ";
    public final static String wasKicked = " was kicked from the guild.";
    public final static String leavedG = " left the guild.";
    public final static String guildFull = "Guild is full.";
    public final static String gDisband = "The guild has been disbanded!";
    public final static String gOwnerLeave = "You are the owner of this guild and cannot leave it, You may disband it permanently though.";
    public final static String targetAlrdyInv = "That player is already invited to your guild.";
    public final static String badWord = "Bro, that offending";
    public final static ChatColor primaryColor = ChatColor.WHITE;
    public final static ChatColor secondaryColor = ChatColor.GRAY;
    public final static int maxSize = 15;

    /**
     * Creates a guild
     * @param p the player who created the guld
     * @param name the name of the guild, will accept {@code String[]}
     * @param description the description of the guild
     */
    public static void createGuild(Player p, String name, String description) {
        if (isInGuild(p)) {
            p.sendMessage(alrdyInG);
            return;
        }
        if (Tazpvp.guildManager.getTakeNames().contains(name)) {
            p.sendMessage(gNameTaken);
            return;
        }

        Guild guild = new Guild(name, null, description, p.getUniqueId());
        Tazpvp.guildManager.addGuild(guild);
        Tazpvp.guildManager.setPlayerGuild(p, guild.getID());
        Tazpvp.guildManager.addTakeName(name);
        p.sendMessage(gCreated);
    }

    public static void createGuildAnvilGui(Player p) {
        if (isInGuild(p)) {
            p.sendMessage(alrdyInG);
            return;
        }
        if (Tazpvp.statsManager.getCoins(p) < 6000 || Tazpvp.statsManager.getShards(p) < 10) {
            p.sendMessage("You need 6000 coins and 10 shards to create a guild.");
            return;
        }
        getNameAnvil(p);
    }

    public static String[] getNameAnvil(Player p) {
        new AnvilGUI.Builder()
                .onComplete((player, text) -> {
                    if (text.startsWith(">")) {
                        text = text.replaceFirst(">", "");
                    }
                    if (Tazpvp.guildManager.getTakeNames().contains(text)) {
                        p.sendMessage(gNameTaken);
                        return AnvilGUI.Response.close();
                    }

                    if (GuildConfig.isOffending(text)) {
                        player.sendMessage(badWord);
                        return AnvilGUI.Response.close();
                    }

                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

                    String name = text;
                    getDescriptionAnvil(player, name);


                    return AnvilGUI.Response.close();
                })
                .onClose(player -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                })
                .text(">")
                .itemLeft(new ItemBuilder(Material.NAME_TAG).setName(ChatColor.GREEN + "Guild Creation"))
                .title(ChatColor.YELLOW + "Guild Name:")
                .plugin(Tazpvp.getInstance())
                .open(p);
        return null;
    }

    public static void getDescriptionAnvil(Player p, String name) {
        new AnvilGUI.Builder()
                .onComplete((player, text) -> {
                    if (text.startsWith(">")) {
                        text = text.replaceFirst(">", "");
                    }

                    if (GuildConfig.isOffending(text)) {
                        player.sendMessage(badWord);
                        return AnvilGUI.Response.close();
                    }

                    String description = text;
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

                    Tazpvp.statsManager.addCoins(p, -6000);
                    Tazpvp.statsManager.addShards(p, -10);

                    Guild guild = new Guild(name, null, description, p.getUniqueId());
                    Tazpvp.guildManager.addGuild(guild);
                    Tazpvp.guildManager.setPlayerGuild(p, guild.getID());
                    Tazpvp.guildManager.addTakeName(name);
                    p.sendMessage(gCreated);
                    return AnvilGUI.Response.close();
                })
                .onClose(player -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                })
                .text(">")
                .itemLeft(new ItemBuilder(Material.NAME_TAG).setName(ChatColor.GREEN + "Guild Creation"))
                .title(ChatColor.YELLOW + "Guild Description:")
                .plugin(Tazpvp.getInstance())
                .open(p);

    }

    public static String viewGuildString(Guild guild) {
        StringBuilder sb = new StringBuilder();
        sb.append(secondaryColor).append(guild.description()).append(primaryColor + "-").append(uuidToOfflinePlayer(guild.owner().get(0)).getName()).append("\n").append("\n").append(secondaryColor + "Kills: " + primaryColor).append("\n").append(guild.getKills()).append(secondaryColor + "Members: " + primaryColor).append(guild.allMembers().size());
        return sb.toString();
    }

    /**
     * Gets information about specified guild
     * @param guild The Guild object
     * @return A {@code String} containing the guild information
     */
    public static List<String> viewGuild(Guild guild) {
        return guildReturn(guild);
    }

    /**
     * Gets information about specified guild
     * @param gUUID The UUID of the guild
     * @return A {@code String} containing the guild information
     */
    public static List<String> viewGuild(UUID gUUID) {
        return guildReturn(Tazpvp.guildManager.getGuild(gUUID));
    }

    private static List<String> guildReturn(Guild guild) {
        List<String> guildInfo = new ArrayList<>();
        guildInfo.add(secondaryColor + guild.description());
        guildInfo.add(primaryColor + "-" + uuidToOfflinePlayer(guild.owner().get(0)).getName());
        guildInfo.add(secondaryColor + "Kills: " + primaryColor + guild.getKills());
        guildInfo.add(secondaryColor + "Members: " + primaryColor + guild.allMembers().size());
        return guildInfo;
    }

    public static OfflinePlayer uuidToOfflinePlayer(UUID uuid) {
        return Bukkit.getOfflinePlayer(uuid);
    }

    /**
     * Checks if player is in a guild
     * @param p The player
     * @return {@code true} if player is in a guild, {@code false} if not
     */
    public static boolean isInGuild(Player p) {
        return Tazpvp.guildManager.getPlayerGuild(p) != null;
    }

    /**
     * Leaves a guild, will promote staff if leader leaves.
     * @param p The player leaving the guild
     * @param guild The guild {@code p} is leaving
     */
    public static void leaveGuild(Player p, Guild guild) {
        if (!isInGuild(p)) {
            p.sendMessage(notInG);
            return;
        }

        if (guild.owner().contains(p.getUniqueId())) {
            p.sendMessage(gOwnerLeave);
            return;
        }

        guild.sendAlL(p.getName() + leavedG);
        guild.removeFromGuild(p.getUniqueId());
        Tazpvp.guildManager.removePlayerGuild(p);
        if (guild.getGuildCount() == 0) {
            Tazpvp.guildManager.removeGuild(guild.getID());
            Tazpvp.guildManager.removeTakeName(guild.name());
            return;
        }
        if (guild.owner().size() == 0) {
            guild.promote(p, (guild.staff().size()  > 0) ? guild.staff().get(0) : guild.allMembers().get(0));
        }
        Tazpvp.guildManager.setGuild(guild.getID(), guild);
    }

    /**
     * Kick a player from a guild.
     * @param p The player who is kicking {@code target}.
     * @param target The player being kicked by {@code p}.
     * @param guild The guild that {@code p} is kicking {@code target} from.
     */
    public static void kickFromGuild(Player p, OfflinePlayer target, Guild guild) {
        if (!isInGuild(p)) {
            p.sendMessage(notInG);
            return;
        }

        if (!guild.hasPerms(p)) {
            p.sendMessage(noPermission);
            return;
        }
        if (guild.owner().contains(target.getUniqueId())) {
            p.sendMessage(noPermission);
            return;
        }

        if (!guild.allMembers().contains(target.getUniqueId())) {
            p.sendMessage(targetNotInUrG);
            return;
        }
        if (guild.staff().contains(target.getUniqueId()) && guild.staff().contains(p.getUniqueId())) {
            p.sendMessage(noPermission);
            return;
        }

        guild.sendAlL(target.getName() + wasKicked);
        guild.removeFromGuild(target.getUniqueId());
        Tazpvp.guildManager.removePlayerGuild(target);
        Tazpvp.guildManager.setGuild(guild.getID(), guild);
    }

    /**
     * Promotes a player to a new rank, will only work if {@code target} is a member.
     * @param p The player who is promoting.
     * @param target The player who is being promoted.
     * @param guild The guild that {@code p} is promoting in.
     */
    public static void promote(Player p, OfflinePlayer target, Guild guild) {
        if (!isInGuild(p)) {
            p.sendMessage(notInG);
            return;
        }

        if (!guild.hasPerms(p)) {
            p.sendMessage(noPermission);
            return;
        }

        if (!guild.allMembers().contains(target.getUniqueId())) {
            p.sendMessage(targetNotInUrG);
            return;
        }

        guild.promote(p, target.getUniqueId());
        guild.sendAlL(target.getName() + wasPromo + guild.getGroup(target.getUniqueId()));
        Tazpvp.guildManager.setGuild(guild.getID(), guild);
    }

    /**
     * Demotes a player's ranking in a guild. Will only work if {@code target} is a Staff member.
     * @param p The player who is demoting {@code target}
     * @param target The Player who is being demoted.
     * @param guild The guild to demote {@code target} from.
     */
    public static void demote(Player p, OfflinePlayer target, Guild guild) {
        if (!isInGuild(p)) {
            p.sendMessage(notInG);
            return;
        }

        if (!guild.hasPerms(p)) {
            p.sendMessage(noPermission);
            return;
        }

        if (!guild.allMembers().contains(target.getUniqueId())) {
            p.sendMessage(targetNotInUrG);
            return;
        }

        guild.demote(p, target.getUniqueId());
        guild.sendAlL(target.getName() + wasDemo + guild.getGroup(target.getUniqueId()));
        Tazpvp.guildManager.setGuild(guild.getID(), guild);
    }

    /**
     * Sends a guild invite to {@code target}
     * @param p The player who sent the invite.
     * @param target The player who is receiving the guild invite.
     * @param guild The guild that {@code p} is inviting {@code target} to.
     */
    public static void invite(Player p, Player target, Guild guild) {
        if (!isInGuild(p)) {
            p.sendMessage(notInG);
            return;
        }
        if (!guild.hasPerms(p)) {
            p.sendMessage(noPermission);
            return;
        }

        if (guild.allMembers().size() >= maxSize) {
            p.sendMessage(guildFull);
            return;
        }

        if (guild.allMembers().contains(target.getUniqueId())) {
            p.sendMessage(alrdyInUrG);
            return;
        }

        if (isInGuild(target)) {
            p.sendMessage(targetAlrdyInG);
            return;
        }

        if (guild.invites().contains(target.getUniqueId())) {
            p.sendMessage(targetAlrdyInv);
            return;
        }

        guild.addInvites(target.getUniqueId());
        Tazpvp.guildManager.setGuild(guild.getID(), guild);

        TextComponent invite = new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "Click Here " + ChatColor.YELLOW + "to join " + guild.name());
        invite.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + "Click Here " + ChatColor.YELLOW + "to join " + guild.name()).create()));
        invite.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/guild accept"));

        guild.sendAlL(primaryColor + target.getName() + " has been invited to join " + guild.name() + " by " + p.getName());
        target.spigot().sendMessage(invite);
        target.setMetadata("guildInvite", new FixedMetadataValue(Tazpvp.getInstance(), guild.getID()));
    }

    /**
     * Checks if {@code p} has a valid invite to accept.
     * @param p the Player who we are checking.
     */
    public static void acceptInvite(Player p) {
        if (isInGuild(p)) {
            p.sendMessage(alrdyInG);
            return;
        }

        if (!p.hasMetadata("guildInvite")) {
            p.sendMessage(noGInv);
            return;
        }
        UUID gUUID = UUID.fromString(p.getMetadata("guildInvite").get(0).asString());
        Guild guild = Tazpvp.guildManager.getGuild(gUUID);
        guild.removeInvites(p.getUniqueId());
        guild.addMember(p.getUniqueId());
        Tazpvp.guildManager.setPlayerGuild(p, gUUID);
        Tazpvp.guildManager.setGuild(gUUID, guild);
        p.removeMetadata("guildInvite", Tazpvp.getInstance());
        guild.sendAlL(primaryColor + p.getName() + " has joined " + guild.name());
    }

    public static Guild acceptInviteChecks(Player p) {
        if (isInGuild(p)) {
            p.sendMessage(alrdyInG);
            return null;
        }

        if (!p.hasMetadata("guildInvite")) {
            p.sendMessage(noGInv);
            return null;
        }
        UUID gUUID = UUID.fromString(p.getMetadata("guildInvite").get(0).asString());
        Guild guild = Tazpvp.guildManager.getGuild(gUUID);
        return guild;
    }

    /**
     * Checks if {@code p} is in the same guild as {@code target}.
     * @param p The player who is in the guild.
     * @param target the target to check if in the guild of p.
     * @return {@code true} if in the same guild, {@code false} if not or not in a guild entirely.
     */
    public static boolean areInSameGuild(Player p, Player target) {
        if (!isInGuild(p)) {
            return false;
        }
        if (!isInGuild(target)) {
            return false;
        }
        return Tazpvp.guildManager.getPlayerGuild(p).equals(Tazpvp.guildManager.getPlayerGuild(target));
    }

    public static LinkedHashMap<UUID, Double> sortByValue(HashMap<UUID, Double> hm) {
        List<Map.Entry<UUID, Double> > list = new LinkedList<>(hm.entrySet());
        list.sort(Map.Entry.<UUID,Double>comparingByValue().reversed());

        LinkedHashMap<UUID, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<UUID, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static Guild getGuild(Player p) {
        return Tazpvp.guildManager.getGuild(Tazpvp.guildManager.getPlayerGuild(p));
    }

    public static Guild getGuild(UUID uuid) {
        return Tazpvp.guildManager.getGuild(uuid);
    }

    /**
     * Checks if {@code p} is in a guild and adds one kill to the guild's leaderboard.
     * @param p The player to check.
     */
    public static void addKillToGuild(Player p) {
        if (!isInGuild(p)) {
            return;
        }
        Guild g = getGuild(p);
        g.setKills(g.getKills() + 1);
        Tazpvp.guildManager.setGuild(g.getID(), g);
    }

    /**
     * Checks if {@code p} is in a guild and adds one death to the guild's leaderboard.
     * @param p The player to check.
     */
    public static void addDeathToGuild(Player p) {
        if (!isInGuild(p)) {
            return;
        }
        Guild g = getGuild(p);
        g.setDeaths(g.getDeaths() + 1);
        Tazpvp.guildManager.setGuild(g.getID(), g);
    }

    public static void guildDisband(Player p, Guild g) {
        if (!g.isOwner(p.getUniqueId())) {
            p.sendMessage(noPermission);
            return;
        }

        for (UUID uuid : g.allMembers()) {
            OfflinePlayer op = Bukkit.getOfflinePlayer(uuid);
            if (op.isOnline()) op.getPlayer().sendMessage(gDisband);
            Tazpvp.guildManager.removePlayerGuild(op);
        }
        Tazpvp.guildManager.removeGuild(g.getID());
        Tazpvp.guildManager.removeTakeName(g.name());
        Tazpvp.guildManager.removeTakenTag(g.tag());

    }

    public static LinkedHashMap<UUID, Double> sortedGuilds() {
        LinkedHashMap<UUID, Double> map = new LinkedHashMap<>();
        for (UUID uuid : Tazpvp.guildManager.getAllGuilds()) {
            map.put(uuid, Tazpvp.guildManager.getGuild(uuid).getKills());
        }
        return GuildUtils.sortByValue(map);
    }

    public static void guildChatMessage(Player p, String msg, Guild g) {
        String guild = ChatColor.GREEN + "Guild > ";
        String beginning;
        if (g.getGroup(p.getUniqueId()).equals("Owner")) {
            beginning = ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "GUILD MASTER " + ChatColor.DARK_GREEN + p.getName() + " ";
        } else if (g.getGroup(p.getUniqueId()).equals("Staff")) {
            beginning = ChatColor.GREEN + "" + ChatColor.BOLD + "STAFF " + ChatColor.GREEN + p.getName() + " ";
        } else {
            beginning = ChatColor.GRAY + p.getName() + " ";
        }
        g.sendAlL(guild + beginning + ChatColor.WHITE + msg);
    }

    public static void hijack(Player p, Player target, Guild g) {
        List<UUID> own = new ArrayList<>(g.owner());
        List<UUID> newOwner = new LinkedList<>();
        newOwner.add(p.getUniqueId());
        newOwner.addAll(own);
        g.owner().clear();
        g.setOwner(newOwner);
        g.sendAlL(ChatColor.RED + "The guild has been hijacked by " + p.getName() + "!");
        Tazpvp.guildManager.setGuild(g.getID(), g);
        Tazpvp.guildManager.setPlayerGuild(p, g.getID());
    }

    public static void wipeAllGuildInvites() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (isInGuild(p)) {
                Guild g = getGuild(p);
                g.invites().clear();
                Tazpvp.guildManager.setGuild(g.getID(), g);
            }
        }
        Bukkit.getLogger().info("All guild invites wiped.");
    }
}
