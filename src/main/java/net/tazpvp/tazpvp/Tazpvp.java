package net.tazpvp.tazpvp;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Tazpvp extends JavaPlugin {

    public static FileConfiguration configFile;


    public static Tazpvp instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Bukkit.getLogger().info(" Tazpvp has been enabled!");

        configFile = this.getConfig();
        initConfig();

        registerCommands();
        registerEvents();
    }

    public void registerCommands(){

    }

    public void registerEvents(){

    }

    public void initConfig(){
        configFile.options().copyDefaults(true);
        this.saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getLogger().info(" Tazpvp has been disabled!");
    }

    public static Tazpvp getInstance(){
        return instance;
    }
}
