package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgCow;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgCritter;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgLich;
import me.sirlich.AsmodeusRpg.utilities.RpgFileReader;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityTypes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MobCreator
{

    public static Entity makeMob(RpgEntityType monster, int level){
        if(monster == RpgEntityType.RPG_LICH){
            return(makeRpgLich(level));
        } else if(monster == RpgEntityType.RPG_CRITTER){
            return(makeCritter(level));
        }else{
            //Kind of a shitty failsafe atm
            return(makeRpgLich(1));
        }
    }

    public static Entity makeRpgLich(int level){
        String fileName = "RPG_LICH.txt";
        RpgFileReader reader = new RpgFileReader(fileName, level);
        World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
        RpgLich entity = new RpgLich(((CraftWorld) world).getHandle());
        entity.setCustomName(ChatColor.RED + ""+ level + " " + reader.readRandomString("name"));

        System.out.println(entity.getUniqueID());
        RpgEntityList.addEntity(entity.getUniqueID());
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());
        rpgEntity.setLevel(level);
        initializeMobFileData(reader, rpgEntity);
        rpgEntity.setName("RpgLich: " + reader.readRandomString("name"));
        return entity;
    }

    public static Entity makeCritter(int level){
        String fileName = "RPG_CRITTER.txt";
        RpgFileReader reader = new RpgFileReader(fileName, level);
        World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
        RpgCritter entity = new RpgCritter(((CraftWorld) world).getHandle());
        entity.setCustomName(ChatColor.RED + ""+ level + " " + reader.readRandomString("name"));

        System.out.println(entity.getUniqueID());
        RpgEntityList.addEntity(entity.getUniqueID());
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());
        rpgEntity.setLevel(level);
        initializeMobFileData(reader, rpgEntity);
        rpgEntity.setName("RpgCritter: " + reader.readRandomString("name"));
        return entity;
    }

    private static void initializeMobFileData(RpgFileReader reader, RpgEntity rpgEntity){
        System.out.println("Initializing mob data .. ");
        //Generic Stuff

        rpgEntity.setWalkSpeed(reader.readDouble("walkSpeed"));
        rpgEntity.setHealthRegeneration(reader.readDouble("healthRegeneration"));
        rpgEntity.setMaxHealth(reader.readDouble("maxHealth"));
        rpgEntity.setHealth(reader.readDouble("health"));

        //Melee Attack Pathfinders
        rpgEntity.setMeleeDamage(reader.readDouble("meleeDamage"));
        rpgEntity.setMeleeKnockbackTaken(reader.readDouble("meleeKnockbackTaken"));
        rpgEntity.setMeleeKnockbackGiven(reader.readDouble("meleeKnockbackGiven"));
        rpgEntity.setMeleeInvincibilityGiven(reader.readDouble("meleeInvincibilityGiven"));

        //Spawn Children Pathfinder
        rpgEntity.setSpawnAmount(reader.readInt("spawnAmount"));
        rpgEntity.setSpawnDelay(reader.readInt("spawnDelay"));
        rpgEntity.setSpawnLevel(reader.readInt("spawnLevel"));

        //Hop pathfinder
        rpgEntity.setHopHeight(reader.readDouble("hopHeight"));
        rpgEntity.setHopSeekRange(reader.readDouble("hopSeekRange"));
        rpgEntity.setHopStrength(reader.readDouble("hopStrength"));

        for (RpgEntityType c : RpgEntityType.values()) {
            if (c.name().equals(reader.readRandomString("spawnType"))){
                rpgEntity.setSpawnType(RpgEntityType.valueOf(reader.readRandomString("spawnType")));
            }
        }

        rpgEntity.setAggressive(true);
        rpgEntity.setMaxAggression(500); //500 ticks before the mob is peacfull again
    }


    public static void spawn(Entity entity, Location location){
        World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
        entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        ((CraftWorld) world).addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }
}
