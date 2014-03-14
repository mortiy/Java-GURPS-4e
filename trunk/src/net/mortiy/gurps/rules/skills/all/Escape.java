package net.mortiy.gurps.rules.skills.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.skills.Skill;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 13.12.12
 * Time: 0:46
 * To change this template use File | Settings | File Templates.
 */
public class Escape extends Skill {
    public Escape(Individual individual) {
        super(individual, "Escape", Attribute.Dexterity, Difficulty.Hard);
    }
}
