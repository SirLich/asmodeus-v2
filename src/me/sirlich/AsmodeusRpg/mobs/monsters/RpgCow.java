package me.sirlich.AsmodeusRpg.mobs.monsters;

import me.sirlich.AsmodeusRpg.mobs.pathfinders.PathFinderGoalReduceAggression;
import me.sirlich.AsmodeusRpg.mobs.pathfinders.RpgAttributes;
import me.sirlich.AsmodeusRpg.utilities.NMSUtils;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftCow;

public class RpgCow extends EntityCow
{
    private double level;
    public RpgCow(World world, Integer level)
    {
        super(world);
        this.level = (double) level;
        this.bukkitEntity = new CraftRpgCow(this.world.getServer(), this);
    }

    @Override
    protected void r()
    {
        this.goalSelector.a(1,new PathFinderGoalReduceAggression(this));
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(RpgAttributes.level).setValue(level);
    }

    private static class CraftRpgCow extends CraftCow
    {

        private CraftRpgCow(CraftServer server, EntityCow parent)
        {
            super(server, parent);
        }

    }
}
