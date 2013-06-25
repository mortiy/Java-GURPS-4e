package net.mortiy.gurps.rules.table;

import net.mortiy.gurps.Log;


public class DiceRoller {

    private static DiceRoller diceRollerInstance = new DiceRoller();

    public static Dice Dice6D = new Dice(6);

    public enum RollResult {
        CriticalFailure,
        Failure,
        Success,
        CriticalSuccess
    }

    private DiceRoller() {
    }

    public static DiceRoller getInstance() {
        return diceRollerInstance;
    }

    public class RollModifier {
        private int value = 0;

        public RollModifier(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public DiceRollResult roll(Dice dice){
        return roll(1, dice, 0);
    }

    public DiceRollResult roll(Dice dice, Integer... modifiers){
        return roll(1, dice, modifiers);
    }

    public DiceRollResult roll(RollFormula formula, Integer ... modifiers){
        int modifier = formula.getModifier();
        for(Integer m : modifiers){
            modifier += m;
        }
        return roll(formula.getDiceQuantity(), Dice6D, modifier);
    }


    public DiceRollResult roll(int quantity, Dice dice, Integer... modifiers){
        DiceRollResult diceRollResult = new DiceRollResult(modifiers);
        if(quantity == 0){
            Log.w("DiceRoller", "Zero dice roll!");
        }
        for(int i = 0; i < quantity; i++){
            int rollNumber = dice.roll();
            diceRollResult.addRoll(rollNumber);
        }

        return diceRollResult;
    }

}
