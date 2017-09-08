package me.sirlich.AsmodeusRpg;

import me.sirlich.AsmodeusRpg.customMobs.npcs.*;
import me.sirlich.AsmodeusRpg.customMobs.monsters.CustomZombie;
import me.sirlich.AsmodeusRpg.regions.Region;
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
    @Override
    public void onEnable() {
        NMSUtils.registerEntity("ranged_zombie", NMSUtils.Type.ZOMBIE, CustomZombie.class, false);
        NMSUtils.registerEntity("civilian", NMSUtils.Type.VILLAGER, Civilian.class, false);
        NMSUtils.registerEntity("shop_keeper", NMSUtils.Type.VILLAGER, ShopKeeper.class, false);
        NMSUtils.registerEntity("blacksmith", NMSUtils.Type.VILLAGER, Blacksmith.class, false);
        listener(new BlacksmithHandler());
        listener(new CivilianHandler());

        initStationaryMobs();
        System.out.println("Its working better!");
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
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder()+ "civilians/civilian.txt"));
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
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder()+ "/civilians/" + s));
            World world = Bukkit.getWorld("world");
            String line;
            String name = br.readLine();
            int proffesion = Integer.parseInt(br.readLine());
            String region = br.readLine();
            List<String> locs = Arrays.asList(br.readLine().split(","));
            Location loc = new Location(world,Double.parseDouble(locs.get(0)),Double.parseDouble(locs.get(1)),Double.parseDouble(locs.get(3)));
            List<String> sayings = Arrays.asList(br.readLine().split(","));

            Civilian civilian = new Civilian(((CraftWorld) world).getHandle());
            civilian.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
            ((CraftWorld) world).addEntity(civilian, CreatureSpawnEvent.SpawnReason.CUSTOM);
            System.out.println("Civilian successfully added.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listener(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
