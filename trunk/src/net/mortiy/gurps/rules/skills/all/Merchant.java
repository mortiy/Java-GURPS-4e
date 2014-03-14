package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.12.12
 * Time: 2:33
 * To change this template use File | Settings | File Templates.
 */
public class Merchant extends Skill {
    public Merchant(Individual individual) {
        super(individual, "Merchant", Attribute.Intelligence, Difficulty.Average);

    }
}
