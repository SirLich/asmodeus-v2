package me.sirlich.AsmodeusRpg.mobs.npcs;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;

public class Blacksmith extends EntityVillager
{

    public Blacksmith(World world)
    {
        super(world);
        this.bukkitEntity = new CraftCustomVillager(this.world.getServer(), this);
        this.addScoreboardTag("blacksmith");
    }

    @Override
    protected void r()
    {
        this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0f));
        this.goalSelector.a(1, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public GroupDataEntity a(DifficultyDamageScaler scaler, GroupDataEntity entity, boolean flag)
    {
        entity = super.a(scaler, entity, flag);
        this.setProfession(3);
        this.setInvulnerable(true);
        this.setCustomNameVisible(true);
        this.setCustomName(ChatColor.GOLD + ">>>" + ChatColor.GRAY + " Blacksmith");
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
