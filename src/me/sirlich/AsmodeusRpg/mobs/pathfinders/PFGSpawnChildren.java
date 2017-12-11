package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.RpgNode;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.PathfinderGoal;
import org.bukkit.scheduler.BukkitScheduler;

public class PFGSpawnChildren extends PathfinderGoal
{
    private EntityCreature entityCreature;
    private RpgEntity rpgEntity;
    private boolean shouldSpawn;

    public PFGSpawnChildren(EntityCreature entityCreature){
        this.entityCreature = entityCreature;
        this.rpgEntity = RpgEntityList.getRpgEntity(entityCreature.getUniqueID());
        this.shouldSpawn = true;
    }

    public boolean a(){
        EntityLiving goalTarget = this.entityCreature.getGoalTarget();
        if (goalTarget == null) {
            System.out.println("goalTarget is null");
            return false;
        } else if (!goalTarget.isAlive()) {
            return false;
        } else if(!RpgEntityList.getRpgEntity(entityCreature.getUniqueID()).isAggressive()){
            return false;
        } else if(!shouldSpawn){
            return false;
        } else {
            return true;
        }
    }

    public void c(){
        shouldSpawn = false;
        BukkitScheduler scheduler = AsmodeusRpg.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(AsmodeusRpg.getInstance(), new Runnable() {
            @Override
            public void run() {
                System.out.println("Spawn sum mobs!");
                //Spawn some mobs
            }
        }, 20L); //SHOULD EVENTUALLY BE PULLED FROM THE OTHER PLACE
        System.out.println("SPAWNED SOME SHITTT!");
    }
}
