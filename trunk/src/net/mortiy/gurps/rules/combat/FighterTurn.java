package net.mortiy.gurps.rules.combat;

/**
 * One turn of
 */
public class FighterTurn {
    Fighter fighter;
    Maneuver maneuver;

    public FighterTurn(Fighter fighter, Maneuver maneuver) {
        this.fighter = fighter;
        this.maneuver = maneuver;
    }
}
