package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Urban Survival
 * ========================
 * Description:
 */
public class UrbanSurvival extends Skill {
    public UrbanSurvival(Individual individual) {
        super(individual, "Urban Survival", Attribute.Perception, Difficulty.Average);
    }
}
