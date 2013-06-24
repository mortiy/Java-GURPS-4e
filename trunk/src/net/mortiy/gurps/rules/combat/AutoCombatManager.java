package net.mortiy.gurps.rules.combat;

import net.mortiy.gurps.rules.combat.exceptions.FighterHasNoManueverException;
import net.mortiy.gurps.rules.combat.exceptions.ImpossibleManeuverException;
import net.mortiy.gurps.rules.combat.exceptions.IsNotReadyException;
import net.mortiy.gurps.rules.combat.exceptions.RoundIsOverException;

public class AutoCombatManager extends CombatManager {
    public AutoCombatManager(Combat combat) {
        super(combat);
    }

    public boolean start() {
        // Continue previous turn or start new
        if (fighterIterator == null || !fighterIterator.hasNext()) {
            startNewRound();
        }
        while (fighterIterator.hasNext()) {
            if (combatState.isPaused()) {
                return false;
            }
            try {
                super.turn();
            } catch (FighterHasNoManueverException e) {
                _log(e.getMessage());
                pause();
                return false;
            } catch (ImpossibleManeuverException e) {
                _log(e.getMessage());
                pause();
                return false;
            } catch (RoundIsOverException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                pause();
                return false;
            } catch (IsNotReadyException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                pause();
                return false;
            }
        }
        return true;
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


}
