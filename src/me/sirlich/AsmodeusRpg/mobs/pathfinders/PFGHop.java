package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.PathfinderGoal;
import org.bukkit.entity.Player;

public class PFGHop extends PathfinderGoal
{
    EntityLiving entityLiving;
    public PFGHop(EntityLiving entityLiving){
        this.entityLiving = entityLiving;
    }
    public boolean a(){
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entityLiving.getUniqueID());
        if(!entityLiving.getBukkitEntity().isOnGround()){
            return false;
        }
        for(org.bukkit.entity.Entity entity : entityLiving.getBukkitEntity().getNearbyEntities(rpgEntity.getHopSeekRange(),rpgEntity.getHopSeekRange(),rpgEntity.getHopSeekRange())){
            if(entity instanceof Player){
                return true;
            }
        }
        return false;
    }

    public void c(){
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entityLiving.getUniqueID());
        for(org.bukkit.entity.Entity entity : entityLiving.getBukkitEntity().getNearbyEntities(rpgEntity.getHopSeekRange(),rpgEntity.getHopSeekRange(),rpgEntity.getHopSeekRange())){
            rpgEntity.knockbackByEntity(-1,1,entity.getLocation());
        }
    }
}
