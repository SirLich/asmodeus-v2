package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.mobs.damageReaction.DamageReaction;
import me.sirlich.AsmodeusRpg.mobs.deathReaction.DeathReaction;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgCow;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgLich;
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

            //World
            World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
            RpgLich entity = new RpgLich(((CraftWorld) world).getHandle());

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
        RpgCow entity = new RpgCow(((CraftWorld) world).getHandle());
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
