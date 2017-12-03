package me.sirlich.AsmodeusRpg.mobs.deathReaction;

import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.core.RpgPlayerList;
import org.bukkit.entity.Player;

public class LightingStrikeDeathReaction extends DeathReaction
{
    private double damage;
    public LightingStrikeDeathReaction(double damage){
        this.damage = damage;
    }

    @Override
    public void onDeath()
    {
        //Nothing here yo
    }

    @Override
    public void onDeath(Player killer){
        killer.getWorld().strikeLightningEffect(killer.getLocation());
        RpgPlayer rpgPlayer = RpgPlayerList.getRpgPlayer(killer);
        rpgPlayer.magicDamage(damage);
        System.out.println("A player has been struck by lighting!");
    }
}
