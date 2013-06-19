package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.FixedTrait;
import net.mortiy.gurps.rules.traits.categories.Physical;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 11.12.12
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public class PerfectBalance extends FixedTrait implements Advantage, Physical {
    public PerfectBalance(net.mortiy.gurps.rules.Character character) {
        super(character, "Perfect Balance", 15);
        addModifier(character.getSkill("Climbing"), new SummandModifier(1));
        addModifier(character.getSkill("Acrobatics"), new SummandModifier(1));
    }
}
