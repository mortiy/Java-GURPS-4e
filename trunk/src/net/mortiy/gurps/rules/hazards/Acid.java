package net.mortiy.gurps.rules.hazards;

import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.RollFormula;

public class Acid extends Hazard {

    private DiceRoller diceRoller = DiceRoller.getInstance();
    private RollFormula rollFormula = new RollFormula(1, -3); // 1d-3

    public Acid(Individual character) {
        super(character);
    }

    @Override
    public int affect() {
        int injureValue = diceRoller.roll(rollFormula).getTotal();
        return injureValue;
    }
}
