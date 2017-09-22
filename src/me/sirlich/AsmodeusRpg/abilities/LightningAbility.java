package me.sirlich.AsmodeusRpg.abilities;

import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

public class LightningAbility extends Ability
{
    public LightningAbility(Player player){
        super(player);
        setName("Lightning Ability");
        setRechargeRate(140);
        setRange(10);
    }
    @Override
    public void run(){
        List<Entity> nearby =  getPlayer().getNearbyEntities(getRange(), getRange(),getRange());
        for (Entity entity: nearby){
            if (entity instanceof Damageable && entity.getType() != EntityType.ARMOR_STAND && entity.getType() != EntityType.VILLAGER){
                getPlayer().getWorld().strikeLightningEffect(entity.getLocation());
            }
        }
    }
}
