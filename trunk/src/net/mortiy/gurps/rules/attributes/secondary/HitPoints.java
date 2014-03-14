package net.mortiy.gurps.rules.attributes.secondary;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.SecondaryCharacteristic;

/**
 * Hit Points represent your body’s ability to sustain injury.
 * By default, you have HP equal to your ST.
 * TODO: In a realistic campaign, the GM should not allow HP to vary by more than ±30% of ST
 */
public class HitPoints extends SecondaryCharacteristic {

    public HitPoints(Individual individual) {
        super(individual);
        attribute = Attribute.HitPoints;
        levelCost = 2;
        this.currentValue = getValue();
    }

    public float getValue() {
        return level + individual.getAttribute(Attribute.Strength).getValue();
    }

    @Override
    /**
     * Get Hit Points cost considering body size discounts
     */
    public float getCost() {

        // Size Modifier discount (p. 16)
        int sizeModifier = individual.getBody().getSizeModifier();
        float sizeDiscount = 1.0f;
        if (sizeModifier > 0) {
            if (sizeModifier > 8) {
                sizeModifier = 8;
            }
            sizeDiscount = 1.0f - sizeModifier * 0.10f;
        }
        return super.getCost() * sizeDiscount;    //To change body of overridden methods use File | Settings | File Templates.
    }
}
