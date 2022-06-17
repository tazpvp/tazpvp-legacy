package net.tazpvp.tazpvp.Guilds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.*;

public class Guild implements Serializable {
    private final UUID id;
    private String name;
    private String tag;
    private String description;
    private Material icon;
    private Map<UUID, Double> kills;
    private Map<UUID, Double> deaths;
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
        this.kills = new HashMap<>();
        kills.put(owner, 0.0);
        this.deaths = new HashMap<>();
        deaths.put(owner, 0.0);
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
        double total = 0;
        for (Double d : kills.values()) {
            total += d;
        }
        return total;
    }

    public double getKillsPlayer(UUID uuid) {
        if (kills.containsKey(uuid)) {
            return kills.get(uuid);
        }
        return 0;
    }

    public Map<UUID, Double> getKillsMap() {
        return kills;
    }

    public Guild addKill(UUID uuid) {
        this.kills.put(uuid, getKillsPlayer(uuid) + 1);
        return this;
    }

    public double getDeaths() {
        double total = 0;
        for (Double d : deaths.values()) {
            total += d;
        }
        return total;
    }

    public double getDeathsPlayer(UUID uuid) {
        if (deaths.containsKey(uuid)) {
            return deaths.get(uuid);
        }
        return 0;
    }

    public Guild addDeath(UUID uuid) {
        this.deaths.put(uuid, getDeathsPlayer(uuid) + 1);
        return this;
    }

    public Map<UUID, Double> getDeathsMap() {
        return deaths;
    }

    public double getKDR() {
        return getKills() / getDeaths();
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
