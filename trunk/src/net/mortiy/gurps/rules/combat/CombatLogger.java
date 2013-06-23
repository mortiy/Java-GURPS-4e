package net.mortiy.gurps.rules.combat;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores every turn of combat
 */
public class CombatLogger {
    private List<FighterTurn> fighterTurns = new ArrayList<>();

    public void logTurn(FighterTurn fighterTurn){
        fighterTurns.add(fighterTurn);
    }

    public List<FighterTurn> getLog(){
        return fighterTurns;
    }
}
