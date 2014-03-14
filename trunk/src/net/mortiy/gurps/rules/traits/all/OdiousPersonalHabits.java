package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.SummandModifier;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.LevelTrait;

/**
 * Odious Personal Habits
 * =================================
 * You usually or always behave in a
 * fashion repugnant to others. An
 * Odious Personal Habit (OPH) is worth
 * -5 points for every -1 to reaction rolls
 * made by people who notice your problem. Specify the behavior when you
 * create your character, and work out
 * the point value with the GM.
 */
public class OdiousPersonalHabits extends LevelTrait implements Disadvantage {
    Modifier modifier;
    public OdiousPersonalHabits(Individual individual) {
        super(individual, "Odious Personal Habits", -5, 3);
        modifier = individual.addModifier(Rolls.ReactionRoll, new SummandModifier(0));
    }

    @Override
    public void onLevelChange(int currentLevel) {
        modifier.setModifierValue(new SummandModifier(-currentLevel));
    }
}
