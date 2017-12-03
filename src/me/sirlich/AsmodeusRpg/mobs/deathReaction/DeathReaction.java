package me.sirlich.AsmodeusRpg.mobs.deathReaction;

import org.bukkit.entity.Player;

public class DeathReaction
{
    public void onDeath(Player killer){
        System.out.println("A mob was killed by " + killer.getName());
    }

    public void onDeath(){
        System.out.println("A mob died");
    }
}
