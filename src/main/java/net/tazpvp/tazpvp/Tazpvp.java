package net.tazpvp.tazpvp;

import net.tazpvp.tazpvp.Commands.Player.HelpCMD;
import net.tazpvp.tazpvp.Commands.Player.RulesCmd;
import net.tazpvp.tazpvp.Events.DeathEvent;
import net.tazpvp.tazpvp.Events.JoinEvent;
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
        Bukkit.getLogger().info(" Tazspree has been enabled!");

        configFile = this.getConfig();
        initConfig();

        registerCommands();
        registerEvents();
    }

    public void registerCommands(){
        getCommand("help").setExecutor(new HelpCMD());
        getCommand("rules").setExecutor(new RulesCmd());
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
    }

    public void initConfig(){
        configFile.options().copyDefaults(true);
        this.saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getLogger().info(" Tazspree has been disabled!");
    }

    public static Tazpvp getInstance(){
        return instance;
    }
}
