package me.sirlich.AsmodeusRpg.mobs.damageReaction;

import me.sirlich.AsmodeusRpg.mobs.entityHandling.DamageType;

public class DamageReaction
{
    public void onDamage(DamageType damageType){
        System.out.println("A mob was damaged");
    }
}
