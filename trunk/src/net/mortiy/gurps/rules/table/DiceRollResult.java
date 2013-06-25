package net.mortiy.gurps.rules.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DiceRollResult {
    private int total = 0;
    private List<Integer> rolls = new ArrayList<Integer>();
    private List<Integer> modifiers = new ArrayList<Integer>();

    DiceRollResult(Integer... modifiers) {
        this.modifiers = Arrays.asList(modifiers);
    }

    public void addRoll(Integer roll){
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
