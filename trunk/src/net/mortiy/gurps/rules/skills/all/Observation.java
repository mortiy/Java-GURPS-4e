package net.mortiy.gurps.rules.skills.all;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.Individual;

/**
 * Observation
 * ========================
 * Description:
 */
public class Observation extends Skill {
    public Observation(Individual individual) {
        super(individual, "Observation", Attribute.Perception, Difficulty.Average);
    }
}
