package me.sirlich.AsmodeusRpg.customMobs.pathfinders;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.customMobs.npcs.CivilianList;
import me.sirlich.AsmodeusRpg.regions.Region;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_12_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitScheduler;

import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public class PathFinderGoalRandomStrollWithinRegion extends PathfinderGoalRandomStroll
{
    private Entity entity;
    private Region region;
    private Vec3D vec3D;
    private BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

    public PathFinderGoalRandomStrollWithinRegion(EntityCreature entity, double var2, int var4){
        super(entity,var2,var4);
        this.entity = entity.getBukkitEntity();
        System.out.println("Test!");
        this.region = CivilianList.getRegion(this.entity);
        System.out.println("Region: " + region.getName());
        findNewLoc();
    }

    @Nullable
    private void findNewLoc(){
        System.out.println("findNewLoc");
        Location loc = region.getRandomLoc();
        vec3D = new Vec3D(loc.getX(),loc.getY(),loc.getZ());
    }

    @Override
    @Nullable
    protected Vec3D f(){
        /*
        scheduler.scheduleSyncRepeatingTask(AsmodeusRpg.getInstance(), new Runnable() {
            @Override
            public void run() {
                findNewLoc();
            }
        }, 0L, 500L);
        */
        return vec3D;
    }
}
