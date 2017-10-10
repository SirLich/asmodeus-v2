package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.customMobs.damageResponses.DamageResponse;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RpgEntity
{
    private int maxHealth;
    private int health;
    private boolean aggression;

    public DamageResponse getDamageResponse()
    {
        return damageResponse;
    }

    public void setDamageResponse(DamageResponse damageResponse)
    {
        this.damageResponse = damageResponse;
    }

    public void damageRespone(){
        this.damageResponse.run();
    }

    private DamageResponse damageResponse;


    public void setAggression(boolean agro){
        aggression = agro;
    }

    public boolean isAggressive(){
        return aggression;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    private String name;

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public String toString(){
        if (getName() != null) {
            return getName();
        } else{
            return "Unknown Entity";
        }
    }

    public void kill()
    {
        Entity entity = RpgEntityList.getEntity(this);
        if (entity instanceof Damageable) {
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.setHealth(0);
            RpgEntityList.removeEntity(entity);
        } else {
            System.out.println("ERROR: RpgEntity.kill");
        }
    }

    public void rawDamageEntity(int damage)
    {
        Entity entity = this.getEntity();
        setHealth(health - damage);
        if (getHealth() <= 0) {
            kill();
        }
    }


    public void knockbackByEntity(double knockback, double knockup, Location entityLoc){
        Entity entity = this.getEntity();
        entity.setVelocity(entityLoc.getDirection().multiply(knockback).setY(knockup));
    }

    public Entity getEntity()
    {
        return RpgEntityList.getEntity(this);
    }

    public UUID getUniqueId()
    {
        return RpgEntityList.getEntity(this).getUniqueId();
    }
}
