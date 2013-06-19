package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.FixedTrait;

/**
 * Pitiable
 * =================================
 * Something about you makes people pity you and want to take care of
 * you. You get +3 on all reaction rolls
 * from those who consider you to be in a position of helplessness, weakness,
 * or need (which  never includes those
 * with the Callous disadvantage). Taken
 * in conjunction with above-average
 * looks, Pitiable means you are “cute”
 * instead of “sexy”; in combination with
 * below-average looks, it means you are
 * “appealingly homely,” like a basset
 * hound.
 * TODO: Modifiers targets
 */
public class Pitiable extends FixedTrait implements Advantage {
    Modifier reactionModifier;

    public Pitiable(Character character) {
        super(character, "Pitiable", 5);
        reactionModifier = addModifier(Rolls.ReactionRoll, new SummandModifier(0));
    }

}
