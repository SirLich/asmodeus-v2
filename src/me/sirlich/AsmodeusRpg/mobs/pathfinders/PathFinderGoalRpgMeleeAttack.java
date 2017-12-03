package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.mobs.monsters.RpgEntity;
import net.minecraft.server.v1_12_R1.PathfinderGoal;
import net.minecraft.server.v1_12_R1.World;

public class PathFinderGoalRpgMeleeAttack extends PathfinderGoal
{
    RpgEntity rpgEntity;
    World world;

    public PathFinderGoalRpgMeleeAttack(RpgEntity rpgEntity, World world)
    {
        super();
        this.rpgEntity = rpgEntity;
        this.world = world;
    }

    public boolean a(){
        System.out.println(rpgEntity.getMeleeDamage());
        return false;
    }
}
