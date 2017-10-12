package me.sirlich.AsmodeusRpg.mobs.npcs;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.List;

public class CivilianList
{
    public static HashMap<Entity, List<String>> msgMap = new HashMap<>();
    public static HashMap<Entity, Location> locMap = new HashMap<>();
    public static HashMap<String, String> regionMap = new HashMap<>();

    public static void addEntity(Entity entity, List<String> msg, Location loc, String regionID)
    {
        System.out.println("Entity added to CivilianList");
        msgMap.put(entity, msg);
        locMap.put(entity, loc);
        regionMap.put(entity.getCustomName(), regionID);
        System.out.println("Testing regionMap: " + regionMap.get(entity.getCustomName()));
    }

    public static List<String> getMessages(Entity entity)
    {
        return msgMap.get(entity);
    }

    public static Location getLoc(Entity entity)
    {
        return locMap.get(entity);
    }

    public static String getRegionID(String name)
    {
        return regionMap.get(name);
    }
}
