package net.tazpvp.tazpvp;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import net.tazpvp.tazpvp.Commands.Admin.*;
import net.tazpvp.tazpvp.Commands.CommandListener;
import net.tazpvp.tazpvp.Commands.Player.*;
import net.tazpvp.tazpvp.DiscordBot.StartBotThread;
import net.tazpvp.tazpvp.Duels.DuelLogic;
import net.tazpvp.tazpvp.Duels.WorldUtils.WorldManageent;
import net.tazpvp.tazpvp.Events.ChatEvents.ChatEvent;
import net.tazpvp.tazpvp.Events.ChatEvents.PlayerCommandPreprocessEvent;
import net.tazpvp.tazpvp.Events.DamageEvents.DamageEvent;
import net.tazpvp.tazpvp.Events.DamageEvents.DeathEvent;
import net.tazpvp.tazpvp.Events.ItemEvents.ItemPickUpEvent;
import net.tazpvp.tazpvp.Events.MouseEvents.InteractEvent;
import net.tazpvp.tazpvp.Events.MouseEvents.NPCEvent;
import net.tazpvp.tazpvp.Events.PhysicalEvents.BlockBreakEvent;
import net.tazpvp.tazpvp.Events.PhysicalEvents.BlockPlaceEvent;
import net.tazpvp.tazpvp.Events.PhysicalEvents.MoveEvent;
import net.tazpvp.tazpvp.Events.ServerEvents.JoinEvent;
import net.tazpvp.tazpvp.Events.ServerEvents.LeaveEvnet;
import net.tazpvp.tazpvp.Managers.*;
import net.tazpvp.tazpvp.Managers.PlayerWrapperManagers.PlayerWrapper;
import net.tazpvp.tazpvp.Managers.YamlStats.*;
import net.tazpvp.tazpvp.NPCS.NpcUtils;
import net.tazpvp.tazpvp.NPCS.Villagers;
import net.tazpvp.tazpvp.Passive.Generator;
import net.tazpvp.tazpvp.Passive.Tips;
import net.tazpvp.tazpvp.Utils.ASCIIArtUtil;
import net.tazpvp.tazpvp.Utils.ConfigGetter;
import net.tazpvp.tazpvp.Utils.MathUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import redempt.redlib.RedLib;
import redempt.redlib.commandmanager.ArgType;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.config.ConfigManager;
import redempt.redlib.enchants.EnchantRegistry;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public final class Tazpvp extends JavaPlugin {
    public static StatsManager statsManager;
    public static BoolManager boolManager;
    public static PunishmentManager punishmentManager;
    public static PlayerWrapperStatsManager playerWrapperStatsManager;
    public static HashMap<UUID, PlayerWrapper> playerWrapperMap = new HashMap<>();
    public static AchievementManager achievementManager;
    public static EnderChestManager enderChestManager;
    public static DuelLogic duelLogic;

    public static boolean isRestarting = false;

    public static Permission permissions;
    public static Chat chat;
    public static ProtocolManager protocolManager;

    public static FileConfiguration configFile;

    public static Tazpvp instance;

    public static Boolean AllowBlocks = true;

    public static boolean chatMuted = false;

    public static WeakHashMap<UUID, Integer> bounty = new WeakHashMap<>();
    public static WeakHashMap<UUID, UUID> lastDamage = new WeakHashMap<>();

    public static HashMap<Material, Material> blocks = new HashMap<Material, Material>();
    public static HashMap<Material, Integer> sellables = new HashMap<Material, Integer>();
    public static WeakHashMap<UUID, Long> hasBeenReported = new WeakHashMap<>();

    @Override
    public void onLoad() {
        for (World world : Bukkit.getWorlds()) {
            if (world.getName().startsWith("active_duel")) {
                new WorldManageent().deleteWorld(world.getName());
            }
        }
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getLogger().info(ASCIIArtUtil.getArt("      Loading...       "));


        managers(true);

        configFile = this.getConfig();
        initConfig();

        try {
            registerEvents();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            registeRedLib();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        StartBotThread thread = new StartBotThread();
        thread.start();

        new Generator().generator(this);
        new Tips().Text(this);

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

        protocolManager = ProtocolLibrary.getProtocolManager();

        doHashMaps();

        NpcUtils.spawnAll();


        new BukkitRunnable() {
            @Override
            public void run() {
                CombatLogManager.tick();
            }
        }.runTaskTimerAsynchronously(this, 20L, 20L);

    }
    public void registeRedLib() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArgType<World> worldType = new ArgType<>("world", Bukkit::getWorld).tabStream(c -> Bukkit.getWorlds().stream().map(World::getName));

//        new EnchantRegistry(this).registerAll(this);
//
//        ArrayList<CommandListener> listes = new ArrayList<>();
//        for (Class<? extends CommandListener> listener : RedLib.getExtendingClasses(this, CommandListener.class)) {
//            listes.add(listener.getConstructor().newInstance());
//        }
//        getLogger().info(listes.toString());

        new CommandParser(this.getResource("command.rdcml")).setArgTypes(worldType).parse().register("tazpvp", this,
                new StatsCMD(),
                new GuiCMD(),
                new ReportCMD(),
                new RegionCMD(),
                new SpawnCMD(),
                new DiscordCMD(),
                new WarnCMD(),
                new MutechatCMD(),
                new MuteCMD(),
                new EnchantCMD(),
                new WorldCMD(),
                new ItemsCMD(),
                new KitCMD(),
                new BountyCMD(),
                new TrollCMD(),
                new NpcCMD(),
                new PWCMD(),
                new BanCMD(),
                new ECCMD(),
                new HoloCMD(),
                new DuelCMD(),
                new BalCMD());

        ConfigManager configManager = ConfigManager.create(this).target(ConfigGetter.class).saveDefaults().load();
    }

    public void registerEvents() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<? extends Listener> listener : RedLib.getExtendingClasses(this, Listener.class)) {
            regList(listener.getConstructor().newInstance());
        }
//        regList(new ChatEvent());
//        regList(new PlayerCommandPreprocessEvent());
//        regList(new DamageEvent());
//        regList(new DeathEvent());
//        regList(new ItemPickUpEvent());
//        regList(new InteractEvent());
//        regList(new NPCEvent());
//        regList(new BlockBreakEvent());
//        regList(new BlockPlaceEvent());
//        regList(new MoveEvent());
//        regList(new JoinEvent());
//        regList(new LeaveEvnet());
    }

    public void regList(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void initConfig(){
        configFile.options().copyDefaults(true);
        this.saveConfig();
    }

    public void managers(boolean init) {
        if (init) {
            statsManager = new StatsManager();
            boolManager = new BoolManager();
            punishmentManager = new PunishmentManager();
            playerWrapperStatsManager = new PlayerWrapperStatsManager();
            achievementManager = new AchievementManager();
            enderChestManager = new EnderChestManager();
            duelLogic = new DuelLogic();
        } else {
            statsManager.saveStats();
            boolManager.saveStats();
            punishmentManager.savePunishments();
            playerWrapperStatsManager.saveStats();
            achievementManager.saveStats();
            enderChestManager.saveStats();
        }
    }

    public void doHashMaps() {
        blocks.put(Material.DEEPSLATE_GOLD_ORE, Material.RAW_GOLD);
        blocks.put(Material.DEEPSLATE_IRON_ORE, Material.RAW_IRON);
        blocks.put(Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE);
        blocks.put(Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_LAZULI);
        blocks.put(Material.DEEPSLATE_EMERALD_ORE, Material.EMERALD);

        sellables.put(Material.DEEPSLATE_GOLD_ORE, 1);
        sellables.put(Material.DEEPSLATE_IRON_ORE, 1);
        sellables.put(Material.DEEPSLATE_REDSTONE_ORE, 1);
        sellables.put(Material.DEEPSLATE_LAPIS_ORE, 1);
        sellables.put(Material.DEEPSLATE_EMERALD_ORE, 1);
        sellables.put(Material.RAW_GOLD, 1);
        sellables.put(Material.RAW_IRON, 1);
        sellables.put(Material.REDSTONE, 1);
        sellables.put(Material.LAPIS_LAZULI, 1);
        sellables.put(Material.EMERALD, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ASCIIArtUtil.getArt("      Disabling...     "));

        this.saveConfig();

        managers(false);
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerWrapperStatsManager.setPlayerWrapper(player, playerWrapperMap.get(player.getUniqueId()));
        }

        NpcUtils.removeAll();
    }

    public void initScoreboard(Player player) {
        if (!statsManager.scoreboards.containsKey(player.getUniqueId())) {
            statsManager.initScoreboard(player);
        }
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        Double d = (Tazpvp.statsManager.getExpLeft(player));

        Scoreboard sb = statsManager.scoreboards.get(player.getUniqueId());
        Objective objective = sb.getObjective("sb");
        assert objective != null;
        objective.unregister();
        objective = sb.registerNewObjective("sb", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3&lTAZPVP.NET"));
        if (sb.getObjective("showhealth") == null) {
            Objective h = sb.registerNewObjective("showhealth", Criterias.HEALTH);
            h.setDisplaySlot(DisplaySlot.BELOW_NAME);
            h.setDisplayName(ChatColor.RED + "❤");
        }
        Score blank = objective.getScore(ChatColor.DARK_AQUA + "                         ");
        blank.setScore(14);
        Score blank1 = objective.getScore(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "WEALTH");
        blank1.setScore(13);
        Score level = objective.getScore(ChatColor.AQUA + "〡 Level  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getLevel(player));
        level.setScore(12);
        Score money = objective.getScore(ChatColor.AQUA + "〡 Money  " + ChatColor.DARK_AQUA + "$" + Tazpvp.statsManager.getMoney(player));
        money.setScore(11);
        Score points = objective.getScore(ChatColor.AQUA + "〡 Shards  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getShards(player));
        points.setScore(10);
        Score exp = objective.getScore(ChatColor.AQUA + "〡 EXP  " + ChatColor.DARK_AQUA + "" + Tazpvp.statsManager.getExp(player) + ChatColor.GRAY + "/" + df.format(d));
        //Score credits = objective.getScore(ChatColor.AQUA + "▷ Credits  " + ChatColor.GRAY + TazPvP.statsManager.getCredits(player));
        exp.setScore(9);
        Score blank2 = objective.getScore("");
        blank2.setScore(8);
        Score blank3 = objective.getScore(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "STATS");
        blank3.setScore(7);
        Score streak = objective.getScore(ChatColor.AQUA + "〡 Streak  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getStreak(player));
        streak.setScore(6);
        Score kills = objective.getScore(ChatColor.AQUA + "〡 Kills  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getKills(player));
        kills.setScore(5);
        Score deaths = objective.getScore(ChatColor.AQUA + "〡 Deaths  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getDeaths(player));
        deaths.setScore(4);
        Score kdr = objective.getScore(ChatColor.AQUA + "〡 KDR  " + ChatColor.DARK_AQUA + ((Tazpvp.statsManager.getDeaths(player) > 0) ? MathUtils.round((float) Tazpvp.statsManager.getKills(player) / Tazpvp.statsManager.getDeaths(player), 2) : Tazpvp.statsManager.getKills(player)));
        kdr.setScore(3);
        Score blank4 = objective.getScore(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "");
        blank4.setScore(2);
        Score blank5 = objective.getScore(ChatColor.GRAY + "〡 tazpvp.net");
        blank5.setScore(1);
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            statsManager.getTeam(player1, sb).addEntry(player1.getName());
        }
        if (permissions.getPrimaryGroup(player).equals("default")) {
            player.setPlayerListName(ChatColor.GRAY + player.getDisplayName());
        } else {
            player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', chat.getGroupPrefix((String) null, permissions.getPrimaryGroup(player)) + player.getDisplayName()));
        }
        player.setScoreboard(sb);
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            setNametag(onlinePlayers);
        }

    }

    public void setNametag(Player player1) {
        Scoreboard scoreboard = player1.getScoreboard();
        if (scoreboard.getTeam(player1.getUniqueId().toString()) != null) {
            scoreboard.getTeam(player1.getUniqueId().toString()).unregister();
        }
        Team team = scoreboard.registerNewTeam(player1.getUniqueId().toString());
        team.setPrefix(ChatColor.translateAlternateColorCodes('&', "eee"));
        scoreboard.getTeam(player1.getUniqueId().toString()).addPlayer(player1);
    }

    public static void sendBaseTablist(Player p) {
        p.setPlayerListHeaderFooter(
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TAZPVP.NET\n",
                ChatColor.GOLD + "                                 \nTPS: " + ChatColor.YELLOW + "20" + "\n" + ChatColor.AQUA + Bukkit.getOnlinePlayers().size() + ChatColor.GRAY + "/" + ChatColor.DARK_AQUA + Bukkit.getMaxPlayers()
        );

    }

    public static Tazpvp getInstance(){
        return instance;
    }
}
