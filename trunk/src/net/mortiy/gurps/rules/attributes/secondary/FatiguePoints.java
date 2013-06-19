package net.mortiy.gurps.rules.attributes.secondary;

import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.SecondaryCharacteristic;

/**
 * Fatigue Points represent your body’s "energy supply."
 * By default, you have FP equal to your HT.
 * You can increase FP at the cost of 3 points per FP, or reduce FP for -3 points per FP.
 * TODO: In a realistic campaign, the GM should not allow FP to vary by more than ±30% of HT;
 * e.g., a HT 10 character could have between 7 and 13 FP.
 * Non-humans and supers are not subject to this limit.
 * Also, while HT is usually limited to 20, there is no such limit on FP.
 * TODO: You burn FP gradually during strenuous activity. Disease, heat, hunger, missed sleep, and the like can also sap FP.
 */
public class FatiguePoints extends SecondaryCharacteristic {
    public FatiguePoints(net.mortiy.gurps.rules.Character character) {
        super(character);
        levelCost = 3;
    }

    public float getValue() {
        return level + character.getAttribute(Attribute.Health).getValue();
    }
}
