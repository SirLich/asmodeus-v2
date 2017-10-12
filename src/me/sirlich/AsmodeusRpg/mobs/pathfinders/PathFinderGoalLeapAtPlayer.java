package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import net.minecraft.server.v1_12_R1.*;

import java.util.Collections;
import java.util.List;

public class PathFinderGoalLeapAtPlayer extends PathfinderGoal
{
    private final EntityInsentient entityInsentient;
    private final PathfinderGoalNearestAttackableTarget.DistanceComparator d;
    private EntityLiving entityLiving;

    public PathFinderGoalLeapAtPlayer(EntityInsentient entityInsentient)
    {
        this.entityInsentient = entityInsentient;
        this.d = new PathfinderGoalNearestAttackableTarget.DistanceComparator(entityInsentient);
    }

    public boolean a()
    {
        double distance = 16d;
        List list = this.entityInsentient.world.a(EntityHuman.class, this.entityInsentient.getBoundingBox().grow(distance, 4.0D, distance));
        Collections.sort(list, this.d);
        if (list.isEmpty()) {
            return false;
        } else {
            this.entityLiving = (EntityLiving) list.get(0);
            return true;
        }
    }

    public boolean b()
    {
        EntityLiving entityliving = this.entityInsentient.getGoalTarget();
        if (entityliving == null) {
            return false;
        } else if (!entityliving.isAlive()) {
            return false;
        } else if (entityliving instanceof EntityHuman && ((EntityHuman) entityliving).abilities.isInvulnerable) {
            return false;
        } else if (entityInsentient.getCustomName().equals("false")) {
            return false;
        } else {
            return true;
        }
    }

    public void c()
    {
        entityInsentient.setCustomName("false");
        /*
        new BukkitRunnable() {
            @Override
            public void run() {
                entityInsentient.setCustomName("true");
            }

        }.runTaskLater(AsmodeusRpg.getInstance(), 100);
        */
        Entity entity = (Entity) entityInsentient;
    }
}
