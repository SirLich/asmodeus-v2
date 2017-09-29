package me.sirlich.AsmodeusRpg.core;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class RpgEntity
{
    public int getMaxHealth()
    {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    private int maxHealth;

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }


    private int health;

    public void kill(){
        Entity entity = RpgEntityList.getEntity(this);
        if(entity instanceof Damageable){
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.setHealth(0);
            RpgEntityList.removeEntity(entity);
        } else{
            System.out.println("ERROR: RpgEntity.kill");
        }
    }


    public void rawDamageEntity(int damage){
        Entity entity = this.getEntity();
        setHealth(health - damage);
        if(getHealth() <= 0){
            kill();
        }
    }

    public Entity getEntity(){
        return RpgEntityList.getEntity(this);
    }

    public UUID getUniqueId(){
        return RpgEntityList.getEntity(this).getUniqueId();
    }
}
