package net.mortiy.gurps.rules.table.rolls;
 import net.mortiy.gurps.rules.Character;
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
    Character character;
    public ReactionRoll(Character character) {
        this.character = character;
    }
    public DiceRoller.RollResult against(Character character){
        float totalReactionModifier = character.getTotalModifier(Rolls.ReactionRoll);
        return DiceRoller.RollResult.Failure;
    }
}
