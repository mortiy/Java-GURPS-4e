package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.combat.exceptions.FighterHasNoManueverException;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;
import net.mortiy.gurps.rules.combat.exceptions.RoundIsOverException;
import net.mortiy.gurps.rules.combat.maneuver.DoNothingManeuver;

import java.util.*;

/**
 * Performs combat management
 */
public class CombatManager {

    public interface OnPauseListener {
        public void onPause(Fighter fighter);
    }

    protected Combat combat;
    protected CombatState combatState;
    protected CombatLogger combatLogger;

    protected Iterator<Fighter> fighterIterator;


    public CombatManager(Combat combat) {
        this.combat = combat;
        this.combatState = new CombatState();

        startNewRound();
    }


    public FighterTurn turn() throws FighterHasNoManueverException, ImpossibleManeuverException, RoundIsOverException, IsNotReadyException {

        if(isRoundOver()){
            throw new RoundIsOverException();
        }

        Fighter currentFighter = fighterIterator.next();

        combatState.setCurrentFighter(currentFighter);

        if (!currentFighter.isActive()) {
            return new FighterTurn(currentFighter, new DoNothingManeuver());
        }

        FighterTurn fighterTurn;

        if (currentFighter.hasManeuver()) {
            fighterTurn = new FighterTurn(currentFighter, currentFighter.getNextManeuver());

            // Fighter executes maneuver:
            ManeuverResult maneuverResult = combat.resolveManeuver(currentFighter);
            fighterTurn.setManeuverResult(maneuverResult);

        } else {
            throw new FighterHasNoManueverException(String.format("Fighter '%s' requires maneuver. ", currentFighter.getIndividual().getName()));
        }

        return fighterTurn;
    }

    /**
     * Starts new combat turn
     */
    public void startNewRound() {
        combatState.newRound();
        _log("============== [Round #%d] =========================", combatState.getRound());
        fighterIterator = combat.getFighters().listIterator();
    }

    public boolean isRoundOver(){
        return !fighterIterator.hasNext();
    }

    protected void _log(String message, Object... args) {
        Log.i("Combat Manager", String.format(message, args));
    }
}
