package me.sirlich.AsmodeusRpg.core;

import me.sirlich.AsmodeusRpg.abilities.Ability;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

public class RpgPlayer
{
    private Ability mobilityAbility;
    private boolean canUseMobilityAbility;
    private int mobilityAbilityLevel;
    private Ability carnageAbility;
    private boolean canUseCarnageAbility;
    private int carnageAbilityLevel;
    private Ability mythicalAbility;
    private boolean canUseMythicalAbility;
    private float speedModifier;
    private double staminaDrainOnRunModifier;
    private double stamineRegenRateModifier;
    private int maxStamina;
    private double staminaDrainOnSwordUseModifier;
    private double stamineDrainOnAxeUseModifier;
    private double stamineDrainOnMagicUseModifier;
    private double staminaDrainOnBowUseModifier;
    private double healthRegenPerSecond;
    private double maxHealth;
    private double health;
    private double takeKnockbackModifier;

    public double getHealthRegenPerSecond()
    {
        return healthRegenPerSecond;
    }

    public void setHealthRegenPerSecond(double healthRegenPerSecond)
    {
        this.healthRegenPerSecond = healthRegenPerSecond;
    }

    public int getMobilityAbilityLevel()
    {
        return mobilityAbilityLevel;
    }

    public void setMobilityAbilityLevel(int mobilityAbilityLevel)
    {
        this.mobilityAbilityLevel = mobilityAbilityLevel;
    }

    public void editSpeedModifier(Float f)
    {
        speedModifier += f;
        Player player = getPlayer();
        if (speedModifier != 0 && speedModifier <= 1) {
            player.setWalkSpeed(speedModifier);
        } else {
            player.setWalkSpeed(1);
        }
    }


    public void setSpeedModifier(Float f)
    {
        speedModifier = f;
        Player player = getPlayer();
        if (speedModifier != 0 && speedModifier <= 1 && speedModifier >= 0) {
            player.setWalkSpeed(speedModifier);
        } else {
            //Speed exceeds max allowed, save the greater number but safely set player speed to max.
            player.setWalkSpeed(1);
        }
    }

    /*
    All damage methods should eventually call this method.
    You should avoid calling this outside of this class though.
    Call one of these instead.
     - Melee Damage
     - Magic Damage
     - Ranged Damage
     */
    public void rawDamage(double dmg)
    {
        editHealth(-dmg);
        getPlayer().getWorld().spawnParticle(Particle.BLOCK_CRACK, getPlayer().getLocation().add(0, 1, 0), 100, 0.2, 0.2, 0.2, new MaterialData(Material.REDSTONE_BLOCK));
    }

    public void rawHeal(double heal)
    {
        editHealth(heal);
        getPlayer().getWorld().spawnParticle(Particle.HEART, getPlayer().getLocation().add(0, 1, 0), 100, 0.2, 0.2, 0.2);
    }

    /*
    This is a safe way to edit players max health.
    Consider using:
    - setMaxHealth
    NOTE: This does not edit their displayed health.
     */
    public void editMaxHealth(double healthChange)
    {
        maxHealth += healthChange;

        //Truncace player health
        if(getHealth() > maxHealth){
            setHealth(maxHealth);
        }
        refreshDisplayedHealth();
    }

    public double getMaxHealth()
    {
        return maxHealth;
    }

    /*
    This is a safe way to edit players max health.
    Consider using:
    - editMaxHealth
    NOTE: This does not edit their displayed health.
     */
    public void setMaxHealth(double i)
    {
        maxHealth = i;
        if(health > maxHealth){
            setHealth(maxHealth);
        }
        refreshDisplayedHealth();
    }

    /*
    This method resets the displayed hearts of players.
    Should not need to call from outside very often.
     */
    public void refreshDisplayedHealth()
    {
        System.out.println("Setting player health!");
        this.getPlayer().setHealth(20 * (health / maxHealth));
    }

    public double getHealth()
    {
        return health;
    }

    /*
    This method can be used to force set a players health to a certain value.
    Will not force health past maxHealth.
     */
    public void setHealth(double i)
    {
        if (i > maxHealth) {
            health = maxHealth;
        } else {
            health = i;
        }
        refreshDisplayedHealth();
    }

    /*
    This method should not be called outside if it can be helped.
    See:
     - rawDamage
     - rawHeal
     */
    public void editHealth(double i)
    {
        if (health + i > maxHealth) {
            health = maxHealth;
        } else {
            health += i;
        }
        refreshDisplayedHealth();
    }

    /*
    This method takes a magicDamage value, applies magic armour modifier, and calls rawDamage.
     */
    public void magicDamage(double dmg){
        //This method should look at players armour. For now, it just takes away 50%
        rawDamage(dmg/2);
    }

    /*
    This method takes a rangedDamage value, applies magic armour modifier, and calls rawDamage.
     */
    public void rangedDamage(double dmg){
        //This method should look at players armour. For now, it just takes away 25%
        rawDamage(dmg*0.75);
    }

    /*
    This method takes a meleeDamage value, applies magic armour modifier, and calls rawDamage.
     */
    public void meleeDamage(double dmg){
        //This method should look at players armour. For now, it just deals the damage.
        rawDamage(dmg);
    }

    public Player getPlayer()
    {
        return PlayerList.getPlayer(this);
    }

    public boolean isCanUseMobilityAbility()
    {
        return canUseMobilityAbility;
    }

    public void setCanUseMobilityAbility(boolean canUseMobilityAbility)
    {
        this.canUseMobilityAbility = canUseMobilityAbility;
    }

    public boolean isCanUseCarnageAbility()
    {
        return canUseCarnageAbility;
    }

    public void setCanUseCarnageAbility(boolean canUseCarnageAbility)
    {
        this.canUseCarnageAbility = canUseCarnageAbility;
    }

    public int getCarnageAbilityLevel()
    {
        return carnageAbilityLevel;
    }

    public void setCarnageAbilityLevel(int carnageAbilityLevel)
    {
        this.carnageAbilityLevel = carnageAbilityLevel;
    }

    public boolean isCanUseMythicalAbility()
    {
        return canUseMythicalAbility;
    }

    public void setCanUseMythicalAbility(boolean canUseMythicalAbility)
    {
        this.canUseMythicalAbility = canUseMythicalAbility;
    }


    public Ability getMobilityAbility()
    {
        return mobilityAbility;
    }

    public void setMobilityAbility(Ability doubleJumpAbility)
    {
        this.mobilityAbility = doubleJumpAbility;
    }

    public Ability getCarnageAbility()
    {
        return carnageAbility;
    }

    public void setCarnageAbility(Ability carnageAbility)
    {
        this.carnageAbility = carnageAbility;
    }

    public Ability getMythicalAbility()
    {
        return mythicalAbility;
    }

    public void setMythicalAbility(Ability mythicalAbility)
    {
        this.mythicalAbility = mythicalAbility;
    }
}
