package me.sirlich.AsmodeusRpg.mobs.damageReaction;

import org.bukkit.entity.Player;

public class DamageReaction
{
    public void onDamage(Player hitter){
        System.out.println("A mob was DamageReaction by " + hitter.getName());
    }

    public void onDamage(){
        System.out.println("A mob was DamageReaction");
    }
}
