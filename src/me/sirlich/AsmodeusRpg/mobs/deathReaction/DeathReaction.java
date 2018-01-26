package me.sirlich.AsmodeusRpg.mobs.deathReaction;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.DamageType;

public class DeathReaction
{
    public void onDeath(DamageType damageType){
        System.out.println("A mob was killed");
    }
}
