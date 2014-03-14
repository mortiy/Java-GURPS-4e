package net.mortiy.gurps.rules.attributes.secondary;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.SecondaryCharacteristic;

/**
 * Basic Lift is the maximum weight
 * you can lift over your head with one
 * hand in one second. It is equal to
 * (ST*ST)/5 lbs. If BL is 10 lbs. or more,
 * round to the nearest whole number; e.g.,
 * 16.2 lbs. becomes 16 lbs. The average
 * human has ST 10 and a BL of 20 lbs.
 */
public class BasicLift extends SecondaryCharacteristic {
    public BasicLift(Individual individual) {
        super(individual);
        attribute = Attribute.BasicLift;
    }

    /**
     * Get maximum weight in Pounds which character can carry
     * @return Weight in pounds.
     */
    @Override
    public float getValue(){
        int strengthLevel = individual.getBasicAttribute(Attribute.Strength).getLevel();
        return Math.round(strengthLevel * strengthLevel / 5f);

    }
}
