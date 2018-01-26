package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import me.sirlich.AsmodeusRpg.mobs.damageReaction.DamageReaction;
import me.sirlich.AsmodeusRpg.mobs.deathReaction.DeathReaction;
import me.sirlich.AsmodeusRpg.utilities.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.UUID;

public class RpgEntity
{
    //Generics
    private String name;
    private ArrayList<DeathReaction> deathReactions;
    private ArrayList<DamageReaction> damageReactions;
    private double walkSpeed;

    //Health
    private double health;
    private double maxHealth;
    private double healthRegeneration;

    //Aggression
    private boolean isAggressive;
    private int maxAggression; //Set this when the mob is attacked
    private int aggression; //Current aggression level. Should reduce 1 per tick.

    //Melee Attack Pathfinder
    private double meleeDamage;
    private double meleeKnockbackGiven;
    private double meleeKnockbackTaken;
    private double meleeInvincibilityGiven;

    //Spawner Pathfinder
    private int spawnAmount;
    private int spawnDelay;
    private int spawnLevel;
    private RpgEntityType spawnType;

    //Hop pathfinder
    private double hopHeight;
    private double hopStrength;
    private double hopSeekRange;

    public double getHopSeekRange()
    {
        return hopSeekRange;
    }

    public void setHopSeekRange(double hopSeekRange)
    {
        this.hopSeekRange = hopSeekRange;
    }

    public double getHopHeight()
    {
        return hopHeight;
    }

    public void setHopHeight(double hopHeight)
    {
        this.hopHeight = hopHeight;
    }

    public double getHopStrength()
    {
        return hopStrength;
    }

    public void setHopStrength(double hopStrength)
    {
        this.hopStrength = hopStrength;
    }

    public double getWalkSpeed()
    {
        return walkSpeed;
    }

    public void setWalkSpeed(double walkSpeed)
    {
        this.walkSpeed = walkSpeed;
    }


    public double getMeleeKnockbackGiven()
    {
        return meleeKnockbackGiven;
    }

    public void setMeleeKnockbackGiven(double meleeKnockbackGiven)
    {
        this.meleeKnockbackGiven = meleeKnockbackGiven;
    }



    public double getHealthRegeneration()
    {
        return healthRegeneration;
    }

    public void setHealthRegeneration(double healthRegeneration)
    {
        this.healthRegeneration = healthRegeneration;
    }
    public int getMaxAggression()
    {
        return maxAggression;
    }

    public double getMeleeKnockbackTaken()
    {
        return meleeKnockbackTaken;
    }

    public void setMeleeKnockbackTaken(double meleeKnockbackTaken)
    {
        this.meleeKnockbackTaken = meleeKnockbackTaken;
    }

    public int getSpawnAmount()
    {
        return spawnAmount;
    }

    public void setSpawnAmount(int spawnAmount)
    {
        this.spawnAmount = spawnAmount;
    }

    public int getSpawnDelay()
    {
        return spawnDelay;
    }

    public void setSpawnDelay(int spawnDelay)
    {
        this.spawnDelay = spawnDelay;
    }

    public int getSpawnLevel()
    {
        return spawnLevel;
    }

    public void setSpawnLevel(int spawnLevel)
    {
        this.spawnLevel = spawnLevel;
    }

    public RpgEntityType getSpawnType()
    {
        return spawnType;
    }

    public void setSpawnType(RpgEntityType spawnType)
    {
        this.spawnType = spawnType;
    }

    public double getMeleeInvincibilityGiven()
    {
        return meleeInvincibilityGiven;
    }

    public void setMeleeInvincibilityGiven(double meleeInvincibilityGiven)
    {
        this.meleeInvincibilityGiven = meleeInvincibilityGiven;
    }

    public ArrayList<DamageReaction> getDamageReactions()
    {
        return damageReactions;
    }

    public void addDamageReaction(DamageReaction damageReactions)
    {
        this.damageReactions.add(damageReactions);
    }


    public ArrayList<DeathReaction> getDeathReactions()
    {
        return deathReactions;
    }

    public void addDeathReaction(DeathReaction deathReactions)
    {
        this.deathReactions.add(deathReactions);
    }

    public void reduceAggression(){
        if(isAggressive){
            this.aggression -= 1;
            if(this.aggression <= 0){
                setAggressive(false);
            }
        }
    }


    public void setMaxAggression(int maxAggression)
    {
        this.maxAggression = maxAggression;
    }


    public double getMeleeDamage()
    {
        return meleeDamage;
    }

    public void setMeleeDamage(double meleeDamage)
    {
        this.meleeDamage = meleeDamage;
    }






    public Location getLocation(){
        return getEntity().getLocation();
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    private int level;

    public void setAggressive(boolean agro){
        isAggressive = agro;
    }

    public boolean isAggressive(){
        return isAggressive;
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

    public void setMaxHealth(double maxHealth)
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
            RpgEntityList.removeEntity(entity);
            LivingEntity livingEntity = (LivingEntity) entity;
            livingEntity.setHealth(0);
        } else {
            System.out.println("Could not kill Entity");
        }
    }

    public void rawDamageEntity(double damage, DamageType damageType)
    {
        ((LivingEntity) getEntity()).damage(0);
        if(this.damageReactions != null){
            for(DamageReaction damageReaction : this.damageReactions){
                damageReaction.onDamage(damageType);
            }
        }
        this.setHealth(health - damage);
        if(health <= 0){
            if(damageType.hasPlayer()){
                Player player = damageType.getPlayer();
                player.sendMessage("You killed a level " + this.getLevel() + " " + this.getName() + " with " + damageType.getDamager());
            }
            if(this.deathReactions != null){
                for(DeathReaction deathReaction : this.deathReactions){
                    deathReaction.onDeath(damageType);
                }
            }
            kill();
        }
    }

    public void meleeDamageEntity(double dmg, DamageType damageType){
        //Eventually filter this using armour etc
        getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, getEntity().getLocation().add(0, 1, 0), 100, 0.2, 0.2, 0.2, new MaterialData(Material.REDSTONE_BLOCK));
        getEntity().getWorld().playSound(getEntity().getLocation(), Sound.BLOCK_STONE_BREAK, 2.0f, 1.4f);
        rawDamageEntity(dmg, damageType);
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
