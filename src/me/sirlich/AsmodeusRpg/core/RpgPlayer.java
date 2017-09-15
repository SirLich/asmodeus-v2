package me.sirlich.AsmodeusRpg.core;
import me.sirlich.AsmodeusRpg.abilities.Ability;
import org.bukkit.entity.Player;

public class RpgPlayer
{
    /*
    These vars are all about player abilities.
     */
    private Ability flyAbility;
    private boolean canUseFlyAbility;
    private int flyAbilityLevel;

    private Ability swapAbility;
    private boolean canUseSwapAbility;
    private int swapAbilityLevel;

    private Ability dropAbility;
    private boolean canUseDropAbility;
    private int dropAbilityLevel;

    /*
    These vars are all about passive things that effect the player.
    Things that might influence these values:
     - Armour
     - Weapons
     - Classes
     - Abilities
     - Artifacts
     - Mob hits (say, that cause slowness)
     */
    private float speedModifier;
    private double staminaDrainOnRunModifier;
    private double stamineRegenRateModifier;
    private int maxStamina;
    private double staminaDrainOnSwordUseModifier;
    private double stamineDrainOnAxeUseModifier;
    private double stamineDrainOnMagicUseModifier;
    private double staminaDrainOnBowUseModifier;
    private double healthRegenPerSecond;
    private double armorValue;
    private double experienceGainedModifier;

    /*
    This method is used to add or subtract speed from a players passive walking/running speed.
    The speed modifier should be kept between the values of -10,10.
     */
    public void IncreaseSpeedModifier(Float f){
        speedModifier += f;
        Player player = getPlayer();
        if(speedModifier != 0){
            player.setWalkSpeed(speedModifier/10);
        } else{
            player.setWalkSpeed(0f);
        }
    }

    /*
    This method is used to SET player speed.
    Please use IncreaseSpeedMofifier for everything: This is just for initialization.
     */
    public void SetSpeedModifier(Float f){
        speedModifier = f;
        Player player = getPlayer();
        if(speedModifier != 0){
            player.setWalkSpeed(speedModifier/10);
        } else{
            player.setWalkSpeed(0f);
        }
    }


    /*
    This method is used to get a Player from an RpgPlayer.
    I make use of the PlayerList to do so.
     */
    public Player getPlayer(){
        System.out.println("Into getPlayer inside RpgPlayer!");
        return PlayerList.getPlayer(this);
    }

    public boolean isCanUseFlyAbility()
    {
        return canUseFlyAbility;
    }

    public void setCanUseFlyAbility(boolean canUseFlyAbility)
    {
        this.canUseFlyAbility = canUseFlyAbility;
    }

    public int getFlyAbilityLevel()
    {
        return flyAbilityLevel;
    }

    public void setFlyAbilityLevel(int flyAbilityLevel)
    {
        this.flyAbilityLevel = flyAbilityLevel;
    }

    public boolean isCanUseSwapAbility()
    {
        return canUseSwapAbility;
    }

    public void setCanUseSwapAbility(boolean canUseSwapAbility)
    {
        this.canUseSwapAbility = canUseSwapAbility;
    }

    public int getSwapAbilityLevel()
    {
        return swapAbilityLevel;
    }

    public void setSwapAbilityLevel(int swapAbilityLevel)
    {
        this.swapAbilityLevel = swapAbilityLevel;
    }

    public boolean isCanUseDropAbility()
    {
        return canUseDropAbility;
    }

    public void setCanUseDropAbility(boolean canUseDropAbility)
    {
        this.canUseDropAbility = canUseDropAbility;
    }

    public int getDropAbilityLevel()
    {
        return dropAbilityLevel;
    }

    public void setDropAbilityLevel(int dropAbilityLevel)
    {
        this.dropAbilityLevel = dropAbilityLevel;
    }

    public Ability getFlyAbility() {
        return flyAbility;
    }

    public void setFlyAbility(Ability doubleJumpAbility) {
        this.flyAbility = doubleJumpAbility;
    }

    public Ability getSwapAbility() {
        return swapAbility;
    }

    public void setSwapAbility(Ability swapAbility) {
        this.swapAbility = swapAbility;
    }

    public Ability getDropAbility() {
        return dropAbility;
    }

    public void setDropAbility(Ability dropAbility) {
        this.dropAbility = dropAbility;
    }
}
