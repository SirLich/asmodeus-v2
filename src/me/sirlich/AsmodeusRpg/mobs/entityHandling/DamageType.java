package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import org.bukkit.entity.Player;

public class DamageType
{
    private Player player;
    private String damager;

    public DamageType(Player player, String damager){
        this.player = player;
        this.damager = damager;
    }

    public Player getPlayer()
    {
        return player;
    }

    public String getDamager(){
        return damager;
    }

    public boolean hasPlayer(){
        return player != null;
    }
}
