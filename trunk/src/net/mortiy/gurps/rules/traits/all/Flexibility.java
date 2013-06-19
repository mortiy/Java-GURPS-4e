package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.FixedTrait;
import net.mortiy.gurps.rules.traits.categories.Physical;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 11.12.12
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class Flexibility extends FixedTrait implements Advantage, Physical {

    public Flexibility(Character character) {
        super(character, "Flexibility", 5);

        addModifier(character.getSkill("Climbing"), new SummandModifier(3));
        addModifier(character.getSkill("Escape"), new SummandModifier(3));

    }
}
