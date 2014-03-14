package net.mortiy.gurps.rules.table.rolls;
 import net.mortiy.gurps.rules.Individual;
 import net.mortiy.gurps.rules.table.DiceRoller;
 import net.mortiy.gurps.rules.table.Rolls;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 10.12.12
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
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
