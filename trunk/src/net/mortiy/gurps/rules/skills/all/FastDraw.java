package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Individual;

/**
 * Fast-Draw
 * ========================
 * Description:
 */
public class FastDraw extends Skill {
    public FastDraw(Individual individual, String speciality) {
        super(individual, "Fast-Draw", speciality, Attribute.Dexterity, Difficulty.Easy);
    }
}
