package me.sirlich.AsmodeusRpg.core;
import me.sirlich.AsmodeusRpg.abilities.Ability;

public class RpgPlayer
{
    private Ability toggleFlyAbility;
    private boolean canUseToggleFlyAbility;
    private int toggleFlyAbilityLevel;

    private Ability switchHandAbility;
    private boolean canUseSwitchHandAbility;
    private int switchHandAbilityLevel;

    private Ability	dropWeaponAbility;
    private boolean canUseDropWeaponAbility;
    private int dropWeaponAbilityLevel;


    private boolean canLoseEnergy;

    public boolean isCanUseToggleFlyAbility()
    {
        return canUseToggleFlyAbility;
    }

    public void setCanUseToggleFlyAbility(boolean canUseToggleFlyAbility)
    {
        this.canUseToggleFlyAbility = canUseToggleFlyAbility;
    }

    public int getToggleFlyAbilityLevel()
    {
        return toggleFlyAbilityLevel;
    }

    public void setToggleFlyAbilityLevel(int toggleFlyAbilityLevel)
    {
        this.toggleFlyAbilityLevel = toggleFlyAbilityLevel;
    }

    public boolean isCanUseSwitchHandAbility()
    {
        return canUseSwitchHandAbility;
    }

    public void setCanUseSwitchHandAbility(boolean canUseSwitchHandAbility)
    {
        this.canUseSwitchHandAbility = canUseSwitchHandAbility;
    }

    public int getSwitchHandAbilityLevel()
    {
        return switchHandAbilityLevel;
    }

    public void setSwitchHandAbilityLevel(int switchHandAbilityLevel)
    {
        this.switchHandAbilityLevel = switchHandAbilityLevel;
    }

    public boolean isCanUseDropWeaponAbility()
    {
        return canUseDropWeaponAbility;
    }

    public void setCanUseDropWeaponAbility(boolean canUseDropWeaponAbility)
    {
        this.canUseDropWeaponAbility = canUseDropWeaponAbility;
    }

    public int getDropWeaponAbilityLevel()
    {
        return dropWeaponAbilityLevel;
    }

    public void setDropWeaponAbilityLevel(int dropWeaponAbilityLevel)
    {
        this.dropWeaponAbilityLevel = dropWeaponAbilityLevel;
    }

    public Ability getToggleFlyAbility() {
        return toggleFlyAbility;
    }

    public void setToggleFlyAbility(Ability doubleJumpAbility) {
        this.toggleFlyAbility = doubleJumpAbility;
    }

    public Ability getSwitchHandAbility() {
        return switchHandAbility;
    }

    public void setSwitchHandAbility(Ability switchHandAbility) {
        this.switchHandAbility = switchHandAbility;
    }

    public Ability getDropWeaponAbility() {
        return dropWeaponAbility;
    }

    public void setDropWeaponAbility(Ability dropWeaponAbility) {
        this.dropWeaponAbility = dropWeaponAbility;
    }

    public boolean isCanLoseEnergy() {
        return canLoseEnergy;
    }

    public void setCanLoseEnergy(boolean canLoseEnergy) {
        this.canLoseEnergy = canLoseEnergy;
    }

}
