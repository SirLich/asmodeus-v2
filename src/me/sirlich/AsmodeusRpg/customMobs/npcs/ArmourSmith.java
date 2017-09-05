package me.sirlich.AsmodeusRpg.customMobs.npcs;

import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_12_R1.World;

public class ArmourSmith extends EntityVillager {

    private static ArmourSmith instance;
    public ArmourSmith(World world) {
        super(world);
        setProfession(3);
        this.addScoreboardTag("armour_smith");
    }
    @Override
    protected void r()
    {
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0f));
    }
    public ArmourSmith getInstance(){
        return instance;
    }

}
