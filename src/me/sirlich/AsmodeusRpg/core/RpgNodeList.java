package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityType;
import me.sirlich.AsmodeusRpg.utilities.RpgFileReader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RpgNodeList
{
    private static ArrayList<RpgNode> rpgNodeList = new ArrayList<RpgNode>();

    public static void startMobSpawning(){
        BukkitScheduler scheduler = AsmodeusRpg.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(AsmodeusRpg.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(RpgNode rpgNode: rpgNodeList){
                    rpgNode.doCountdown();
                }
            }
        }, 0L, 20L);
    }
    public static void initializeNodes(){
        //Spawn Nodes
        try {
            BufferedReader br = new BufferedReader(new FileReader(AsmodeusRpg.getInstance().getDataFolder() + "/nodes/nodes.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                spawnNode(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void spawnNode(String fileName)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(AsmodeusRpg.getInstance().getDataFolder() + "/nodes/" + fileName));
            RpgFileReader reader = new RpgFileReader(br);

            //World
            World world = Bukkit.getWorld(AsmodeusRpg.getInstance().getWorld());

            //Name
            //String name = RpgFileReader.trimContent(br.readLine());
            String name = reader.readSting();

            //UUID
            //int uuid = Integer.parseInt(RpgFileReader.trimContent(br.readLine()));
            int uuid = reader.readInt();

            //Mob-type
            //RpgEntityType mobType = RpgEntityType.valueOf(RpgFileReader.trimContent(br.readLine()));
            RpgEntityType mobType = reader.readRpgMonster();

            //Mob-level
            //String levels[] = RpgFileReader.trimContent(br.readLine()).split("-");
            //int minLevel = Integer.parseInt(levels[0]);
            //int maxLevel = Integer.parseInt(levels[1]);
            ArrayList<Integer> levels = reader.readIntList();
            int minLevel = levels.get(0);
            int  maxLevel = levels.get(1);

            //Location
            //String locs[] = RpgFileReader.trimContent(br.readLine()).split(",");
            //Location location = new Location(world, Double.parseDouble(locs[0]), Double.parseDouble(locs[1]), Double.parseDouble(locs[2]));
            ArrayList<Double> locs = reader.readDoubleList();
            Location location = new Location(world, locs.get(0), locs.get(1), locs.get(2));

            //Radius
            //int radius = Integer.parseInt(RpgFileReader.trimContent(br.readLine()));
            int radius = reader.readInt();

            //Max-mobs spawned
            //int maxMobs = Integer.parseInt(RpgFileReader.trimContent(br.readLine()));
            int maxMobs = reader.readInt();

            //Mob-Refresh Rate
            //int refreshRate = Integer.parseInt(RpgFileReader.trimContent(br.readLine()));
            int refreshRate = reader.readInt();

            //Mobs spawned on refresh
            //int refreshNum = Integer.parseInt(RpgFileReader.trimContent(br.readLine()));
            int refreshNum = reader.readInt();

            RpgNode rpgNode = new RpgNode(name, uuid, mobType,minLevel,maxLevel,location,radius,maxMobs,refreshRate,refreshNum);

            rpgNodeList.add(rpgNode);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
