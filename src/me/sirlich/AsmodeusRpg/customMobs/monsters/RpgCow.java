package me.sirlich.AsmodeusRpg.customMobs.monsters;

import me.sirlich.AsmodeusRpg.customMobs.pathfinders.PathFinderGoalRpgCowMeleeAttack;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftCow;

public class RpgCow extends EntityCow
{
    public RpgCow(World world)
    {
        super(world);
        this.bukkitEntity = new CraftAggressiveCow(this.world.getServer(), this);
    }

    @Override
    protected void r()
    {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
        this.goalSelector.a(2,new PathFinderGoalRpgCowMeleeAttack(this,2,true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
    }

    private static class CraftAggressiveCow extends CraftCow
    {

        private CraftAggressiveCow(CraftServer server, EntityCow parent)
        {
            super(server, parent);
        }

    }
}