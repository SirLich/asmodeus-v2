package me.sirlich.AsmodeusRpg;

import me.sirlich.AsmodeusRpg.customMobs.npcs.ArmourSmith;
import me.sirlich.AsmodeusRpg.customMobs.CustomZombie;
import me.sirlich.AsmodeusRpg.customMobs.npcs.ShopKeeper;
import me.sirlich.AsmodeusRpg.customMobs.npcs.listeners.ArmourSmithListener;
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
        NMSUtils.registerEntity("shop_keeper", NMSUtils.Type.VILLAGER, ShopKeeper.class, false);
        NMSUtils.registerEntity("armour_smith", NMSUtils.Type.VILLAGER, ArmourSmith.class, false);
        listener(new ArmourSmithListener());
        initiateShopKeeper();
        System.out.println("Its working better!");
    }

    @Override
    public void onDisable(){
        System.out.println("Asmodeus disabled");
    }

    private void initiateShopKeeper(){
        System.out.println("Inside!");
        try {
            BufferedReader br = new BufferedReader(new FileReader(getDataFolder()+ "/ArmourSmith.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                List<String> arr = Arrays.asList(line.split(","));
                World world = Bukkit.getServer().getWorld("world");
                Location loc = new Location(world,Double.parseDouble(arr.get(0)),Double.parseDouble(arr.get(1)),Double.parseDouble(arr.get(2)));
                ArmourSmith keeper = new ArmourSmith(((CraftWorld) world).getHandle());
                keeper.setLocation(loc.getX(),loc.getY(),loc.getZ(),loc.getYaw(),loc.getPitch());
                ((CraftWorld) world).addEntity(keeper, CreatureSpawnEvent.SpawnReason.CUSTOM);
                keeper.setProfession(3);
                keeper.setInvulnerable(true);
                System.out.println("Added entity!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listener(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
