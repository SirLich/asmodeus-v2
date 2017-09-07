package me.sirlich.AsmodeusRpg.customMobs.npcs;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;

public class Civilian extends EntityVillager {

    public Civilian(World world) {
        super(world);
        this.bukkitEntity = new CraftCustomVillager(this.world.getServer(), this);
        this.addScoreboardTag("civilian");
    }

    @Override
    protected void r()
    {
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public GroupDataEntity a(DifficultyDamageScaler scaler, GroupDataEntity entity, boolean flag) {
        entity = super.a(scaler, entity, flag);
        this.setProfession(3);
        this.setInvulnerable(true);
        this.setCustomName(ChatColor.GOLD + ">>>" +ChatColor.GRAY + " Civilian");
        return entity;
    }


    private static class CraftCustomVillager extends CraftVillager
    {

        private CraftCustomVillager(CraftServer server, EntityVillager parent) {
            super(server, parent);
        }

    }
}
