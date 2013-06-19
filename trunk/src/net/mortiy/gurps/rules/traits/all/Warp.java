package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.TimeModifier;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.basic.Intelligence;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.rolls.SimpleRoll;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.FixedTrait;
import net.mortiy.gurps.rules.traits.categories.Exotic;
import net.mortiy.gurps.rules.utils.Converter;

/**
 * You have the ability to teleport,
 * traveling from point to point without
 * moving through the intervening space. To do
 * so, you must be able to see your destination
 * with your own eyes, or view it remotely (via
 * closed-circuit TV, someone else’s eyes using
 * Mind Reading with the Sensory enhancement,
 * etc.), or visualize it clearly (which is only possible if
 * you have visited it previously in person).
 */
public class Warp extends FixedTrait implements Advantage, Exotic, Modifier.IModifiable {
    public Warp(Character character) {
        super(character, "Warp", 100);
    }

    float jumpRangeLimit = 0;

    public static enum Enhancement {
        Blind,
        ExtraCarryingCapacity,
        Reliable,
        WarpJump
    }

    public static enum Limitations {
        Hyperjump,
        Naked,
        Psionic,
        PsionicTeleporation,
        RangeLimit
    }

    @Override
    public int getRawCost() {
        return Math.round(cost * (1f - (0.5f + 0.1f)));
    }


    /**
     * Get Warp distance penalty
     *
     * @param distanceValue Converter.Value Distance in length units
     * @return Distance penalty
     */
    private int getDistancePenalty(Converter.Value distanceValue) {
        int penalty;

        float yards = Converter.getInstance().convert(distanceValue, Converter.Units.Yard);
        float miles = yards / Converter.YARDS_IN_MILE;

        if (miles > 1000) {
            penalty = 7 + (int) Math.floor(Math.log(miles) - 3);
        } else if (miles > 100) {
            penalty = 7;
        } else if (miles > 10) {
            penalty = 6;
        } else if (miles > 2) {
            penalty = 5;
        } else if (yards > 500) {
            penalty = 4;
        } else if (yards > 100) {
            penalty = 3;
        } else if (yards > 20) {
            penalty = 2;
        } else if (yards > 10) {
            penalty = 1;
        } else {
            penalty = 0;
        }
        return -penalty;
    }

    private int getPreparationTimeModifier(Converter.Value time) {
        float minutes = Converter.getInstance().convert(time, Converter.Units.Minute);
        float seconds = minutes * 60;
        float hours = minutes / 60;

        int modifier = 0;
        return modifier;
    }

    public void activate(Converter.Value distanceValue, Converter.Value time) {
        int distancePenalty = getDistancePenalty(distanceValue);
        int preparationModifier = getPreparationTimeModifier(time);
        float characterModifiers = character.getModifiersList(this).getTotal();

        Intelligence IQ = (Intelligence) character.getBasicAttribute(Attribute.Intelligence);

        int rollAgainst = IQ.getLevel() + distancePenalty + preparationModifier + (int) characterModifiers;
        SimpleRoll roll = new SimpleRoll(rollAgainst);
        if (roll.getResult() == DiceRoller.RollResult.Success) {

        } else {
            warpFail();
        }
    }

    /**
     * On a failure, you go nowhere and strain your power: you
     * are at -5 to use it again in the next 10 minutes.
     */
    public void warpFail() {
        TimeModifier timeModifier = new TimeModifier(character, this, -5, 10 * 60f);
        addModifier(timeModifier);
    }

}
