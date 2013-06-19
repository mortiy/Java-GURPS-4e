package net.mortiy.gurps.rules.table;

import net.mortiy.gurps.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
    public class DiceRollResult {
        private int total = 0;
        private List<Integer> rolls = new ArrayList<Integer>();
        private List<Integer> modifiers = new ArrayList<Integer>();

        DiceRollResult(Integer... modifiers) {
            this.modifiers = Arrays.asList(modifiers);
        }

        private void addRoll(Integer roll){
            rolls.add(roll);
        }

        public List<Integer> getRolls() {
            return rolls;
        }

        public int getTotal(){
            if(total == 0){
                for(Integer i : rolls){
                    total += i;
                }
                for(Integer modifier : modifiers){
                    total += modifier;
                }
            }
            return total;
        }
    }
    public DiceRollResult roll(Dice dice){
        return roll(1, dice, 0);
    }

    public DiceRollResult roll(Dice dice, Integer... modifiers){
        return roll(1, dice, modifiers);
    }

    public DiceRollResult roll(RollFormula formula){
        return roll(formula.getDiceQuantity(), Dice6D, formula.getModifier());
    }

    public DiceRollResult roll(int quantity, Dice dice, Integer... modifiers){
        DiceRollResult diceRollResult = new DiceRollResult(modifiers);
        if(quantity == 0){
            Log.w("DiceRoller", "Zero dice roll!");
        }
        for(int i = 0; i < quantity; i++){
            int rollNumber = dice.roll();
            diceRollResult.rolls.add(rollNumber);
        }

        return diceRollResult;
    }

}
