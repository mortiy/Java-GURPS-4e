package net.mortiy.gurps.rules.attributes.secondary;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.SecondaryCharacteristic;

/**
 * Will measures your ability to withstand psychological stress (brainwashing, fear, hypnotism, interrogation,
 * seduction, torture, etc.) and your resistance to supernatural attacks (magic, psionics, etc.).
 * TODO: You cannot raise Will past 20, or lower it by more than 4, without GM permission.
 * By default, Will is equal to IQ.
 */
public class Will extends SecondaryCharacteristic {
    public Will(Individual individual) {
        super(individual);
        attribute = Attribute.Will;
        levelCost = 5;
    }

    public float getValue(){
        return level + individual.getAttribute(Attribute.Intelligence).getValue();
    }

}
