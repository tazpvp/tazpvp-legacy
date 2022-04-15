package net.tazpvp.tazpvp;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import net.tazpvp.tazpvp.Commands.Admin.*;
import net.tazpvp.tazpvp.Commands.Player.*;
import net.tazpvp.tazpvp.Events.*;
import net.tazpvp.tazpvp.Managers.PunishmentManager;
import net.tazpvp.tazpvp.Managers.StatsManager;
import net.tazpvp.tazpvp.Utils.ConfigGetter;
import net.tazpvp.tazpvp.Utils.Custom.ItemManager.ItemManager;
import net.tazpvp.tazpvp.Utils.MathUtils;
import net.tazpvp.tazpvp.Utils.TipsUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;
import redempt.redlib.commandmanager.ArgType;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.config.ConfigManager;
import redempt.redlib.enchants.EnchantRegistry;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.WeakHashMap;

public final class Tazpvp extends JavaPlugin {
    public static StatsManager statsManager;
    public static PunishmentManager punishmentManager;

    public static boolean isRestarting = false;

    public static Permission permissions;
    public static Chat chat;

    public static FileConfiguration configFile;

    public static Tazpvp instance;

    public static Boolean AllowBlocks = true;

    public static boolean chatMuted = false;

    public static WeakHashMap<UUID, Integer> bounty = new WeakHashMap<>();
    public static WeakHashMap<UUID, UUID> lastDamage = new WeakHashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Bukkit.getLogger().info(" Tazspree has been enabled!");

        statsManager = new StatsManager();
        punishmentManager = new PunishmentManager();

        configFile = this.getConfig();
        initConfig();

        registerEvents();
        registeRedLib();
        ItemManager.init();

        new TipsUtils().Text(this);

        if(getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
            RegisteredServiceProvider<Chat> rsp1 = getServer().getServicesManager().getRegistration(Chat.class);
            if(rsp != null) {
                permissions = rsp.getProvider();
            }
            if(rsp1 != null) {
                chat = rsp1.getProvider();
            }
        } else {
            System.out.println("Vault not found!");
        }
    }


    public void registeRedLib(){
        ArgType<World> worldType = new ArgType<>("world", Bukkit::getWorld).tabStream(c -> Bukkit.getWorlds().stream().map(World::getName));

        new EnchantRegistry(this).registerAll(this);
        new CommandParser(this.getResource("command.rdcml")).setArgTypes(worldType).parse().register("tazpvp", this,
                new StatsCMD(),
                new GuiCMD(),
                new HelpCMD(),
                new AppealCMD(),
                new ReportCMD(),
                new RulesCMD(),
                new ApplyCMD(),
                new initCMD(),
                new RegionCMD(),
                new SpawnCMD(),
                new DiscordCMD(),
                new WarnCMD(),
                new MutechatCMD(),
                new MuteCMD(),
                new EnchantCMD(),
                new WorldCMD(),
                new ADCMD(),
                new NickCMD(),
                new ItemsCMD(),
                new KitCMD(),
                new BountyCMD());


        ConfigManager configManager = ConfigManager.create(this).target(ConfigGetter.class).saveDefaults().load();
    }

    public void registerEvents(){
        regList(new DeathEvent());
        regList(new JoinEvent());
        regList(new SpawnCMD());
        regList(new ChatEvent());
        regList(new MoveEvent());
        regList(new LeaveEvnet());
        regList(new PlaceBlockEvent());
        regList(new DamageEvent());
    }

    public void regList(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void initConfig(){
        configFile.options().copyDefaults(true);
        this.saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        Bukkit.getLogger().info(" Tazspree has been disabled!");

        this.saveConfig();

        statsManager.saveStats();
        punishmentManager.savePunishments();
    }

    public void initScoreboard(Player player) {
        if(!statsManager.scoreboards.containsKey(player.getUniqueId())) {
            statsManager.initScoreboard(player);
        }
        /* if (Tazpvp.punishmentManager.isBanned(player)) {
            Scoreboard sb = statsManager.scoreboards.get(player.getUniqueId());
            Objective objective = sb.getObjective("sb");
            objective.unregister();
            objective = sb.registerNewObjective("sb", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', configFile.getString("branding.sb.name")));
            if (sb.getObjective("showhealth") == null) {
                Objective h = sb.registerNewObjective("showhealth", Criterias.HEALTH);
                h.setDisplaySlot(DisplaySlot.BELOW_NAME);
                h.setDisplayName(ChatColor.RED + "❤");
            }
            Score blank = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "--------------- ");
            blank.setScore(8);
            Score gaming = objective.getScore(" ");
            gaming.setScore(7);
            Score blank222 = objective.getScore(ChatColor.RED + "" + ChatColor.BOLD + "BANNED");
            blank222.setScore(6);
            Score blank2222 = objective.getScore(ChatColor.GRAY +  "﹂/appeal");
            blank2222.setScore(5);
            Score eeee = objective.getScore("");
            eeee.setScore(4);
            //Score tttt = objective.getScore(ChatColor.RED + "Time Left: " + ChatColor.WHITE + (((TazPvP.punishmentManager.getBanDuration(player) - (System.currentTimeMillis()-TazPvP.punishmentManager.getBanTime(player))) / 60000) + 1) + "m");
            //tttt.setScore(4);
            Score blank2 = objective.getScore("  ");
            blank2.setScore(3);
            Score blank4 = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "---------------");
            blank4.setScore(2);
            Score blank5 = objective.getScore(ChatColor.translateAlternateColorCodes('&', configFile.getString("branding.sb.footer")));
            blank5.setScore(1);
            for(Player player1 : Bukkit.getOnlinePlayers()) {
                statsManager.getTeam(player1, sb).addEntry(player1.getName());
            }
            if(permissions.getPrimaryGroup(player).equals("default")) {
                player.setPlayerListName(ChatColor.GRAY + player.getDisplayName());
            } else {
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&',chat.getGroupPrefix((String) null, permissions.getPrimaryGroup(player)) + player.getDisplayName()));
            }
            player.setScoreboard(sb);
        } else { */
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            Double d = (Tazpvp.statsManager.getExpLeft(player));

            Scoreboard sb = statsManager.scoreboards.get(player.getUniqueId());
            Objective objective = sb.getObjective("sb");
            objective.unregister();
            objective = sb.registerNewObjective("sb", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lTazSpree"));
            if (sb.getObjective("showhealth") == null) {
                Objective h = sb.registerNewObjective("showhealth", Criterias.HEALTH);
                h.setDisplaySlot(DisplaySlot.BELOW_NAME);
                h.setDisplayName(ChatColor.RED + "❤");
            }
            Score blank = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "--------------- ");
            blank.setScore(14);
            Score blank1 = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "PLAYER");
            blank1.setScore(13);
            Score level = objective.getScore(ChatColor.DARK_AQUA + "〡 Level  " + ChatColor.GRAY + Tazpvp.statsManager.getLevel(player));
            level.setScore(12);
            Score money = objective.getScore(ChatColor.DARK_AQUA + "〡 Money  " + ChatColor.GRAY +  "$"+ Tazpvp.statsManager.getMoney(player));
            money.setScore(11);
            Score points = objective.getScore(ChatColor.DARK_AQUA + "〡 Points  " + ChatColor.GRAY + Tazpvp.statsManager.getShards(player));
            points.setScore(10);
            Score exp = objective.getScore(ChatColor.DARK_AQUA + "〡 EXP  " + ChatColor.GRAY + "" + Tazpvp.statsManager.getExp(player) + ChatColor.DARK_GRAY + "/" + df.format(d));
            //Score credits = objective.getScore(ChatColor.AQUA + "▷ Credits  " + ChatColor.GRAY + TazPvP.statsManager.getCredits(player));
            exp.setScore(9);
            Score blank2 = objective.getScore("");
            blank2.setScore(8);
            Score blank3 = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "STATS");
            blank3.setScore(7);
            Score streak = objective.getScore(ChatColor.RED + "〡 Streak  " + ChatColor.GRAY + Tazpvp.statsManager.getStreak(player));
            streak.setScore(6);
            Score kills = objective.getScore(ChatColor.RED + "〡 Kills  " + ChatColor.GRAY + Tazpvp.statsManager.getKills(player));
            kills.setScore(5);
            Score deaths = objective.getScore(ChatColor.RED + "〡 Deaths  " + ChatColor.GRAY + Tazpvp.statsManager.getDeaths(player));
            deaths.setScore(4);
            Score kdr = objective.getScore(ChatColor.RED + "〡 KDR  " + ChatColor.GRAY + ((Tazpvp.statsManager.getDeaths(player) > 0) ?  MathUtils.round((float) Tazpvp.statsManager.getKills(player) / Tazpvp.statsManager.getDeaths(player), 2) : Tazpvp.statsManager.getKills(player)));
            kdr.setScore(3);
            Score blank4 = objective.getScore(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "---------------");
            blank4.setScore(2);
            Score blank5 = objective.getScore(ChatColor.GRAY + "〡 tazpvp.net");
            blank5.setScore(1);
            for(Player player1 : Bukkit.getOnlinePlayers()) {
                statsManager.getTeam(player1, sb).addEntry(player1.getName());
            }
            if(permissions.getPrimaryGroup(player).equals("default")) {
                player.setPlayerListName(ChatColor.GRAY + player.getDisplayName());
            } else {
                player.setPlayerListName(ChatColor.translateAlternateColorCodes('&',chat.getGroupPrefix((String) null, permissions.getPrimaryGroup(player)) + player.getDisplayName()));
            }
            player.setScoreboard(sb);

    }

    public static Tazpvp getInstance(){
        return instance;
    }
}
