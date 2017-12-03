package me.sirlich.AsmodeusRpg.core;

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

public class MobCreator
{
    public static Entity makeLich(String name){
        World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
        RpgLich entity = new RpgLich(((CraftWorld) world).getHandle(), 30);
        entity.setCustomName(name);

        RpgEntityList.addEntity(entity.getUniqueID());
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());

        rpgEntity.setName("RpgLich:" + name);
        entity.setCustomName(ChatColor.RED + " " + name);
        rpgEntity.setAggressive(true);
        rpgEntity.setMaxHealth(50);
        rpgEntity.setToFullHealth();
        rpgEntity.setLevel(40);
        rpgEntity.setMaxAggression(500);
        rpgEntity.setMaxAggression(0);
        rpgEntity.setDamageReaction(new DamageReaction());
        rpgEntity.setDeathReaction(new DeathReaction());

        return entity;
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
