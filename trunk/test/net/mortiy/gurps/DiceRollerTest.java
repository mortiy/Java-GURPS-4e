package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.table.DiceRollResult;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.RollFormula;
import net.mortiy.gurps.rules.table.rolls.SuccessRoll;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 08.12.12
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class DiceRollerTest extends TestCase {
    DiceRoller diceRoller = DiceRoller.getInstance();

    public void testDiceRoller() throws Exception {

        final int NUMBER_OF_ROLLS = 10;
        DiceRollResult rollResult = diceRoller.roll(NUMBER_OF_ROLLS, DiceRoller.Dice6D);
        assertEquals("Check if RollResult contains correct number of rolls", NUMBER_OF_ROLLS, rollResult.getRolls().size());

        for(int i = 0, j = 1000; i < j; i++){
            if(diceRoller.roll(3, DiceRoller.Dice6D).getTotal() > 18){
                throw new WrongRollResultException();
            }
        }

        // Try to roll using
        diceRoller.roll(new RollFormula(3, 5));
    }

    public void testRollAgainst() {
        assertEquals(DiceRoller.RollResult.CriticalSuccess, new SuccessRoll(15).rollFor(5).getRollResult());
        assertEquals(DiceRoller.RollResult.CriticalFailure, new SuccessRoll(5).rollFor(15).getRollResult());
        assertEquals(DiceRoller.RollResult.Failure, new SuccessRoll(10).rollFor(12).getRollResult());
        assertEquals(DiceRoller.RollResult.Success, new SuccessRoll(10).rollFor(8).getRollResult());
    }

    private class WrongRollResultException extends Exception {
    }
}
