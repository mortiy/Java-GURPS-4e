package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.12.12
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class Stealth extends Skill {
    public Stealth(Individual individual) {
        super(individual, "Stealth", Attribute.Dexterity, Difficulty.Average);
    }
}
