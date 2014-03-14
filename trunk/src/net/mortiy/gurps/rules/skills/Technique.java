package net.mortiy.gurps.rules.skills;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;

/**
 * Technique
 * ==================================
 * You (or your GM!) may want a way to improve your ability with a specific
 * application of a skill without increasing the overall skill level.
 * This is realistic – people do train at particular tasks
 * to the exclusion of others – but allowing this in the game makes play (and
 * character sheets) more complex. As a result, the following section is  purely optional.
 * A “technique” is any feat that you can practice and perfect separately
 * from the skill that allows you to peform that task. It is a specific action
 * covered by the parent skill, studied on its own.
 * It differs from an optional specialty (p. 169), which covers a body of theory, not an action.
 * Techniques work a lot like skills, but with a few important differences.
 * TODO: Technique (p. 229)
 */
public class Technique extends Skill {
    public Technique(Individual individual, String name, Attribute attribute, Difficulty difficulty) {
        super(individual, name, attribute, difficulty);
    }
}
