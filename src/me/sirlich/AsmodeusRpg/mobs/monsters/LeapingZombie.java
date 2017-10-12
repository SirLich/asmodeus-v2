package me.sirlich.AsmodeusRpg.mobs.monsters;

import me.sirlich.AsmodeusRpg.mobs.pathfinders.PathFinderGoalLeapAtPlayer;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftHusk;

public class LeapingZombie extends EntityZombieHusk
{
    public LeapingZombie(World world)
    {
        super(world);
    }

    @Override
    protected void r()
    {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
        this.goalSelector.a(3, new PathfinderGoalMeleeAttack(this, 1, true));
        this.goalSelector.a(4, new PathFinderGoalLeapAtPlayer(this));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
    }

    private static class CraftLeapingZombie extends CraftHusk
    {

        private CraftLeapingZombie(CraftServer server, EntityZombieHusk parent)
        {
            super(server, parent);
        }
    }
}
