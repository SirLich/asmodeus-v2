package me.sirlich.AsmodeusRpg.mobs.pathfinders;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.core.RpgNode;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.MobCreator;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntity;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.RpgEntityList;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.PathfinderGoal;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitScheduler;

public class PFGSpawnChildren extends PathfinderGoal
{
    private EntityCreature entityCreature;
    private boolean shouldSpawn;
    public PFGSpawnChildren(EntityCreature entityCreature){
        this.entityCreature = entityCreature;
        this.shouldSpawn = true;
    }

    public boolean a(){
        if(!shouldSpawn){
            return false;
        } else {
            return true;
        }
    }

    public void c(){
        shouldSpawn = false;
        RpgEntity rpgEntity = RpgEntityList.getRpgEntity(entityCreature.getUniqueID());

        for(int i = 0; i < rpgEntity.getSpawnAmount(); i ++){
            Entity entity = MobCreator.makeCritter(rpgEntity.getSpawnLevel());
            MobCreator.spawn(entity,rpgEntity.getLocation());
        }
        BukkitScheduler scheduler = AsmodeusRpg.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(AsmodeusRpg.getInstance(), new Runnable() {
            @Override
            public void run() {
                System.out.println("You should spawn again!");
                shouldSpawn = true;
            }
        }, rpgEntity.getSpawnDelay()); //SHOULD EVENTUALLY BE PULLED FROM THE OTHER PLACE
    }
}
