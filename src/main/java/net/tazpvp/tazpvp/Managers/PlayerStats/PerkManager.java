package net.tazpvp.tazpvp.Managers.PlayerStats;

import net.tazpvp.tazpvp.Tazpvp;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PerkManager {
    public final FileConfiguration statsFile;
    final File file;

    public PerkManager(){
        file = new File(Tazpvp.getInstance().getDataFolder() + "/perks.yml");
        statsFile = YamlConfiguration.loadConfiguration(file);
    }

    public void initPerks(OfflinePlayer p){
        statsFile.set(p.getUniqueId().toString()+".hasClickedMiner", false);
    }

    public boolean getFatPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".fatPerk"); }
    public void setFatPerk(Player p, boolean type){
        if (getFatPerk(p)) { p.sendMessage("You already have this perk!"); return;}
        statsFile.set(p.getUniqueId().toString()+".fatPerk", type);}

    public boolean getExcavatorPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".excavatorPerk"); }
    public void setExcavatorPerk(Player p, boolean type){
        if (getExcavatorPerk(p)) { p.sendMessage("You already have this perk!"); return;}
        statsFile.set(p.getUniqueId().toString()+".excavatorPerk", type);}

    public boolean getArcherPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".archerPerk"); }
    public void setArcherPerk(Player p, boolean type){
        if (getArcherPerk(p)) { p.sendMessage("You already have this perk!"); return;}
        statsFile.set(p.getUniqueId().toString()+".archerPerk", type);}

    public boolean getBuilderPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".builderPerk"); }
    public void setBuilderPerk(Player p, boolean type){
        if (getBuilderPerk(p)) { p.sendMessage("You already have this perk!"); return;}
        statsFile.set(p.getUniqueId().toString()+".builderPerk", type); }

    public boolean getGobblePerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".gobblePerk"); }
    public void setGobblePerk(Player p, boolean type){
        if (getGobblePerk(p)) { p.sendMessage("You already have this perk!"); return;}
        statsFile.set(p.getUniqueId().toString()+".gobblePerk", type); }

    public boolean getAgilityPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".agilityPerk"); }
    public void setAgilityPerk(Player p, boolean type){
        if (getAgilityPerk(p)) { p.sendMessage("You already have this perk!"); return;}
        statsFile.set(p.getUniqueId().toString()+".agilityPerk", type); }

    public boolean getTankPerk(Player p){ return statsFile.getBoolean(p.getUniqueId().toString()+".tankPerk"); }
    public void setTankPerk(Player p, boolean type){
        if (getTankPerk(p)) { p.sendMessage("You already have this perk!"); return;}
        statsFile.set(p.getUniqueId().toString()+".tankPerk", type); }

    public void saveStats(){
        try {
            statsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
