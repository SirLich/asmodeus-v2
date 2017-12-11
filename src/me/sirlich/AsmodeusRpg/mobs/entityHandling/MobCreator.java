package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.mobs.damageReaction.DamageReaction;
import me.sirlich.AsmodeusRpg.mobs.deathReaction.DeathReaction;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgCow;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgLich;
import me.sirlich.AsmodeusRpg.utilities.RpgFileReader;
import net.minecraft.server.v1_12_R1.Entity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MobCreator
{

    public static Entity makeMob(RpgEntityType monster, int level){
        return(makeLich(7));
    }
    public static Entity makeLich(int level){
        try {
            String fileName = "RPG_LICH.txt";
            BufferedReader br = new BufferedReader(new FileReader(AsmodeusRpg.getInstance().getDataFolder() + "/monsters/" + fileName));
            RpgFileReader reader = new RpgFileReader(br);

            //Name
            String name = reader.readRandomStringFromList();

            //MeleeDamage
            double meleeDamage = reader.readSpecificDouble(level);

            //MeleeKnockback
            double meleeKnockbackGiven = reader.readSpecificDouble(level);

            //MeleeKnockback taken
            double meleeKnockbackTaken = reader.readSpecificDouble(level);

            //Invincibility given on meleeDamage
            double stunOnMeleeAttack = reader.readSpecificDouble(level);

            //Spawn number
            int minionNumberSpawned = reader.readSpecificInt(level);

            //Spawn level
            int minionLevel = reader.readSpecificInt(level);

            //Spawn delay between minions spawns
            int minionSpawnDelay = reader.readSpecificInt(level);

            //Health
            double health = reader.readSpecificDouble(level);

            //healthRegenPerSecond
            double healthRegen = reader.readSpecificDouble(level);

            //World
            World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
            RpgLich entity = new RpgLich(((CraftWorld) world).getHandle());

            entity.setCustomName(name);

            RpgEntityList.addEntity(entity.getUniqueID());
            RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());

            rpgEntity.setName("RpgLich: " + name);
            entity.setCustomName(ChatColor.RED + " " + name);

            rpgEntity.setAggressive(true);
            rpgEntity.setMaxHealth(health);
            rpgEntity.setToFullHealth();
            rpgEntity.setLevel(level);
            rpgEntity.setMaxAggression(500);
            rpgEntity.setDamageReaction(new DamageReaction());
            rpgEntity.setDeathReaction(new DeathReaction());
            rpgEntity.setMeleeDamage(meleeDamage);
            rpgEntity.setMeleeKnockback(meleeKnockbackGiven);
            rpgEntity.setMeleeKnockbackTaken(meleeKnockbackTaken);
            rpgEntity.setMinionSpawnDelay(minionSpawnDelay);
            rpgEntity.setMinionSpawnLevel(minionLevel);
            rpgEntity.setMinionSpawnNum(minionNumberSpawned);
            rpgEntity.setMinionSpawnType(RpgEntityType.RPG_CRITTER);
            return entity;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Entity makeCritter(int level){
        return null;
    }

    public static Entity makeCow(double maxHealth, double damage, String name, int level){
        World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
        RpgCow entity = new RpgCow(((CraftWorld) world).getHandle(), damage, 10);
        entity.setCustomName(ChatColor.RED + ""+ level + " " + name);

        RpgEntityList.addEntity(entity.getUniqueID());
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());

        rpgEntity.setName("RpgCow: " + name);
        rpgEntity.setAggressive(true);
        rpgEntity.setMaxHealth(50);
        rpgEntity.setToFullHealth();
        rpgEntity.setLevel(level);
        rpgEntity.setMeleeDamage(3);
        rpgEntity.setMaxAggression(500);
        rpgEntity.setMaxAggression(0);

        return entity;
    }

    public static void spawn(Entity entity, Location location){
        World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
        entity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        ((CraftWorld) world).addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }
}
