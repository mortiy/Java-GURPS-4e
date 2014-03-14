package net.mortiy.gurps.rules.traits.all;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.FixedTrait;

/**
 * Fashion Sense
 * ==================================
 * Your look is always one step ahead
 * of the crowd. You have the ability to
 * create a fashion statement out of the
 * cheapest and most nondescript materials. This gives +1 to reaction rolls in
 * social situations when you have a
 * chance to plan your attire in advance.
 * You can also give  someone else a +1
 * reaction bonus when you put together
 * the outfit.
 */
public class FashionSense extends FixedTrait implements Advantage {

    Modifier reactionModifier;

    public FashionSense(Individual individual) {
        super(individual, "Fashion Sense", 5);
        reactionModifier = addModifier(Rolls.ReactionRoll, new SummandModifier(+1));
    }
}
