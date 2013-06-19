package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.table.rolls.SimpleRoll;
import net.mortiy.gurps.rules.traits.categories.Mental;

/**
 * Many mental disadvantages do not affect you constantly – you may attempt to control your urges.
 * An asterisk (*) appears next to the point cost of any disadvantage that offers a chance to resist.
 * For each disadvantage like this, you must choose a selfcontrol number:
 * the number you must roll on 3d to avoid giving in.
 */
public class SelfControlDisadvantage extends FixedTrait implements Disadvantage, Mental {
    public static enum Frequency {

        /**
         * Quite rarely (roll of 6 or less): 2 x listed cost
         */
        QuiteRarely,
        /**
         * Fairly often (roll of 9 or less): 1.5 x listed cost
         */
        FairlyOften,
        /**
         * Quite often (roll of 12 or less): listed cost
         */
        QuiteOften,
        /**
         * Almost all the time (roll of 15 or less): 0.5 x listed cost
         */
        Always
    }

    /**
     * Cost multipliers
     */
    private float costMultipliers[] = new float[] { 2f, 1.5f, 1f, 0.5f,};
    /**
     * Frequency rolls
     */
    private int[] frequencyRoll = new int[]{6, 9, 12, 15};
    private Frequency frequency;

    public SelfControlDisadvantage(Character character, String name, Integer cost, Frequency frequency) {
        super(character, name, cost);
        this.frequency = frequency;
    }

    @Override
    public int getCost() {
        return (int) Math.floor(super.getCost() * costMultipliers[frequency.ordinal()]);
    }

    public DiceRoller.RollResult performSelfControlRoll() {
        int rollAgainst = (int) Math.floor(frequencyRoll[frequency.ordinal()] + character.getTotalModifier(Rolls.SelfControlRoll));
        return new SimpleRoll(rollAgainst).getResult();
    }

}


