package me.sirlich.AsmodeusRpg.mobs.damageReaction;

import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.core.RpgPlayerList;
import me.sirlich.AsmodeusRpg.mobs.entityHandling.DamageType;
import org.bukkit.entity.Player;

public class ThornsDamageReaction extends DamageReaction
{
    private double damage;
    public ThornsDamageReaction(double damage){
        this.damage = damage;
    }

    @Override
    public void onDamage(DamageType damageType){
        if(damageType.hasPlayer()){
            RpgPlayer rpgPlayer = RpgPlayerList.getRpgPlayer(damageType.getPlayer());
            rpgPlayer.magicDamage(damage);
        }
    }
}
