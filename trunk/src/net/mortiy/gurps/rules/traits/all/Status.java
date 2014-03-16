package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.LevelTrait;

public class Status extends LevelTrait implements Advantage {

    Modifier reactionModifier;

    public Status(Individual individual, String name, int levelCost) {
        super(individual, name, levelCost);
        reactionModifier = registerModifier(Rolls.ReactionRoll, new SummandModifier(0));
    }
}
