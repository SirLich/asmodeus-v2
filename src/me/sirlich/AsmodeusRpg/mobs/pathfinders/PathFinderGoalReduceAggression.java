package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.core.RpgEntity;
import me.sirlich.AsmodeusRpg.core.RpgEntityList;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.PathfinderGoal;
import org.bukkit.entity.Entity;

public class PathFinderGoalReduceAggression extends PathfinderGoal
{
    protected EntityCreature entityCreature;

    public PathFinderGoalReduceAggression(EntityCreature entityCreature) {
        this.entityCreature = entityCreature;
    }

    public boolean a(){
        Entity entity = (Entity) entityCreature.getBukkitEntity();
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entity.getUniqueId());
        rpgEntity.reduceAggression();
        return false;
    }
}
