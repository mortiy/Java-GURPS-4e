package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.FixedTrait;
import net.mortiy.gurps.rules.traits.categories.Physical;

/**
 * AbsoluteDirection
 * ========================
 */
public class AbsoluteDirection extends FixedTrait implements Advantage, Physical {
    public AbsoluteDirection(Individual individual) {
        super(individual, "Absolute Direction", 5);
            registerModifier(individual.getSkill("Body Sense"), new SummandModifier(3));
            registerModifier(individual.getSkill("Navigation"), new SummandModifier(3));

    }
}
