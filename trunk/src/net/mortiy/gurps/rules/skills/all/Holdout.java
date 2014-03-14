package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Holdout
 * ========================
 * Description:
 */
public class Holdout extends Skill {
    public Holdout(Individual individual) {
        super(individual, "Holdout", Attribute.Intelligence, Difficulty.Average);
    }
}
