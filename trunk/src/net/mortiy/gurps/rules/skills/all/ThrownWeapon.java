package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Thrown Weapon
 * ========================
 * Description:
 */
public class ThrownWeapon extends Skill {
    public ThrownWeapon(Individual individual, String speciality) {
        super(individual, "Thrown Weapon", speciality, Attribute.Dexterity, Difficulty.Easy);
    }
}
