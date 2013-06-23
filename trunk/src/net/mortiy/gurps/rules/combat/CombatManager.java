package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.combat.exceptions.FighterHasNoManueverException;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;

import java.util.*;

/**
 * Performs combat management
 */
public class CombatManager {

    public interface OnPauseListener {
        public void onPause(Fighter fighter);
    }

    private Combat combat;
    private CombatState combatState;
    private CombatLogger combatLogger;

    private OnPauseListener onPauseListener;

    private Iterator<Fighter> fighterIterator;

    public CombatManager(Combat combat) {
        this.combat = combat;
        this.combatState = new CombatState();
    }

    public void setOnPauseListener(OnPauseListener onPauseListener) {
        this.onPauseListener = onPauseListener;
    }

    public void performTurns(){
        performTurns(-1);
    }

    /**
     * Performs combat while for given number of turns or while
     * there are active fighters present.
     *
     * @param turnsCounter
     */
    public void performTurns(int turnsCounter) {

        combatState.setPaused(true);
        while (combat.getFighters().size() > 1 && !combatState.isPaused() && ((turnsCounter == -1) || (turnsCounter-- > 0))) {
            try {
                if (!turn()) {
                    return;
                }
            } catch (FighterHasNoManueverException e) {
                _log(e.getMessage());
                pause();
                return;
            } catch (ImpossibleManeuverException e) {
                _log(e.getMessage());
                pause();
                return;
            }
        }

    }

    /**
     * Pauses combat
     */
    public void pause() {
        _log("Combat is paused.");
        combatState.setPaused(true);
        if (onPauseListener != null) {
            onPauseListener.onPause(combatState.getCurrentFighter());
        }
    }

    private boolean turn() throws FighterHasNoManueverException, ImpossibleManeuverException {

        // Continue previous turn or start new
        if (fighterIterator == null || !fighterIterator.hasNext()) {
            newTurn();
        }
        while (fighterIterator.hasNext()) {
            if (combatState.isPaused()) {
                return false;
            }
            if (!combatState.hasCurrentFighter()) {
                combatState.setCurrentFighter(fighterIterator.next());
            }

            Fighter currentFighter = combatState.getCurrentFighter();

            if (!currentFighter.isActive()) {
                continue;
            }
            if (currentFighter.hasManeuver()) {
                // Fighter executes maneuver:
                combat.resolveManeuver(currentFighter);
            } else {
                throw new FighterHasNoManueverException(String.format("Fighter '%s' requires maneuver. ", currentFighter.getCharacter().getName()));
            }
        }
        return true;
    }

    /**
     * Starts new combat turn
     */
    private void newTurn() {
        combatState.newTurn();
        _log("[Turn #%d]", combatState.getCurrentTurn());
        fighterIterator = combat.getFighters().listIterator();
    }

    private void _log(String message, Object... args) {
        Log.i("Combat Manager", String.format(message, args));
    }
}
