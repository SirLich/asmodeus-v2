package me.sirlich.AsmodeusRpg.mobs.npcs;

import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_12_R1.World;

public class ShopKeeper extends EntityVillager
{
    private static ShopKeeper instance;

    public ShopKeeper(World world)
    {
        super(world);
    }

    @Override
    protected void r()
    {
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0f));
    }

    public ShopKeeper getInstance()
    {
        return instance;
    }
}
