package net.mortiy.gurps.rules.equipment.weapon.statistics;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.table.RollFormula;

/**
 * Represents damage dealt by muscle powered weapons
 */
public class MusclePoweredDamage  {

    private final static int[] thrustModifiers = new int[]{-1, -1, 0, 0, +1, +1, +2, +2};
    private final static int[] thrustWeakModifiers = new int[]{-6, -6, -5, -5, -4, -4, -3, -3, -2, -2};

    private final static int[] swingModifiers = new int[]{+1, +2, -1, 0};
    private final static int[] swingWeakModifiers = new int[]{-5, -5, -4, -4, -3, -3, -2, -2, -1, 0};


    public static enum Type {
        Thrusting,
        Swinging
    }

    public static RollFormula getDamageFormula(Individual individual, Type type) {
        switch (type) {
            case Thrusting:
                return getThrustDamage(individual);
            case Swinging:
                return getSwingDamage(individual);
            default:
                Log.w("Damage", "Unknown damage type: " + type);
                return new RollFormula(1);
        }

    }

    private static RollFormula getThrustDamage(Individual individual) {
        int strengthLevel = individual.getBasicAttribute(Attribute.Strength).getLevel();
        int diceQuantity;
        int modifier;

        if (strengthLevel > 10) { // 12
            strengthLevel -= 10; // 2
            diceQuantity = (int) Math.ceil(strengthLevel / 8f); // 1
            int modifierIndex = (strengthLevel - (diceQuantity - 1) * 8) % 10 - 1;
            modifier = thrustModifiers[modifierIndex];
        } else {
            diceQuantity = 1;
            int modifierIndex = strengthLevel - 1;
            modifier = thrustWeakModifiers[modifierIndex];

        }

        return new RollFormula(diceQuantity, modifier);
    }

    private static RollFormula getSwingDamage(Individual individual) {
        int strengthLevel = individual.getBasicAttribute(Attribute.Strength).getLevel();
        int diceQuantity = 1;
        int modifier;


        if (strengthLevel >= 60) {
            diceQuantity = (int) Math.floor(strengthLevel / 10f) + 3;
        } else if (strengthLevel > 48) {
            diceQuantity = 8;
        } else if (strengthLevel > 38) {
            diceQuantity = 7;
        } else if (strengthLevel > 30) {
            diceQuantity = 6;
        } else if (strengthLevel > 24) {
            diceQuantity = 5;
        } else if (strengthLevel > 20) {
            diceQuantity = 4;
        } else if (strengthLevel > 16) {
            diceQuantity = 3;
        } else if (strengthLevel > 12) {
            diceQuantity = 2;
        } else if (strengthLevel <= 12) {
            diceQuantity = 1;
        }

        if (strengthLevel > 10) {
            strengthLevel -= 10;
            int k = (int) Math.ceil(strengthLevel / 16f);
            strengthLevel -= (k - 1) * 16 + 1;
            int modifierIndex = (int) Math.floor((strengthLevel % (k * 4)) / k);
            modifier = swingModifiers[modifierIndex];
        } else {
            modifier = swingWeakModifiers[strengthLevel - 1];
        }

        return new RollFormula(diceQuantity, modifier);
    }

}
