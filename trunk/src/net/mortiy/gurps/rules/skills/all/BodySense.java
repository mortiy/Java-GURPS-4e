package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Individual;

/**
 * Body Sense
 * ========================
 * Description:
 */
public class BodySense extends Skill {
    public BodySense(Individual individual) {
        super(individual, "Body Sense", Attribute.Dexterity, Difficulty.Hard);
    }
}
