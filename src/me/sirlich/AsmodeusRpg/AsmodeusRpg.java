package me.sirlich.AsmodeusRpg;

import me.sirlich.AsmodeusRpg.abilities.AbilitiesEditor;
import me.sirlich.AsmodeusRpg.abilities.AbilitiesHandler;
import me.sirlich.AsmodeusRpg.core.CancelHunger;
import me.sirlich.AsmodeusRpg.core.CancelPassiveRegeneration;
import me.sirlich.AsmodeusRpg.core.PlayerJoinHandler;
import me.sirlich.AsmodeusRpg.core.PlayerLeaveHandler;
import me.sirlich.AsmodeusRpg.customMobs.monsters.AggressiveCow;
import me.sirlich.AsmodeusRpg.customMobs.npcs.*;
import me.sirlich.AsmodeusRpg.customMobs.monsters.CustomZombie;
import me.sirlich.AsmodeusRpg.regions.Region;
import me.sirlich.AsmodeusRpg.regions.RegionUtils;
import me.sirlich.AsmodeusRpg.testing.TestPlayerList;
import me.sirlich.AsmodeusRpg.testing.TestIncreaseSpeedModifier;
import me.sirlich.AsmodeusRpg.testing.TestSetSpeedModifier;
import me.sirlich.AsmodeusRpg.utilities.DebugUtilities;
import me.sirlich.AsmodeusRpg.utilities.NMSUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AsmodeusRpg extends JavaPlugin {
    private static AsmodeusRpg instance;

    public AsmodeusRpg(){
        instance = this;
    }
    public static AsmodeusRpg getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable() {
        RegionUtils.loadFiles();

        registerCommands();

        NMSUtils.registerEntity("ranged_zombie", NMSUtils.Type.ZOMBIE, CustomZombie.class, false);
        NMSUtils.registerEntity("civilian", NMSUtils.Type.VILLAGER, Civilian.class, false);
        NMSUtils.registerEntity("shop_keeper", NMSUtils.Type.VILLAGER, ShopKeeper.class, false);
        NMSUtils.registerEntity("blacksmith", NMSUtils.Type.VILLAGER, Blacksmith.class, false);
        NMSUtils.registerEntity("aggressive_cow", NMSUtils.Type.COW, AggressiveCow.class, false);

        listener(new BlacksmithHandler());
        listener(new CivilianHandler());
        listener(new AbilitiesHandler());
        listener(new PlayerJoinHandler());
        listener(new CancelPassiveRegeneration());
        listener(new PlayerLeaveHandler());
        listener(new AbilitiesEditor());
        listener(new CancelHunger());

        initStationaryMobs();
    }

    @Override
    public void onDisable(){
        System.out.println("Asmodeus disabled");
    }

    private void initStationaryMobs(){
        System.out.println("Begin mob spawning...");

        //Spawn blacksmiths
        try {
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder()+ "/blacksmith.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                List<String> arr = Arrays.asList(line.split(","));
                World world = Bukkit.getServer().getWorld("world");
                Location loc = new Location(world,Double.parseDouble(arr.get(0)),Double.parseDouble(arr.get(1)),Double.parseDouble(arr.get(2)));
                Blacksmith keeper = new Blacksmith(((CraftWorld) world).getHandle());
                keeper.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
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
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder()+ "/civilians/civilians.txt"));
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

    private void civilianLoader(String s){
        try {
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder()+ "/civilians/" + s + ".txt"));

            World world = Bukkit.getWorld("world");
            String name = br.readLine();
            int profession = Integer.parseInt(br.readLine());
            List<String> locs = Arrays.asList(br.readLine().split(","));
            Location loc = new Location(world,Double.parseDouble(locs.get(0)),Double.parseDouble(locs.get(1)),Double.parseDouble(locs.get(2)));
            String regionID = br.readLine();
            Region region = RegionUtils.getRegion(regionID);
            System.out.println("Region name: " + region.getName());
            List<String> quotes = Arrays.asList(br.readLine().split(">"));
            System.out.println("1");
            System.out.println("Region name: " + region.getName());
            Civilian civilian = new Civilian(((CraftWorld) world).getHandle(),name,profession,region);
            System.out.println("2");
            CivilianList.addEntity(civilian.getBukkitEntity(),quotes,loc,regionID);
            System.out.println("3");
            civilian.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
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

    private void registerCommands(){
        this.getCommand("increaseSpeed").setExecutor(new TestIncreaseSpeedModifier());
        this.getCommand("playerList").setExecutor(new TestPlayerList());
        this.getCommand("setSpeed").setExecutor(new TestSetSpeedModifier());
        this.getCommand("debug").setExecutor(new DebugUtilities());
    }
    private void listener(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
