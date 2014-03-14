package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Fast-Talk
 * ========================
 * Description:
 */
public class FastTalk extends Skill {
    public FastTalk(Individual individual) {
        super(individual, "Fast-Talk", Attribute.Intelligence, Difficulty.Average);
    }
}
