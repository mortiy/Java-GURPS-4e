package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.12.12
 * Time: 2:34
 * To change this template use File | Settings | File Templates.
 */
public class Finance extends Skill {
    public Finance(Individual individual) {
        super(individual, "Finance", Attribute.Intelligence, Difficulty.Hard);
    }
}
