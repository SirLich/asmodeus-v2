package me.sirlich.AsmodeusRpg.mobs.damageReaction;

import me.sirlich.AsmodeusRpg.core.RpgPlayer;
import me.sirlich.AsmodeusRpg.core.RpgPlayerList;
import org.bukkit.entity.Player;

public class ThornsDamageReaction extends DamageReaction
{
    private double damage;
    public ThornsDamageReaction(double damage){
        this.damage = damage;
    }
    @Override
    public void onDamage(Player player){
        RpgPlayer rpgPlayer = RpgPlayerList.getRpgPlayer(player);
        rpgPlayer.magicDamage(damage);
    }

    @Override
    public void onDamage(){
        System.out.println("HOW THE FUCK DID THIS GET CALLED?");
    }
}
