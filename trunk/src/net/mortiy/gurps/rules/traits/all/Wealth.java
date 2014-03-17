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
        DeadBroke(-25),
        /**
         * Your starting wealth is only
         * 1/5 of the average for your society.
         * Some jobs are not available to you, and
         * no job you find pays very well.
         */
        Poor(-15),
        /**
         * Your starting wealth is
         * only 1/2 of the average for your society.
         * Any job is open to you (you can be
         * a Struggling doctor or movie actor),
         * but you don’t earn much. This is
         * appropriate if you are, for instance, a
         * 21st-century student
         */
        Struggling(-10),
        /**
         * The default wealth level, as
         * explained above
         */
        Average(0),
        /**
         * You work for a living,
         * but your lifestyle is better than most.
         * Your starting wealth is twice the average
         */
        Comfortable(10),
        /**
         * Your starting wealth is five
         * times average; you live very well
         * indeed
         */
        Wealthy(20),
        /**
         * Your starting wealth is
         * 20 times the average.
         */
        VeryWealthy(30),
        /**
         * Your starting wealth is
         * 100 times average. You can buy almost
         * anything you want without considering
         * the cost.
         */
        FilthyRich(50),
        /**
         * “Filthy rich”
         * doesn’t even begin to describe your
         * wealth!
         */
        Multimillionaire_1(75),
        Multimillionaire_2(100),
        Multimillionaire_3(125),
        Multimillionaire_4(150),
        Multimillionaire_5(175),
        Multimillionaire_6(200),
        Multimillionaire_7(225),
        Multimillionaire_8(250);

        private final int cost;

        Levels(int cost) {
            this.cost = cost;
        }

        @Override
        public int getCost() {
            return cost;
        }
    }

}
