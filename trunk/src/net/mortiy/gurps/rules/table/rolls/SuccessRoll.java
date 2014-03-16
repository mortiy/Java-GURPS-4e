package net.mortiy.gurps.rules.table.rolls;

import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.DiceRoller.RollResult;

/**
 * Success Roll
 * =============================
 * Whenever a character attempts to
 * perform an action (e.g., use a skill),
 * roll three dice to determine the outcome.
 * This is called a success roll. The
 * task in question succeeds if the total rolled
 * on the dice is less than or equal to the number
 * that governs the action – most often a
 * skill or an attribute. Otherwise, it fails.
 *
 * To avoid bogging down the game in endless die rolls, the GM should only require a success
 * roll if there is a chance of meaningful failure or gainful success.
 * In particular, the GM should require success rolls when . . .
 *      • A PC’s health, wealth, friends, reputation, or equipment are
 * at risk. This includes chases, combat (even if the target is stationary
 * and at point-blank range!), espionage, thievery, and similar “adventuring”
 * activities.
 *      • A PC stands to gain allies, information, new abilities, social standing, or wealth.
 * The GM should not require rolls for . . .
 *      • Utterly trivial tasks, such as crossing the street, driving into town,
 * feeding the dog, finding the corner store, or turning on the computer.
 *      • Daily work at a mundane, nonadventuring job. (To evaluate job
 * performance, make monthly “job rolls”; see Jobs, p. 516.)
 */
public class SuccessRoll implements Modifier.IModifiable {
    private RollResult rollResult = DiceRoller.RollResult.Failure;
    private int effectiveSkill;
    private int diceRoll;
    private int margin;

    public SuccessRoll(int effectiveSkill) {
        this.effectiveSkill = effectiveSkill;

    }

    public SuccessRoll roll(){
        return roll(0);

    }
    public SuccessRoll roll(int modifiers){
        int diceRoll = DiceRoller.getInstance().roll(3, DiceRoller.Dice6D, modifiers).getTotal();
        return rollFor(diceRoll);
    }

    public SuccessRoll rollFor(int diceRoll){
        RollResult rollResult = RollResult.Failure;
        int margin = effectiveSkill - diceRoll;
        if (diceRoll == 3 || diceRoll == 4 || (margin >= 10)) {
            rollResult = RollResult.CriticalSuccess;
        } else if (diceRoll < effectiveSkill) {
            rollResult = RollResult.Success;
        } else if (diceRoll == 18 || (diceRoll == 17 && effectiveSkill <= 15) || (margin <= -10)) {
            rollResult = RollResult.CriticalFailure;
        } else if (diceRoll >= effectiveSkill) {
            rollResult = RollResult.Failure;
        }
        this.diceRoll = diceRoll;
        this.rollResult = rollResult;
        this.margin = margin;
        return this;
    }

    public DiceRoller.RollResult getRollResult() {
        return rollResult;
    }

    /**
     * Returns Success/Failure margin
     *
     * @return
     */
    public int getMargin() {
        return Math.abs(margin);
    }

    public int getDiceRoll() {
        return diceRoll;
    }

}
