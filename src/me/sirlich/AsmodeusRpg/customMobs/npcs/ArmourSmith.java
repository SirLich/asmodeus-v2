package me.sirlich.AsmodeusRpg.customMobs.npcs;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftVillager;

public class ArmourSmith extends EntityVillager {

    public ArmourSmith(World world) {
        super(world);
        this.bukkitEntity = new CraftCustomVillager(this.world.getServer(), this);
        this.addScoreboardTag("armour_smith");
    }

    @Override
    protected void r()
    {
        this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 10.0f));
        this.goalSelector.a(1, new PathfinderGoalRandomLookaround(this));
    }

    @Override
    public GroupDataEntity a(DifficultyDamageScaler scaler, GroupDataEntity entity, boolean flag) {
        entity = super.a(scaler, entity, flag);
        this.setProfession(3);
        this.setInvulnerable(true);
        this.setCustomNameVisible(true);
        this.setCustomName(ChatColor.DARK_GRAY + "Armour Smith");
        return entity;
    }


    private static class CraftCustomVillager extends CraftVillager
    {

        private CraftCustomVillager(CraftServer server, EntityVillager parent) {
            super(server, parent);
        }

    }
}
