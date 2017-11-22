package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.mobs.monsters.RpgCow;
import net.minecraft.server.v1_12_R1.Entity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobCreator
{
    public static Entity makeCow(double maxHealth, double damage, String name, int level){
        World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
        RpgCow entity = new RpgCow(((CraftWorld) world).getHandle(), damage, 10);
        entity.setCustomName(ChatColor.RED + ""+ level + " " + name);
        RpgEntityList.addEntity(entity.getUniqueID());
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());

        rpgEntity.setName("RpgCow: " + name);
        rpgEntity.setAggressive(false);
        rpgEntity.setMaxHealth(50);
        rpgEntity.setToFullHealth();
        rpgEntity.setLevel(level);
        rpgEntity.setAggressive(false);
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
