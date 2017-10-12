package me.sirlich.AsmodeusRpg.mobs.monsters;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillagerZombie;

public class RpgPolarBearRider extends EntityZombieVillager
{
    public RpgPolarBearRider(World world)
    {
        super(world);
        this.bukkitEntity = new RpgPolarBearRider.CraftRpgPolarBearRider(this.world.getServer(), this);
    }

    @Override
    protected void r()
    {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalTargetNearestPlayer(this));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
    }

    private static class CraftRpgPolarBearRider extends CraftVillagerZombie
    {

        private CraftRpgPolarBearRider(CraftServer server, EntityZombieVillager parent)
        {
            super(server, parent);
        }

    }
}
