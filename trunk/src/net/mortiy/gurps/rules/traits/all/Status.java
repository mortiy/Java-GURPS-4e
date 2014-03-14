package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.LevelTrait;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 11.12.12
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */
public class Status extends LevelTrait implements Advantage {

    Modifier reactionModifier;

    public Status(Individual individual, String name, int levelCost) {
        super(individual, name, levelCost);
        reactionModifier = addModifier(Rolls.ReactionRoll, new SummandModifier(0));
    }
}
