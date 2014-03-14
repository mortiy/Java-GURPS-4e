package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Pickpocket
 * =============================
 *
 */
public class Pickpocket extends Skill {

    public Pickpocket(Individual individual) {
        super(individual, "Pickpocket", Attribute.Dexterity, Difficulty.Hard);
    }
}
