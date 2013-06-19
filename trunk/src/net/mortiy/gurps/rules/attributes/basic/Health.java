package net.mortiy.gurps.rules.attributes.basic;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.BasicAttribute;

/**
 * Health measures energy and vitality.
 It represents stamina, resistance (to
 poison, disease, radiation, etc.), and
 basic “grit.” A high HT is good for anyone
 – but it is vital for low-tech warriors.
 */
public class Health extends BasicAttribute {
    public Health() {
        super();
        attribute =  Attribute.Health;
        levelCost = 10;
    }
}
