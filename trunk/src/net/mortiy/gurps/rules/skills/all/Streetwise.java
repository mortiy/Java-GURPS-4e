package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Streetwise
 * ========================
 * Description:
 */
public class Streetwise extends Skill {
    public Streetwise(Individual individual) {
        super(individual, "Streetwise", Attribute.Intelligence, Difficulty.Average);
    }
}
