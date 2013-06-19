package net.mortiy.gurps.rules.attributes.secondary;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.SecondaryCharacteristic;

/**
 * Perception represents your general alertness.
 * The GM makes a "Sense roll" against your Per to determine whether you notice something (see Sense Rolls, p. 358).
 * By default, Per equals IQ, but you can increase it for 5 points per +1, or reduce it for -5 points per -1.
 * TODO: You cannot raise Per past 20, or lower it by more than 4, without GM permission
 */
public class Perception extends SecondaryCharacteristic {
    public Perception(net.mortiy.gurps.rules.Character character) {
        super(character);
        levelCost = 5;
    }

    public float getValue(){
        return level + character.getAttribute(Attribute.Intelligence).getValue();
    }
}
