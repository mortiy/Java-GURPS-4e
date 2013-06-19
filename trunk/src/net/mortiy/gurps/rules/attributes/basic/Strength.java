package net.mortiy.gurps.rules.attributes.basic;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.BasicAttribute;

/**
 * Strength measures physical power
 and bulk. It is crucial if you are a
 warrior in a primitive world, as high
 ST lets you dish out and absorb more
 damage in hand-to-hand combat.
 */
public class Strength extends BasicAttribute {
    public Strength() {
        super();
        attribute = Attribute.Strength;
        levelCost = 10;
    }
}
