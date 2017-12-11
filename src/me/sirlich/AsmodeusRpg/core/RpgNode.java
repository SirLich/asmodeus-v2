package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.MobCreator;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityType;
import net.minecraft.server.v1_12_R1.Entity;
import org.bukkit.Location;

public class RpgNode
{
    private String name;
    int uuid;
    RpgEntityType monster;
    int minLevel;
    int maxLevel;
    Location location;
    int radius;
    int maxMobs;
    int refreshRate;
    int mobsOnRefresh;
    int currentRefresh;
    public RpgNode(String name, int uuid, RpgEntityType mobType, int minLevel,
                   int maxLevel, Location location, int radius, int
                           maxMobs, int refreshRate, int mobsOnRefresh){
        this.name = name;
        this.uuid = uuid;
        this.monster = mobType;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.location = location;
        this.radius = radius;
        this.maxMobs = maxMobs;
        this.refreshRate = refreshRate;
        this.mobsOnRefresh = mobsOnRefresh;
        this.currentRefresh = mobsOnRefresh;
    }

    public void doCountdown(){
        currentRefresh -= 1;
        if(currentRefresh <= 0){
            currentRefresh = refreshRate;
            spawnMobs();
        }
    }

    private void spawnMobs(){
        //Loop through spawning mobs
        for(int i = 0; i < mobsOnRefresh; i ++){
            int specLevel = (int)(Math.random() * (maxLevel + 1 - minLevel) + minLevel);
            Entity entity = MobCreator.makeMob(monster,specLevel);
            MobCreator.spawn(entity, location);
        }
    }

    public Location generateLocationInRadius(){
        //Generate location code here
        return location;
    }
}
