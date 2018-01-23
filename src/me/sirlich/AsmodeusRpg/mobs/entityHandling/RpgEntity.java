package me.sirlich.AsmodeusRpg.mobs.entityHandling;

import me.sirlich.AsmodeusRpg.mobs.damageReaction.DamageReaction;
import me.sirlich.AsmodeusRpg.mobs.deathReaction.DeathReaction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import java.util.UUID;

public class RpgEntity
{
    //Generics
    private String name;
    private DeathReaction deathReaction;
    private DamageReaction damageReaction;
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
    private RpgEntityType minionSpawnType;


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

    public RpgEntityType getMinionSpawnType()
    {
        return minionSpawnType;
    }

    public void setMinionSpawnType(RpgEntityType minionSpawnType)
    {
        this.minionSpawnType = minionSpawnType;
    }

    public double getMeleeInvincibilityGiven()
    {
        return meleeInvincibilityGiven;
    }

    public void setMeleeInvincibilityGiven(double meleeInvincibilityGiven)
    {
        this.meleeInvincibilityGiven = meleeInvincibilityGiven;
    }

    public DamageReaction getDamageReaction()
    {
        return damageReaction;
    }

    public void setDamageReaction(DamageReaction damageReaction)
    {
        this.damageReaction = damageReaction;
    }


    public DeathReaction getDeathReaction()
    {
        return deathReaction;
    }

    public void setDeathReaction(DeathReaction deathReaction)
    {
        this.deathReaction = deathReaction;
    }


    //Constructor sets some default shit to stop horrible errors on mob spawn if something doesint set right.
    public RpgEntity(){
        this.maxHealth = 100;
        this.health = 100;
        this.isAggressive = true;
        this.maxAggression = 500;
        this.aggression = 500;
        this.name = "UNDEFINED MOB";
        this.meleeDamage = 10;
        this.meleeKnockbackGiven = 0.4;
        this.meleeKnockbackTaken = 0.1;
        this.meleeInvincibilityGiven = 1;
        this.spawnAmount = 1;
        this.spawnDelay = 20;
        this.spawnLevel = 1;
        this.minionSpawnType = RpgEntityType.RPG_CRITTER;
        this.deathReaction = new DeathReaction();
        this.walkSpeed = 1;
        this.damageReaction = new DamageReaction();
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

    public void editHealth(double i, Player player){
        health += i;
        ((LivingEntity) getEntity()).damage(0);
        if(health <= 0){

            /*
            This part handles the mobs DeathReaction Reaction.
             */
            if(player != null){
                getDeathReaction().onDeath(player);
            } else{
                getDeathReaction().onDeath();
            }
            kill();
        } else if(health > maxHealth){
            health = maxHealth;
        }
    }

    public void rawDamageEntity(double damage, Player player)
    {
        editHealth(-damage, player);

        /*
        This handles the mods reaction to getting damaged.
         */
        if(player != null){
            getDamageReaction().onDamage(player);
        } else{
            getDamageReaction().onDamage();
        }
    }

    public void meleeDamageEntity(double dmg, Player player){
        rawDamageEntity(dmg, player);
        getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, getEntity().getLocation().add(0, 1, 0), 100, 0.2, 0.2, 0.2, new MaterialData(Material.REDSTONE_BLOCK));
        getEntity().getWorld().playSound(getEntity().getLocation(), Sound.BLOCK_STONE_BREAK, 2.0f, 1.4f);
    }

    public void magicDamageEntity(double dmg, Player player){
        rawDamageEntity(dmg, player);
    }

    public void rangedDamageEntity(double dmg, Player player){
        rawDamageEntity(dmg, player);
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
