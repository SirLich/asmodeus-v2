package me.sirlich.AsmodeusRpg.customMobs.pathfinders;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import me.sirlich.AsmodeusRpg.regions.Region;
import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_12_R1.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitScheduler;

import javax.annotation.Nullable;

public class PathFinderGoalRandomStrollWithinRegion extends PathfinderGoalRandomStroll
{
    private Vec3D vec3D;
    private BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    private Region region;

    public PathFinderGoalRandomStrollWithinRegion(EntityCreature entityCreature, double var2, int var4, Region region)
    {
        super(entityCreature, var2, var4);
        this.region = region;
        locationSwitcher();
    }

    @Nullable
    private void locationSwitcher()
    {
        scheduler.scheduleSyncRepeatingTask(AsmodeusRpg.getInstance(), new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Running find new loc on: " + region.getName());
                Location loc = region.getRandomLoc();
                vec3D = new Vec3D(loc.getX(), loc.getY(), loc.getZ());
            }
        }, 500L, 500L);
    }

    @Override
    @Nullable
    protected Vec3D f()
    {
        return vec3D;
    }
}
