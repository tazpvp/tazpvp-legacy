package net.tazpvp.tazpvp;

import net.dv8tion.jda.api.JDA;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import net.tazpvp.tazpvp.Commands.Admin.*;
import net.tazpvp.tazpvp.Commands.Player.*;
import net.tazpvp.tazpvp.Cosmetics.Particle.ParticleUtil;
import net.tazpvp.tazpvp.Cosmetics.Particle.Particles.StaticSpingParticle;
import net.tazpvp.tazpvp.DiscordBot.StartBotThread;
import net.tazpvp.tazpvp.Duels.DuelLogic;
import net.tazpvp.tazpvp.Duels.WorldUtils.WorldManageent;
import net.tazpvp.tazpvp.Events.*;
import net.tazpvp.tazpvp.Guilds.GuildCMD;
import net.tazpvp.tazpvp.Guilds.GuildManager;
import net.tazpvp.tazpvp.Guilds.GuildUtils;
import net.tazpvp.tazpvp.Managers.CombatTag;
import net.tazpvp.tazpvp.Managers.EnderChestManager;
import net.tazpvp.tazpvp.Managers.PlayerStats.*;
import net.tazpvp.tazpvp.Managers.PlayerWrapperManagers.PlayerWrapper;
import net.tazpvp.tazpvp.Mobs.MobUtils;
import net.tazpvp.tazpvp.Utils.Fun.ASCIIArtUtil;
import net.tazpvp.tazpvp.Utils.Functionality.ChatEnum;
import net.tazpvp.tazpvp.Utils.Functionality.MathUtils;
import net.tazpvp.tazpvp.Utils.NPCS.NpcUtils;
import net.tazpvp.tazpvp.Utils.NPCS.Villagers;
import net.tazpvp.tazpvp.Utils.Passive.Generator;
import net.tazpvp.tazpvp.Utils.Passive.Messages;
import net.tazpvp.tazpvp.Utils.Scoreboard.SbUtil;
import net.tazpvp.tazpvp.unused.ConfigGetter;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
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

import java.lang.reflect.InvocationTargetException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public final class Tazpvp extends JavaPlugin { //ntdi branmch test
    public static StatsManager statsManager;
    public static BoolManager boolManager;
    public static PerkManager perkManager;
    public static PunishmentManager punishmentManager;
    public static PlayerWrapperStatsManager playerWrapperStatsManager;
    public static HashMap<UUID, PlayerWrapper> playerWrapperMap = new HashMap<>();
    public static AchievementManager achievementManager;
    public static EnderChestManager enderChestManager;
    public static DuelLogic duelLogic;
    public static SbUtil sbUtil;
    public static ParticleUtil particleUtil;
    public static GuildManager guildManager;

    public static boolean isRestarting = false;
    public static JDA jda;
    public static Permission permissions;
    public static Chat chat;

    public static Tazpvp instance;
    public FileConfiguration config = getConfig();

    public static Boolean AllowBlocks = true;

    public static boolean chatMuted = false;

    public static WeakHashMap<UUID, Integer> bounty = new WeakHashMap<>();
    public static WeakHashMap<UUID, UUID> lastDamage = new WeakHashMap<>();
    public static List<UUID> returnItems = new ArrayList<>();
    public static List<UUID> hidden = new ArrayList<>();
    public static HashMap<Material, Material> blocks = new HashMap<Material, Material>();
    public static HashMap<Material, Integer> sellables = new HashMap<Material, Integer>();
    public static HashMap<UUID, UUID> dueling = new HashMap<>();
    public static WeakHashMap<UUID, Long> hasBeenReported = new WeakHashMap<>();
    public static List<Player> fallDamageImmune = new ArrayList<>();
    public static List<String> allowedCmds = new ArrayList<>();
    public static List<Material> allowedBlocks = new ArrayList<>();
    public static List<Player> afkPlayers = new ArrayList<>();
    public static List<Player> vanished = new ArrayList<>();

    public static Player toBeKicked;
    public static List<UUID> votedYes = new ArrayList<>();
    public static List<UUID> votedNo = new ArrayList<>();
    public static Map<UUID, Long> tempBan = new HashMap<>();

    public static Map<UUID, ChatEnum> chatEnum = new HashMap<>();

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

        config.options().copyDefaults(true);
        saveConfig();

        new WorldCreator("ban").environment(World.Environment.NETHER).createWorld();

        registerCommands();
        tempRegisterEvents();
//        try {
//            registerEvents();
//        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
        try {
            registeRedLib();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        StartBotThread thread = new StartBotThread();
        thread.start();

        new Generator().generator(this);
        new Messages().Text(this);

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


        doHashMaps();
        particleUtil.staticEffects.add(new StaticSpingParticle());

        for (Villagers vi : Villagers.values()) {
            if (vi.location.getWorld() != null) {
                NpcUtils.spawn(vi);
            }
        }

        new Thread(() -> Bukkit.getScheduler().runTaskTimer(Tazpvp.this, GuildUtils::wipeAllGuildInvites, 20 * 60 * 5, 20 * 60 * 5)).start();

        new Thread(() -> Bukkit.getScheduler().runTaskTimer(Tazpvp.this, MobUtils::everySec, 20, 20)).start();

        MobUtils.doShitFirstTImeYKYK();

        new BukkitRunnable() {
            @Override
            public void run() {
                CombatTag.tick();
                particleUtil.onTick();
            }
        }.runTaskTimer(this, 1L, 1L);
    }


    public void registerCommands() {
        getCommand("report").setExecutor(new ReportCMD());
    }
    public void registeRedLib() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ArgType<World> worldType = new ArgType<>("world", Bukkit::getWorld).tabStream(c -> Bukkit.getWorlds().stream().map(World::getName));

        new EnchantRegistry(this).registerAll(this);
//
//        ArrayList<CommandListener> listes = new ArrayList<>();
//        for (Class<? extends CommandListener> listener : RedLib.getExtendingClasses(this, CommandListener.class)) {
//            listes.add(listener.getConstructor().newInstance());
//        }
//        getLogger().info(listes.toString());

        new CommandParser(this.getResource("command.rdcml")).setArgTypes(worldType).parse().register("tazpvp", this,
            new StatsCMD(),
            new GuiCMD(),
            new SpawnCMD(),
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
            new BalCMD(),
            new ParticleCMD(),
            new SkullCMD(),
            new TazloadCMD(),
            new StaffchatCMD(),
            new AfkCMD(),
            new GuildCMD(),
            new MobsCMD(),
            new VotekickCMD(),
            new ChatCMD(),
            new VanishCMD(),
            new HideCMD());

        ConfigManager configManager = ConfigManager.create(this).target(ConfigGetter.class).saveDefaults().load();
    }

    public void registerEvents() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<? extends Listener> listener : RedLib.getExtendingClasses(this, Listener.class)) {
            regList(listener.getConstructor().newInstance());
        }
    }

    public void tempRegisterEvents() {
        List<Listener> listeners = List.of(
                new BlockBreakEvent(),
                new BlockFireEvent(),
                new BlockPlaceEvent(),
                new BowShootEvent(),
                new ChatEvent(),
                new CommandEvent(),
                new DamageEvent(),
                new DeathEvent(),
                new DrinkMilkEvent(),
                new EatEvent(),
                new InteractEvent(),
                new ItemPickUpEvent(),
                new JoinEvent(),
                new LeaveEvnet(),
                new MoveEvent(),
                new NPCEvent(),
                new WorldChangeEvent(),
                new ItemDropEvent(),
                new WorldLoadEvent());

        listeners.forEach(this::regList);
    }

    public void regList(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void managers(boolean init) {
        if (init) {
            statsManager = new StatsManager();
            boolManager = new BoolManager();
            perkManager = new PerkManager();
            punishmentManager = new PunishmentManager();
            playerWrapperStatsManager = new PlayerWrapperStatsManager();
            achievementManager = new AchievementManager();
            enderChestManager = new EnderChestManager();
            duelLogic = new DuelLogic();
            particleUtil = new ParticleUtil();
            guildManager = new GuildManager();
        } else {
            statsManager.saveStats();
            boolManager.saveStats();
            perkManager.saveStats();
            punishmentManager.savePunishments();
            playerWrapperStatsManager.saveStats();
            achievementManager.saveStats();
            enderChestManager.saveStats();
            guildManager.saveStats();
        }
    }

    public void doHashMaps() {
        blocks.put(Material.DEEPSLATE_GOLD_ORE, Material.RAW_GOLD);
        blocks.put(Material.DEEPSLATE_IRON_ORE, Material.RAW_IRON);
        blocks.put(Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE);
        blocks.put(Material.DEEPSLATE_LAPIS_ORE, Material.LAPIS_LAZULI);
        blocks.put(Material.DEEPSLATE_EMERALD_ORE, Material.EMERALD);

        sellables.put(Material.DEEPSLATE_GOLD_ORE, 1);
        sellables.put(Material.DEEPSLATE_IRON_ORE, 2);
        sellables.put(Material.DEEPSLATE_REDSTONE_ORE, 2);
        sellables.put(Material.DEEPSLATE_LAPIS_ORE, 3);
        sellables.put(Material.DEEPSLATE_EMERALD_ORE, 4);
        sellables.put(Material.RAW_GOLD, 2);
        sellables.put(Material.RAW_IRON, 3);
        sellables.put(Material.REDSTONE, 3);
        sellables.put(Material.LAPIS_LAZULI, 4);
        sellables.put(Material.EMERALD, 5);

        allowedCmds.add("/ad");
        allowedCmds.add("/pm");
        allowedCmds.add("/appeal");
        allowedCmds.add("/apply");
        allowedCmds.add("/help");
        allowedCmds.add("/discord");
        allowedCmds.add("/playtime");
        allowedCmds.add("/rules");
        allowedCmds.add("/store");

        allowedBlocks.add(Material.OAK_PLANKS);
        allowedBlocks.add(Material.PURPLE_WOOL);
        allowedBlocks.add(Material.YELLOW_WOOL);
        allowedBlocks.add(Material.GREEN_WOOL);
        allowedBlocks.add(Material.RED_WOOL);
        allowedBlocks.add(Material.COBWEB);
        allowedBlocks.add(Material.FIRE);
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
        MobUtils.clearMobs();
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
//        if (sb.getObjective("showguild") == null) {
//            Objective h = sb.registerNewObjective("showguild", "dummy");
//            h.setDisplaySlot(DisplaySlot.BELOW_NAME);
//            h.setDisplayName(ChatColor.RED + player.getUniqueId().toString());
//        }
        Score blank = objective.getScore(ChatColor.DARK_AQUA + "                         ");
        blank.setScore(14);
        Score blank1 = objective.getScore(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "WEALTH");
        blank1.setScore(13);
        Score level = objective.getScore(ChatColor.AQUA + "〡 Level  " + ChatColor.DARK_AQUA + Tazpvp.statsManager.getLevel(player));
        level.setScore(12);
        Score money = objective.getScore(ChatColor.AQUA + "〡 Coins  " + ChatColor.DARK_AQUA + "$" + Tazpvp.statsManager.getCoins(player));
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
    }

    public static void sendBaseTablist(Player p) {
        p.setPlayerListHeaderFooter(
                ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "TAZPVP.NET\n",
                ChatColor.GOLD + "                                                    \n" + ChatColor.AQUA + (Bukkit.getOnlinePlayers().size() - vanished.size()) + ChatColor.GRAY + "/" + ChatColor.DARK_AQUA + Bukkit.getMaxPlayers()
        );
    }

    public void addOnlinePlayersToSB(Player player) {
//        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            if (player.equals(onlinePlayer))
//                continue;
//
//            Team team = player.getScoreboard().getTeam(onlinePlayer.getUniqueId().toString());
//            if (team != null) {
//                team.unregister();
//            }
//
//            team = player.getScoreboard().registerNewTeam(onlinePlayer.getUniqueId().toString());
//
//
//            if (hidden.contains(onlinePlayer.getUniqueId())) {
//                team.setPrefix(ChatColor.GRAY + "");
//                team.setDisplayName(onlinePlayer.getName());
//
//            } else {
//                team.setPrefix(ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(onlinePlayer)));
//            }
//            if (GuildUtils.isInGuild(onlinePlayer) && GuildUtils.getGuild(onlinePlayer).tag() != null) {
//                team.setSuffix(ChatColor.YELLOW + " [" + GuildUtils.getGuild(onlinePlayer).tag() + "]");
//            }
//            team.setColor(statsManager.getColor(onlinePlayer));
//            team.addPlayer(onlinePlayer);
//
//        }
    }

    public void addPlayerToOnlinePlayersSB(Player player) {
//        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            Scoreboard scoreboard = onlinePlayer.getScoreboard();
//
//            Team team = scoreboard.getTeam(player.getUniqueId().toString());
//
//            if (team != null)
//                team.unregister();
//
//            team = scoreboard.registerNewTeam(player.getUniqueId().toString());
//
//            if (hidden.contains(player.getUniqueId())) {
//                team.setPrefix(ChatColor.GRAY + "");
//                team.setDisplayName(player.getName());
//
//            } else {
//                team.setPrefix(ChatColor.translateAlternateColorCodes('&', Tazpvp.chat.getPlayerPrefix(player)));
//            }
//            if (GuildUtils.isInGuild(player) && GuildUtils.getGuild(player).tag() != null) {
//                team.setSuffix(ChatColor.YELLOW + " [" + GuildUtils.getGuild(player).tag() + "]");
//            }
//            team.setColor(statsManager.getColor(player));
//            team.addPlayer(player);
//        }
    }

    public static Tazpvp getInstance(){
        return instance;
    }
}
