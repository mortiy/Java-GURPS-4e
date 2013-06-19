package net.mortiy.gurps.rules.equipment.weapon;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.Item;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponMode;

import java.util.List;

/**
 * Items that character can use in the fight
 */
public abstract class Weapon extends Item {

    protected List<? extends WeaponMode> weaponModes;
    protected WeaponMode activeWeaponMode;
    /**
     * Skill required to use this weapon
     */
    protected Class requiredSkills[];

    protected int minStrength;


    public Weapon(String name, TechLevel.Level techLevel, List<? extends WeaponMode> weaponModes, float cost, float weight) {
        super(name, techLevel, cost, weight);
        this.weaponModes = weaponModes;
        activeWeaponMode = weaponModes.get(0);
    }

    public List<? extends WeaponMode> getWeaponModes() {
        return weaponModes;
    }

    public void setActiveWeaponMode(WeaponMode activeWeaponMode) {
        if(weaponModes.contains(activeWeaponMode)){
            this.activeWeaponMode = activeWeaponMode;
        } else {
            //Log.w("Weapon", String.format("Weapon '%s' has no '%s' damage type, so it's impossible to set it as active.", getName(), activeWeaponMode.()));
        }
    }

    public Class getRequiredSkillClass(){
        return requiredSkills[0];
    }

    public WeaponMode getActiveWeaponMode() {
        return activeWeaponMode;
    }

    public int getMinStrength() {
        return minStrength;
    }
}
