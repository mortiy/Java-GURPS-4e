package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Filch
 * ========================
 * Description:
 */
public class Filch extends Skill {
    public Filch(Individual individual) {
        super(individual, "Filch", Attribute.Dexterity, Difficulty.Average);
    }
}
