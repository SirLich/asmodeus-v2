package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import me.sirlich.AsmodeusRpg.utilities.WorldUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.UUID;

public class RpgEntityList
{

    public static HashMap<UUID, RpgEntity> rpgEntityHashMap = new HashMap<>();
    public static HashMap<RpgEntity, UUID> entityHashMap = new HashMap<>();

    public static void addEntity(Entity entity)
    {
        RpgEntity rpgEntity = new RpgEntity();
        rpgEntityHashMap.put(entity.getUniqueId(), rpgEntity);
        entityHashMap.put(rpgEntity, entity.getUniqueId());
    }

    public static void addEntity(UUID uuid){
        System.out.println("inside");
        RpgEntity rpgEntity = new RpgEntity();
        System.out.println("inside again");
        rpgEntityHashMap.put(uuid, rpgEntity);
        entityHashMap.put(rpgEntity, uuid);
    }

    public static void removeEntity(Entity entity)
    {
        RpgEntity rpgEntity = rpgEntityHashMap.get(entity.getUniqueId());
        entityHashMap.remove(rpgEntity);
        rpgEntityHashMap.remove(entity.getUniqueId());
    }

    public static void removeEntity(RpgEntity rpgEntity)
    {
        entityHashMap.remove(rpgEntity);
        rpgEntityHashMap.remove(rpgEntity.getUniqueId());
    }

    public static void removeEntity(UUID uuid)
    {
        entityHashMap.remove(rpgEntityHashMap.get(uuid));
        rpgEntityHashMap.remove(uuid);
    }

    public static RpgEntity getRpgEntity(Entity entity)
    {
        return rpgEntityHashMap.get(entity.getUniqueId());
    }

    public static RpgEntity getRpgEntity(UUID uuid)
    {
        return rpgEntityHashMap.get(uuid);
    }

    public static Entity getEntity(RpgEntity rpgEntity)
    {
        return WorldUtil.getEntityByUniqueId(entityHashMap.get(rpgEntity));
    }

    public static void recalculate(){
        for(UUID u :entityHashMap.values()){
            if(Bukkit.getEntity(u) != null){
                Entity entity = Bukkit.getEntity(u);
                if(entity.isDead()){
                    System.out.println("Removed entity with ID: " + entity.getUniqueId());
                    removeEntity(entity);
                }
            } else {
                System.out.println("Removed entity with ID: " + u);
                removeEntity(u);
            }

        }
    }
    public static boolean doesEntityExist(Entity entity)
    {
        return rpgEntityHashMap.containsKey(entity.getUniqueId());
    }
    public static boolean doesEntityExist(UUID uuid)
    {
        return rpgEntityHashMap.containsKey(uuid);
    }
}

