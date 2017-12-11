package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import net.minecraft.server.v1_12_R1.*;

import java.util.Random;

public class PathFinderGoalLeapAtPlayer extends PathfinderGoal
{
    World world;
    double knockback;
    double damage;
    double power;
    int averageReload;
    protected EntityCreature entityCreature;
    PathEntity pathEntity;

    public PathFinderGoalLeapAtPlayer(EntityCreature entityCreature, double knockback, double damage, double power, int averageReload) {
        this.entityCreature = entityCreature;
        this.knockback = knockback;
        this.damage = damage;
        this.power = power;
        this.averageReload = averageReload;
        this.world = entityCreature.world;

        this.a(3);
    }

    public boolean a() {
        EntityLiving var1 = this.entityCreature.getGoalTarget();
        if (var1 == null) {
            return false;
        } else if (!var1.isAlive()) {
            return false;
        } else if(!RpgEntityList.getRpgEntity(entityCreature.getUniqueID()).isAggressive()){
            return false;
        }else {
            Random r = new Random();
            if(r.nextInt(averageReload) == 0){
                System.out.println("LEAP MAI BOI!");
                Entity entity = (Entity) this.entityCreature;
                RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueID());
                Entity player = (Entity) var1;
                org.bukkit.entity.Entity BukkitEntity = player.getBukkitEntity();
                rpgEntity.knockbackByEntity(-1,0.4, BukkitEntity.getLocation());
                return true;
            } else{
                return false;
            }
        }
    }
}
