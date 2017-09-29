package me.sirlich.AsmodeusRpg.core;
import java.util.HashMap;
import java.util.UUID;

import me.sirlich.AsmodeusRpg.utilities.WorldUtil;
import org.bukkit.entity.Entity;

public class RpgEntityList {

    public static HashMap <UUID,RpgEntity> rpgEntityHashMap = new HashMap<>();
    public static HashMap <RpgEntity,UUID> entityHashMap = new HashMap<>();

    public static void addEntity(Entity entity){
        RpgEntity rpgEntity = new RpgEntity();

        rpgEntityHashMap.put(entity.getUniqueId(),rpgEntity);
        entityHashMap.put(rpgEntity,entity.getUniqueId());
    }

    public static void removeEntity(Entity entity){
        RpgEntity rpgEntity = rpgEntityHashMap.get(entity.getUniqueId());
        entityHashMap.remove(rpgEntity);
        rpgEntityHashMap.remove(entity.getUniqueId());
    }

    public static void removeEntity(RpgEntity rpgEntity){
        entityHashMap.remove(rpgEntity);
        rpgEntityHashMap.remove(rpgEntity.getUniqueId());
    }

    public static RpgEntity getRpgEntity(Entity entity){
        return rpgEntityHashMap.get(entity.getUniqueId());
    }

    public static Entity getEntity(RpgEntity rpgEntity){
        return WorldUtil.getEntityByUniqueId(entityHashMap.get(rpgEntity));
    }

    public static boolean doesEntityExist(Entity entity){
        return rpgEntityHashMap.containsKey(entity.getUniqueId());
    }
}

