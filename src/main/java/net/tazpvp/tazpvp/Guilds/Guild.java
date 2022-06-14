package net.tazpvp.tazpvp.Guilds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Guild implements Serializable {
    private final UUID id;
    private String name;
    private String tag;
    private String description;
    private Material icon;
    private double kills;
    private double deaths;
    private List<UUID> owner;
    private List<UUID> staff;
    private List<UUID> members;
    private List<UUID> invites;

    public Guild(String name, String tag, String description, UUID owner) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.tag = tag;
        this.description = description;
        this.icon = Material.OAK_SIGN;
        this.kills = 0;
        this.deaths = 0;
        this.owner = new ArrayList<>();
        this.owner.add(owner);
        this.staff = new ArrayList<>();
        this.members = new ArrayList<>();
        this.invites = new ArrayList<>();
    }

    public UUID getID() {
        return id;
    }

    public String name() {
        return name;
    }

    public Guild setName(String name) {
        this.name = name;
        return this;
    }

    public String tag() {
        return tag;
    }

    public Guild setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String description() {
        return description;
    }

    public Guild setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<UUID> owner() {
        return owner;
    }

    public Guild setOwner(List<UUID> owner) {
        this.owner = owner;
        return this;
    }

    public List<UUID> staff() {
        return staff;
    }

    public Guild setStaff(List<UUID> staff) {
        this.staff = staff;
        return this;
    }

    public List<UUID> members() {
        return members;
    }

    public Guild setMembers(List<UUID> members) {
        this.members = members;
        return this;
    }

    public List<UUID> invites() {
        return invites;
    }

    public Guild setInvites(List<UUID> invites) {
        this.invites = invites;
        return this;
    }

    public Guild addInvites(UUID uuid) {
        this.invites.add(uuid);
        return this;
    }

    public Guild removeInvites(UUID uuid) {
        this.invites.remove(uuid);
        return this;
    }

    public List<UUID> allMembers() {
        List<UUID> allMembers = new ArrayList<>();
        allMembers.addAll(members);
        allMembers.addAll(staff);
        allMembers.addAll(owner);
        return allMembers;
    }

    public boolean isOwner(UUID uuid) {
        return owner.contains(uuid);
    }

    public boolean isStaff(UUID uuid) {
        return staff.contains(uuid);
    }

    public boolean isMember(UUID uuid) {
        return members.contains(uuid);
    }

    public boolean isInGuild(UUID uuid) {
        return isOwner(uuid) || isStaff(uuid) || isMember(uuid);
    }

    public int getGuildCount() {
        return owner.size() + staff.size() + members.size();
    }

    public void removeFromGuild(UUID uuid) {
        if (isOwner(uuid)) {
            owner.remove(uuid);
        } else if (isStaff(uuid)) {
            staff.remove(uuid);
        } else if (isMember(uuid)) {
            members.remove(uuid);
        }
    }

    public void promote(Player p, UUID uuid) {
        if (isMember(uuid)) {
            members.remove(uuid);
            staff.add(uuid);
            return;
        }
        if (isStaff(uuid)) {
            p.sendMessage("You can't promote a staff of the guild!");
            return;
        }
        if (isOwner(uuid)) {
            p.sendMessage("You can't promote a owner of a guild!");
        }
    }

    public void demote(Player p, UUID uuid) {
        if (isStaff(uuid)) {
            staff.remove(uuid);
            members.add(uuid);
            return;
        }
        if (isMember(uuid)) {
            members.remove(uuid);
            p.sendMessage("You can't demote a member of a guild! You must kick them instead.");
            return;
        }
        p.sendMessage("Owner's cannot be demoted.");
    }

    public Guild addMember(UUID uuid) {
        members.add(uuid);
        return this;
    }

    public double getKills() {
        return kills;
    }

    public Guild setKills(double kills) {
        this.kills = kills;
        return this;
    }

    public double getDeaths() {
        return deaths;
    }

    public Guild setDeaths(double deaths) {
        this.deaths = deaths;
        return this;
    }

    public double getKDR() {
        return kills / deaths;
    }

    public void sendAlL(String msg) {
        for (UUID uuid : allMembers()) {
            Player p = Bukkit.getPlayer(uuid);
            if (p != null) {
                p.sendMessage(msg);
            }
        }
    }

    public boolean hasPerms(OfflinePlayer p) {
        if (isOwner(p.getUniqueId())) {
            return true;
        }
        return isStaff(p.getUniqueId());
    }

    public String getGroup(UUID uuid) {
        if (isOwner(uuid)) {
            return "Owner";
        }
        if (isStaff(uuid)) {
            return "Staff";
        }
        return "Member";
    }

    public Material getIcon() {
        return icon;
    }

    public Guild setIcon(Material icon) {
        this.icon = icon;
        return this;
    }
}
