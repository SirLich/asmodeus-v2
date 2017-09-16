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

    private Ability swapAbility;
    private boolean canUseSwapAbility;
    private int swapAbilityLevel;

    private Ability dropAbility;
    private boolean canUseDropAbility;

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
    The value range is between 0 (no walk) and 1 (speed 10 in essentials)
    Vanilla walking speed: 0.2
    Sneaking walking speed: 0.1
    So boots that add 5% walking speed should add 0.01 speed (and you should add -0.01 when you take them off)
     */
    public void editSpeedModifier(Float f){
        speedModifier += f;
        Player player = getPlayer();
        if(speedModifier != 0 && speedModifier <= 1){
            player.setWalkSpeed(speedModifier);
        } else{
            System.out.println("A player exceeded their max speed.");
            speedModifier = 0;
            player.setWalkSpeed(speedModifier);
        }
    }

    /*
    This method is used to SET player speed.
    This should only be used in rare cases, such as for initialization.
    Please see editSpeedModifier for a more general case method.
     */
    public void setSpeedModifier(Float f){
        speedModifier = f;
        Player player = getPlayer();
        if(speedModifier != 0 && speedModifier <= 1 && speedModifier >= 0){
            player.setWalkSpeed(speedModifier);
        } else{
            System.out.println("A player exceeded their max speed.");
            speedModifier = 0;
            player.setWalkSpeed(speedModifier);
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
