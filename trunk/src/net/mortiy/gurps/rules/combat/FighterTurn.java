package net.mortiy.gurps.rules.combat;

/**
 * One turn of
 */
public class FighterTurn {
    protected Fighter fighter;
    protected Maneuver maneuver;
    protected ManeuverResult maneuverResult;

    public FighterTurn(Fighter fighter, Maneuver maneuver) {
        this.fighter = fighter;
        this.maneuver = maneuver;
    }

    public void setManeuverResult(ManeuverResult maneuverResult) {
        this.maneuverResult = maneuverResult;
    }

    public Fighter getFighter() {
        return fighter;
    }

    public Maneuver getManeuver() {
        return maneuver;
    }

    public ManeuverResult getManeuverResult() {
        return maneuverResult;
    }
}
