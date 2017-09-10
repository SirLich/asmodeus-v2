package me.sirlich.AsmodeusRpg.customMobs.pathfinders;

import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_12_R1.Vec3D;
import org.bukkit.entity.Entity;

import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public class PathFinderGoalRandomStrollWithinRegion extends PathfinderGoalRandomStroll
{
    private Entity entity;
    private Vec3D vec3D;
    public PathFinderGoalRandomStrollWithinRegion(EntityCreature entity, double var2, int var4){
        super(entity,var2,var4);
        this.entity = entity.getBukkitEntity();
    }

    private Vec3D findNewLoc(){
        return new Vec3D(ThreadLocalRandom.current().nextInt(230,250),ThreadLocalRandom.current().nextInt(60,80),ThreadLocalRandom.current().nextInt(280,300));
        //Nothing here atm/
    }

    @Override
    @Nullable
    protected Vec3D f(){
        System.out.println("Called");
        return new Vec3D(ThreadLocalRandom.current().nextInt(230,250),ThreadLocalRandom.current().nextInt(60,80),ThreadLocalRandom.current().nextInt(280,300));
    }
}
