package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Guns
 * ========================
 * Description:
 */
public class Guns extends Skill {
    public Guns(Individual individual, String speciality) {
        super(individual, "Guns", speciality, Attribute.Dexterity, Difficulty.Easy);
    }
}
