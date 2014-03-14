package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Climbing
 * =============================
 * Description:
 * (p. 183)
 */
public class Climbing extends Skill {

    public Climbing( Individual individual) {
        super(individual, "Climbing", Attribute.Dexterity, Difficulty.Average);
    }
}
