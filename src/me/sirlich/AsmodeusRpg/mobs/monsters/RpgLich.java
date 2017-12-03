package me.sirlich.AsmodeusRpg.mobs.monsters;

import me.sirlich.AsmodeusRpg.mobs.mobUtils;
import me.sirlich.AsmodeusRpg.mobs.pathfinders.PathFinderGoalReduceAggression;
import me.sirlich.AsmodeusRpg.mobs.pathfinders.PathFinderGoalRpgMeleeAttack;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftCow;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftSkeleton;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftZombie;
import org.bukkit.entity.Zombie;

import java.util.List;
import java.util.Set;

public class RpgLich extends RpgEntity
{


    public RpgLich(World world, double damage)
    {
        super(world);
        this.setMeleeDamage(damage);
        this.bukkitEntity = new CraftRpgLich(this.world.getServer(),this);
    }

    @Override
    public void r(){
        this.goalSelector.a(3,new PathFinderGoalRpgMeleeAttack(this,this.getWorld()));
    }

    private static class CraftRpgLich extends CraftZombie
    {
        private CraftRpgLich(CraftServer server, RpgEntity parent)
        {
            super(server, parent);
        }

    }
}
