package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Navigation
 * ========================
 * Description:
 */
public class Navigation extends Skill {
    public Navigation(Individual individual) {
        super(individual, "Navigation", Attribute.Intelligence, Difficulty.Average);
    }
}
