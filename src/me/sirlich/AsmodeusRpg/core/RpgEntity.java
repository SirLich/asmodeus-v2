package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.mobs.damageResponses.DamageResponse;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class RpgEntity
{
    private double maxHealth;
    private double health;
    private boolean aggression;
    private DamageResponse damageResponse;
    private String name;


    public DamageResponse getDamageResponse()
    {
        return damageResponse;
    }

    public void setDamageResponse(DamageResponse damageResponse)
    {
        this.damageResponse = damageResponse;
    }

    public void damageResponse(){
        this.damageResponse.run();
    }


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


    public double getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public double getHealth()
    {
        return health;
    }

    public void setHealth(double i)
    {
        if (i > maxHealth) {
            health = maxHealth;
        } else if(i <= 0){
            kill();
        } else {
            health = i;
        }
    }

    public void setToFullHealth(){
        setHealth(maxHealth);
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

    public void editHealth(double i){
        health += i;
        if(health <= 0){
            kill();
        } else if(health > maxHealth){
            health = maxHealth;
        }
    }
    public void rawDamageEntity(double damage)
    {
        editHealth(-damage);
    }

    public void meleeDamageEntity(double dmg){
        rawDamageEntity(dmg);
    }

    public void magicDamageEntity(double dmg){
        rawDamageEntity(dmg);
    }

    public void rangedDamageEntity(double dmg){
        rawDamageEntity(dmg);
    }

    public void knockbackByEntity(double knockback, double knockup, Location entityLoc){
        Entity entity = this.getEntity();
        if(entity != null){
            if(entity.isOnGround()){
                entity.setVelocity(entityLoc.getDirection().multiply(knockback).setY(knockup));
            } else{
                entity.setVelocity(entityLoc.getDirection().multiply(knockback).setY(0));
            }
        }
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
