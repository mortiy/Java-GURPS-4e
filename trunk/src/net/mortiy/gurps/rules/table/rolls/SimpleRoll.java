package net.mortiy.gurps.rules.table.rolls;

import net.mortiy.gurps.rules.table.DiceRoller;

public class SimpleRoll {
    DiceRoller.RollResult rollResult;
    int rollValue = 0;
    public SimpleRoll(int rollAgainstValue) {
        rollValue = DiceRoller.getInstance().roll(3, DiceRoller.Dice6D).getTotal();
        if(rollValue < rollAgainstValue){
            this.rollResult = DiceRoller.RollResult.Success;
        } else {
            this.rollResult = DiceRoller.RollResult.Failure;
        }
    }

    public DiceRoller.RollResult getResult() {
        return rollResult;
    }
}
