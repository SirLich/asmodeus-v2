package me.sirlich.AsmodeusRpg.core;
import me.sirlich.AsmodeusRpg.abilities.Ability;

public class RpgPlayer
{
    private Ability toggleFlyAbility;
    private Ability switchHandAbility;
    private Ability	dropWeaponAbility;
    private boolean canLoseEnergy;

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
