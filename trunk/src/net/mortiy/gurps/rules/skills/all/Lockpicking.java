package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Lockpicking
 * ===========================
 */
public class Lockpicking extends Skill {
    public Lockpicking(Individual individual) {
        super(individual, "Lockpicking", Attribute.Intelligence, Difficulty.Average);
    }
}
