package me.sirlich.AsmodeusRpg.customMobs.npcs;

import me.sirlich.AsmodeusRpg.regions.Region;
import me.sirlich.AsmodeusRpg.regions.RegionUtils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.List;

public class CivilianList
{
    public static HashMap<Entity,List<String>> msgMap = new HashMap<>();
    public static HashMap<Entity,Location> locMap = new HashMap<>();
    public static HashMap<Entity,String> regionMap = new HashMap<>();

    public static void addEntity(Entity entity,List<String> msg, Location loc, String regionID){
        msgMap.put(entity,msg);
        locMap.put(entity,loc);
        regionMap.put(entity,regionID);
    }
    public static List<String> getMessages(Entity entity){
        return msgMap.get(entity);
    }

    public static Location getLoc(Entity entity){
        return locMap.get(entity);
    }

    public static Region getRegion(Entity entity){
        System.out.println("getRegion (Civilian list)");
        return RegionUtils.getRegion("desert_blacksmith_shop");
    }
}
