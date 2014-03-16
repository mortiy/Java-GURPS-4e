package net.mortiy.gurps.rules.table.rolls;
import net.mortiy.gurps.rules.Individual;
 import net.mortiy.gurps.rules.table.DiceRoller;
 import net.mortiy.gurps.rules.table.Rolls;

public class ReactionRoll {
    Individual individual;
    public ReactionRoll(Individual individual) {
        this.individual = individual;
    }
    public DiceRoller.RollResult against(Individual individual){
        float totalReactionModifier = individual.getTotalModifier(Rolls.ReactionRoll);
        return DiceRoller.RollResult.Failure;
    }
}
