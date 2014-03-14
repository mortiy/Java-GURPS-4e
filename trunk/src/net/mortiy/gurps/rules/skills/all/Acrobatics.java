package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Individual;

/**
 * Acrobatics
 * ========================
 * Description:
 */
public class Acrobatics extends Skill {
    public Acrobatics(Individual individual) {
        super(individual, "Acrobatics", Attribute.Dexterity, Difficulty.Hard);
    }
}
