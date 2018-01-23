package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
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
        try {
            //Generic Stuff
            String fileName = "RPG_LICH.txt";
            BufferedReader br = new BufferedReader(new FileReader(AsmodeusRpg.getInstance().getDataFolder() + "/monsters/" + fileName));
            RpgFileReader reader = new RpgFileReader(br);
            World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
            RpgLich entity = new RpgLich(((CraftWorld) world).getHandle());
            entity.setCustomName(ChatColor.RED + ""+ level + " " + reader.readString(MobAttributes.name.toString()));

            System.out.println("1");
            System.out.println(entity.getUniqueID());
            RpgEntityList.addEntity(entity.getUniqueID());
            System.out.println("2");
            RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());

            //Some non-generic stuff that isn't set using setters atm
            rpgEntity.setAggressive(true);
            rpgEntity.setMaxAggression(500); //500 ticks before the mob is peacfull again

            System.out.println("3");
            //RpgEntity generic setters
            rpgEntity.setName("RpgLich: " + reader.readString(MobAttributes.name.toString()));
            System.out.println("Entity name after assigment: " + rpgEntity.getName());
            System.out.println("3.5");
            initGenericMobData(reader,rpgEntity,level);

            System.out.println("4");

            //RpgEntity meleeAttackSetter
            initPFGMeleeAttack(reader, rpgEntity,level);

            System.out.println("5");

            //RpgEntity spawner setters
            initPFGSpawnChildren(reader,rpgEntity,level);

            System.out.println("6");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void initPFGMeleeAttack(RpgFileReader reader, RpgEntity rpgEntity, int level){
        rpgEntity.setMeleeDamage(reader.readDouble(MobAttributes.meleeDamage.toString(),level));
        rpgEntity.setMeleeKnockbackTaken(reader.readDouble(MobAttributes.meleeKnockbackTaken.toString(),level));
        rpgEntity.setMeleeKnockbackGiven(reader.readDouble(MobAttributes.meleeKnockbackGiven.toString(),level));
        rpgEntity.setMeleeInvincibilityGiven(reader.readDouble(MobAttributes.meleeInvincibilityGiven.toString(),level));
    }

    private static void initPFGSpawnChildren(RpgFileReader reader, RpgEntity rpgEntity, int level){
        rpgEntity.setSpawnAmount(reader.readInt(MobAttributes.spawnAmount.toString(),level));
        rpgEntity.setSpawnDelay(reader.readInt(MobAttributes.spawnDelay.toString(),level));
        rpgEntity.setSpawnLevel(reader.readInt(MobAttributes.spawnAmount.toString(),level));
        rpgEntity.setSpawnType(RpgEntityType.valueOf(reader.readString(MobAttributes.spawnType.toString())));
    }

    private static void initGenericMobData(RpgFileReader reader, RpgEntity rpgEntity, int level){
        rpgEntity.setHealthRegeneration(reader.readDouble(MobAttributes.healthRegeneration.toString(), level));
        rpgEntity.setMaxHealth(reader.readDouble(MobAttributes.maxHealth.toString(), level));
        rpgEntity.setToFullHealth(); //No need to use a reader tag for this
        rpgEntity.setLevel(level);
    }

    public static Entity makeCritter(int level){
        try {
            //Generic Stuff
            String fileName = "RPG_CRITTER.txt";
            BufferedReader br = new BufferedReader(new FileReader(AsmodeusRpg.getInstance().getDataFolder() + "/monsters/" + fileName));
            RpgFileReader reader = new RpgFileReader(br);
            World world = Bukkit.getServer().getWorld(AsmodeusRpg.getInstance().getWorld());
            RpgLich entity = new RpgLich(((CraftWorld) world).getHandle());
            entity.setCustomName(ChatColor.RED + ""+ level + " " + reader.readString(MobAttributes.name.toString()));
            RpgEntityList.addEntity(entity.getUniqueID());
            RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());

            //Some non-generic stuff that isn't set using setters atm
            rpgEntity.setAggressive(true);
            rpgEntity.setMaxAggression(500); //500 ticks before the mob is peacfull again

            //RpgEntity generic setters
            rpgEntity.setName("RpgCritter: " + reader.readString(MobAttributes.name.toString()));
            initGenericMobData(reader,rpgEntity,level);

            //RpgEntity meleeAttackSetter
            initPFGMeleeAttack(reader, rpgEntity,level);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
