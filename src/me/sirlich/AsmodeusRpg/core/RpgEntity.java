package me.sirlich.AsmodeusRpg.core;

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
    private double maxHealth;
    private double health;
    private boolean isAggressive;
    private int maxAggression;
    private int aggression;
    private String name;
    private double meleeDamage;
    private double meleeKnockback;
    private DeathReaction deathReaction;

    public DamageReaction getDamageReaction()
    {
        return damageReaction;
    }

    public void setDamageReaction(DamageReaction damageReaction)
    {
        this.damageReaction = damageReaction;
    }

    private DamageReaction damageReaction;

    public DeathReaction getDeathReaction()
    {
        return deathReaction;
    }

    public void setDeathReaction(DeathReaction deathReaction)
    {
        this.deathReaction = deathReaction;
    }


    public RpgEntity(){
        maxHealth = 100;
        health = 100;
        isAggressive = false;
        maxAggression = 500;
        aggression = 0;
        name = "RpgCow";
        meleeDamage = 5;
        meleeKnockback = 0.3;
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

    public double getMeleeKnockback()
    {
        return meleeKnockback;
    }

    public void setMeleeKnockback(double meleeKnockback)
    {
        this.meleeKnockback = meleeKnockback;
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
