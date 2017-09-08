package me.sirlich.AsmodeusRpg.customMobs.pathfinders;

import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_12_R1.Vec3D;

import javax.annotation.Nullable;

public class PathFinderGoalRandomStrollWithinRegion extends PathfinderGoalRandomStroll
{
    private Vec3D v = new Vec3D(242,68,296);
    public PathFinderGoalRandomStrollWithinRegion(EntityCreature entity, double var2, int var4){
        super(entity,var2,var4);
    }

    @Override
    @Nullable
    protected Vec3D f(){
        return v;
    }
}
