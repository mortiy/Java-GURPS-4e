package net.mortiy.gurps.rules.equipment.weapon;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.weapon.statistics.*;

import java.util.Arrays;
import java.util.List;

/**
 * Melee weapons are grouped under the skills required to use them. Skill
 * names appear in capital letters, with defaults in parentheses; e.g.,
 * “AXE/MACE (DX-5, Flail-4, or Two- Handed Axe/Mace-3).” If there is
 * more than one way to use a weapon, each method gets its own line.
 * If multipleskills let you use a weapon, the weapon appears under each skill.
 * For example, both Staff skill and Two-Handed Sword skill let you wield a
 * quarterstaff – and either lets you swing the staff or thrust with it.
 */
public class MusclePoweredMeleeWeapon extends MeleeWeapon {

    public MusclePoweredMeleeWeapon(
            String name, Class requiredSkill[], TechLevel.Level techLevel,
            MusclePoweredMeleeWeaponMode weaponModes[],
            int cost, float weight, int minStrength) {
        this(name, requiredSkill, techLevel, Arrays.asList(weaponModes), cost, weight, minStrength);
    }

    public MusclePoweredMeleeWeapon(
            String name, Class requiredSkill[], TechLevel.Level techLevel,
            List<? extends WeaponMode> damageTypes,
            int cost, float weight, int minStrength) {

        super(name, techLevel, damageTypes, cost, weight);

        this.requiredSkills = requiredSkill;
        this.minStrength = minStrength;

    }

    public List<MusclePoweredMeleeWeaponMode> getWeaponModes(){
        return (List<MusclePoweredMeleeWeaponMode>) weaponModes;
    }

}


