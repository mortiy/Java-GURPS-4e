package net.mortiy.gurps.rules.attributes.basic;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.BasicAttribute;

/**
 * Dexterity measures a combination
 of agility, coordination, and fine
 motor ability. It controls your basic
 ability at most athletic, fighting, and
 vehicle-operation skills, and at craft
 skills that call for a delicate touch.
 */
public class Dexterity extends BasicAttribute {

    public Dexterity() {
        super();
        attribute =  Attribute.Dexterity;
        levelCost = 20;
    }
}
