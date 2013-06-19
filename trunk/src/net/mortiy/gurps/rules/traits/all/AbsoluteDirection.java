package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.FixedTrait;
import net.mortiy.gurps.rules.traits.categories.Physical;

/**
 * AbsoluteDirection
 * ========================
 */
public class AbsoluteDirection extends FixedTrait implements Advantage, Physical {
    public AbsoluteDirection(Character character) {
        super(character, "Absolute Direction", 5);
            addModifier(character.getSkill("Body Sense"), new SummandModifier(3));
            addModifier(character.getSkill("Navigation"), new SummandModifier(3));

    }
}
