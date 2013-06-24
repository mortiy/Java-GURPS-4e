package net.mortiy.gurps.rules.combat;

/**
 * Stores current combat state
 */
public class CombatState {

    private boolean isPaused;
    private boolean isStarted;
    private Fighter currentFighter;
    private int round = 0;


    public void newRound(){
        round++;
        currentFighter = null;
    }

    public boolean hasCurrentFighter(){
        return currentFighter != null;
    }

    public Fighter getCurrentFighter() {
        return currentFighter;
    }

    public int getRound() {
        return round;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setCurrentFighter(Fighter currentFighter) {
        this.currentFighter = currentFighter;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}
