package me.sirlich.AsmodeusRpg;

import me.sirlich.AsmodeusRpg.abilities.AbilitiesEditor;
import me.sirlich.AsmodeusRpg.abilities.AbilitiesHandler;
import me.sirlich.AsmodeusRpg.cancellers.CancelHunger;
import me.sirlich.AsmodeusRpg.cancellers.CancelPassiveRegeneration;
import me.sirlich.AsmodeusRpg.core.PlayerJoinHandler;
import me.sirlich.AsmodeusRpg.core.PlayerLeaveHandler;
import me.sirlich.AsmodeusRpg.core.RPGDamage;
import me.sirlich.AsmodeusRpg.core.RpgPassiveRegen;
import me.sirlich.AsmodeusRpg.customMobs.handlers.AggressiveCowHandler;
import me.sirlich.AsmodeusRpg.customMobs.monsters.AggressiveCow;
import me.sirlich.AsmodeusRpg.customMobs.monsters.CustomZombie;
import me.sirlich.AsmodeusRpg.customMobs.monsters.LeapingZombie;
import me.sirlich.AsmodeusRpg.customMobs.monsters.TestMob;
import me.sirlich.AsmodeusRpg.customMobs.npcs.*;
import me.sirlich.AsmodeusRpg.items.RPGWeapon;
import me.sirlich.AsmodeusRpg.items.Texture;
import me.sirlich.AsmodeusRpg.regions.Region;
import me.sirlich.AsmodeusRpg.regions.RegionUtils;
import me.sirlich.AsmodeusRpg.testing.GetItem;
import me.sirlich.AsmodeusRpg.testing.TestMobSpawn;
import me.sirlich.AsmodeusRpg.testing.getPlayerHealthCommand;
import me.sirlich.AsmodeusRpg.testing.testDamageCommand;
import me.sirlich.AsmodeusRpg.utilities.AsmodeusCommand;
import me.sirlich.AsmodeusRpg.utilities.NMSUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

//import me.sirlich.AsmodeusRpg.items.RPGWeapon;
//import me.sirlich.AsmodeusRpg.items.Texture;
//import me.sirlich.AsmodeusRpg.testing.GetItem;

public class AsmodeusRpg extends JavaPlugin
{
    static CommandMap map;
    private static AsmodeusRpg instance;

    public AsmodeusRpg()
    {
        instance = this;
    }

    public static AsmodeusRpg getInstance()
    {
        return instance;
    }

    public static void register(AsmodeusCommand... cmds)
    {
        try {
            final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            map = (CommandMap) f.get(Bukkit.getServer());

            for (AsmodeusCommand cmd : cmds) {
                map.register(cmd.getName(), cmd);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable()
    {
        RegionUtils.loadFiles();
        RpgPassiveRegen.startTicker();

        register(new GetItem());
        register(new TestMobSpawn());
        register(new testDamageCommand());
        register(new getPlayerHealthCommand());

        NMSUtils.registerEntity("ranged_zombie", NMSUtils.Type.ZOMBIE, CustomZombie.class, false);
        NMSUtils.registerEntity("civilian", NMSUtils.Type.VILLAGER, Civilian.class, false);
        NMSUtils.registerEntity("shop_keeper", NMSUtils.Type.VILLAGER, ShopKeeper.class, false);
        NMSUtils.registerEntity("blacksmith", NMSUtils.Type.VILLAGER, Blacksmith.class, false);
        NMSUtils.registerEntity("aggressive_cow", NMSUtils.Type.COW, AggressiveCow.class, false);
        NMSUtils.registerEntity("leaping_zombie", NMSUtils.Type.HUSK, LeapingZombie.class, false);
        NMSUtils.registerEntity("test_mob", NMSUtils.Type.SKELETON, TestMob.class, false);


        listener(new BlacksmithHandler());
        listener(new CivilianHandler());
        listener(new AbilitiesHandler());
        listener(new PlayerJoinHandler());
        listener(new CancelPassiveRegeneration());
        listener(new PlayerLeaveHandler());
        listener(new AbilitiesEditor());
        listener(new CancelHunger());
        listener(new RPGDamage());
        listener(new AggressiveCowHandler());

        initStationaryMobs();

        RPGWeapon wep = new RPGWeapon("weapon_test-sword");
        wep.primaryAttack(1, 5, 10, 0).texture(Texture.WOOD_SWORD).rarity(0).name("Test Sword").description("&9This weapon was created to",
                "&9test the functionalities of the", "&9Item System.").finish();

        /*new RPGWeapon(Texture.WOOD_SWORD, "Test Sword", "weapon_test-sword", RPGWeapon.Rarity.COMMON, true,
                1, 5, 5, 0, false, 0, 0, 0,
                0, null, null, null);*/
    }

    @Override
    public void onDisable()
    {
        System.out.println("Asmodeus disabled");
    }

    private void initStationaryMobs()
    {
        System.out.println("Begin mob spawning...");

        //Spawn blacksmiths
        try {
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder() + "/blacksmith.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                List<String> arr = Arrays.asList(line.split(","));
                World world = Bukkit.getServer().getWorld("world");
                Location loc = new Location(world, Double.parseDouble(arr.get(0)), Double.parseDouble(arr.get(1)), Double.parseDouble(arr.get(2)));
                Blacksmith keeper = new Blacksmith(((CraftWorld) world).getHandle());
                keeper.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                ((CraftWorld) world).addEntity(keeper, CreatureSpawnEvent.SpawnReason.CUSTOM);
                System.out.println("Blacksmith successfully added.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Spawn Civilians
        try {
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder() + "/civilians/civilians.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                civilianLoader(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void civilianLoader(String s)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder() + "/civilians/" + s + ".txt"));

            World world = Bukkit.getWorld("world");
            String name = br.readLine();
            int profession = Integer.parseInt(br.readLine());
            List<String> locs = Arrays.asList(br.readLine().split(","));
            Location loc = new Location(world, Double.parseDouble(locs.get(0)), Double.parseDouble(locs.get(1)), Double.parseDouble(locs.get(2)));
            String regionID = br.readLine();
            Region region = RegionUtils.getRegion(regionID);
            System.out.println("Region name: " + region.getName());
            List<String> quotes = Arrays.asList(br.readLine().split(">"));
            System.out.println("1");
            System.out.println("Region name: " + region.getName());
            Civilian civilian = new Civilian(((CraftWorld) world).getHandle(), name, profession, region);
            System.out.println("2");
            CivilianList.addEntity(civilian.getBukkitEntity(), quotes, loc, regionID);
            System.out.println("3");
            civilian.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
            System.out.println("4");
            ((CraftWorld) world).addEntity(civilian, CreatureSpawnEvent.SpawnReason.CUSTOM);
            System.out.println("5");
            System.out.println("Civilian successfully added.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listener(Listener... listeners)
    {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
