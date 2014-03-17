package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.VariableTrait;
import net.mortiy.gurps.rules.traits.categories.Mental;

/**
 * Shyness
 * You are uncomfortable around strangers.
 * Roleplay it! This disadvantage comes in three levels;
 * you can buy it off one level at a time.
 */
public class Shyness extends VariableTrait implements Disadvantage, Mental {

    public static enum Level implements ITraitLevel {
        /**
         * You are uneasy with strangers, especially assertive or attractive ones.
         * You have -1 on skills that require you to deal with people,
         * including Acting, Carousing, Diplomacy, Fast-Talk, Intimidation,
         * Leadership, Merchant, Panhandling, Performance, Politics, Public
         * Speaking, Savoir-Faire, Sex Appeal, Streetwise, and Teaching.
         */
        Mild(-5),
        /**
         * You are very uncomfortable around strangers, and tend to be quiet
         * even among friends. -2 the skills listed above.
         */
        Severe(-10),
        /**
         * You avoid strangers whenever possible.
         * You may not learn the skills listed above at all, and are at
         * -4 on default rolls on such skills.
         */
        Crippling(-20);

        private final int cost;

        Level(int cost) {
            this.cost = cost;
        }

        @Override
        public int getCost() {
            return cost;
        }
    }

    public Shyness(Individual individual) {
        super(individual, "Shyness");
    }


}
