package me.sirlich.AsmodeusRpg.customMobs.npcs;

import me.sirlich.AsmodeusRpg.customMobs.pathfinders.PathFinderGoalRandomStrollWithinRegion;
import me.sirlich.AsmodeusRpg.regions.Region;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;

public class Civilian extends EntityVillager
{
    private String name;
    private int profession;
    private Region region;

    public Civilian(World world, String name, int profession, Region region)
    {
        super(world);
        this.name = name;
        this.profession = profession;
        this.region = region;
        this.bukkitEntity = new CraftCustomVillager(this.world.getServer(), this);
        this.addScoreboardTag("civilian");
    }

    @Override
    protected void r()
    {
        System.out.println("Inside (r)");
        System.out.println("Region just added to mob: " + region.getName());
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.goalSelector.a(6, new PathFinderGoalRandomStrollWithinRegion(this, 0.5, 4, region));
    }

    @Override
    public GroupDataEntity a(DifficultyDamageScaler scaler, GroupDataEntity entity, boolean flag)
    {
        entity = super.a(scaler, entity, flag);
        this.setProfession(profession);
        this.setInvulnerable(true);
        this.setCustomName(ChatColor.GRAY + name);
        return entity;
    }


    private static class CraftCustomVillager extends CraftVillager
    {

        private CraftCustomVillager(CraftServer server, EntityVillager parent)
        {
            super(server, parent);
        }

    }
}
