package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.traits.*;
import net.mortiy.gurps.rules.traits.categories.Social;

/**
 * Wealth
 * ===================================
 * Above-average Wealth is an advantage; it means you start with two or
 * more times the average starting wealth of your game world.
 * Below-average Wealth is a disadvantage; it means you
 * start with only a fraction of average
 * starting wealth. The precise meaning of
 * each wealth level in a particular game
 * world will be defined in the associated
 * worldbook.
 */
public class Wealth extends VariableTrait implements Advantage, Disadvantage, Social {

    private float multipliers[] = new float[]{0f, 0.2f, 0.5f, 1.0f, 2.0f, 5.0f, 20.0f};

    public Wealth(Individual individual) {
        super(individual, "Wealth");
        levelsCost = new int[]{-25, -15, -10, 0, 10, 20, 30, 50, 75, 100, 125, 150, 175, 200, 225, 250};
        changeLevel(Levels.Average);
    }

    public float getWealthMultiplier() {
        return currentLevel.ordinal() > 7
                ? (float) Math.pow(10, currentLevel.ordinal() - 6) // 10 x Multimillionaire Level
                : multipliers[currentLevel.ordinal()];
    }

    public static enum Levels implements ITraitLevel {
        /**
         * You have no job, no
         * source of income, no money, and no
         * property other than the clothes you are
         * wearing. Either you are unable to work
         * or there are no jobs to be found
         */
        DeadBroke,
        /**
         * Your starting wealth is only
         * 1/5 of the average for your society.
         * Some jobs are not available to you, and
         * no job you find pays very well.
         */
        Poor,
        /**
         * Your starting wealth is
         * only 1/2 of the average for your society.
         * Any job is open to you (you can be
         * a Struggling doctor or movie actor),
         * but you don’t earn much. This is
         * appropriate if you are, for instance, a
         * 21st-century student
         */
        Struggling,
        /**
         * The default wealth level, as
         * explained above
         */
        Average,
        /**
         * You work for a living,
         * but your lifestyle is better than most.
         * Your starting wealth is twice the average
         */
        Comfortable,
        /**
         * Your starting wealth is five
         * times average; you live very well
         * indeed
         */
        Wealthy,
        /**
         * Your starting wealth is
         * 20 times the average.
         */
        VeryWealthy,
        /**
         * Your starting wealth is
         * 100 times average. You can buy almost
         * anything you want without considering
         * the cost.
         */
        FilthyRich,
        /**
         * “Filthy rich”
         * doesn’t even begin to describe your
         * wealth!
         */
        Multimillionaire_1,
        Multimillionaire_2,
        Multimillionaire_3,
        Multimillionaire_4,
        Multimillionaire_5,
        Multimillionaire_6,
        Multimillionaire_7,
        Multimillionaire_8
    }

}
