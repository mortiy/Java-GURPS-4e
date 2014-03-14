package net.mortiy.gurps.rules.attributes.secondary;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.SecondaryCharacteristic;

/**
 * Your Basic Move is your ground
 * speed in yards per second. This is how fast you can run – or roll, slither, etc. –
 * without encumbrance (although you
 * can go a little faster if you “sprint” in a
 * straight line; see p. 354).
 * TODO: Home Gravity (p. 17)
 */
public class BasicMove extends SecondaryCharacteristic {
    public BasicMove(Individual individual) {
        super(individual);
        levelCost = 5;
    }

    @Override
    public float getValue() {
        // Encumbrance influence (p. 17)
        float modifier = 1.0f - individual.getEncumbrance().ordinal() * 0.2f;
        float value = (level + individual.getBasicSpeed().getValue()) * modifier;
        // Encumbrance can never reduce Move or Dodge below 1.
        if (value < 1.0f) {
            value = 1.0f;
        }
        return (float) Math.floor(value);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
