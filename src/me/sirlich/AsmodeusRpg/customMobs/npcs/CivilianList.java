package me.sirlich.AsmodeusRpg.customMobs.npcs;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CivilianList
{
    public static HashMap<Entity,List<String>> msgMap = new HashMap<Entity,List<String>>();
    public static HashMap<Entity,Location> locMap = new HashMap<Entity,Location>();

    public static void addEntity(Entity entity,List<String> msg, Location loc){
        msgMap.put(entity,msg);
        locMap.put(entity,loc);
    }
    public static List<String> getMessages(Entity entity){
        return msgMap.get(entity);
    }

    public static Location getLoc(Entity entity){
        return locMap.get(entity);
    }
}
