package me.sirlich.AsmodeusRpg.mobs.monsters;

import net.minecraft.server.v1_12_R1.EntityCreature;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.World;

public class RpgEntity extends EntityZombie
{
    double meleeDamage;

    public double getMeleeDamage()
    {
        return meleeDamage;
    }

    public void setMeleeDamage(double meleeDamage)
    {
        this.meleeDamage = meleeDamage;
    }

    public double getKnockbackGiven()
    {
        return knockbackGiven;
    }

    public void setKnockbackGiven(double knockbackGiven)
    {
        this.knockbackGiven = knockbackGiven;
    }

    public double getKockbackTaken()
    {
        return kockbackTaken;
    }

    public void setKockbackTaken(double kockbackTaken)
    {
        this.kockbackTaken = kockbackTaken;
    }

    double knockbackGiven;
    double kockbackTaken;


    public RpgEntity(World world){
        super(world);
    }
}
